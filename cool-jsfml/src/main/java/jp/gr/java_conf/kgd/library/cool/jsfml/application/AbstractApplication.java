package jp.gr.java_conf.kgd.library.cool.jsfml.application;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.ActorHolder;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs.IJSFMLInputStore;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs.IJSFMLInputStoreManager;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs.JSFMLInputStoreManager;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.IResourceRegister;
import jp.gr.java_conf.kgd.library.cool.jsfml.resource.ResourceRegister;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public abstract class AbstractApplication
extends ApplicatonConfig
implements IApplication, IApplicationContext
{
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	public static final int DEFAULT_COLOR_BITS = 32;

	public static final String DEFAULT_TITLE = "Application";
//	public static final int DEFAULT_FPS = 60;
	
	// context
	//状態
	private boolean isRunning = false;
	private boolean hasFocus = true;

	private float averageFPS = DEFAULT_TARGET_FPS;
	private long elapsedFrame = 0;
	
	//view
	private final RenderWindow renderWindow;
	
	private final List<Event> events = new LinkedList<>();
	
	private final Map<Object, RenderTexture> renderTextureMap = new LinkedHashMap<>();
	//RenderTextureは生成が重いので、同サイズで使いまわせるところはできるだけ使いまわす。
	//画面全体のエフェクトは同サイズのままで使いまわしやすい。
	//ただし、入れ子や平行して描画される場合に注意。
	
	
	private Sound backgroundMusic = new Sound();
	
	
	//input
	//仮想キーの設定はマネージャインターフェースからしかできないので、
	//アプリケーションを継承した先のクラスで行う必要がある。
	private IJSFMLInputStoreManager inputStoreManager = new JSFMLInputStoreManager();

	
	//リソース
	private IResourceRegister resourceRegister = new ResourceRegister();
	
	private IApplicationDataBox applicationDataBox = new ApplicationDataBox();
	//
	
	
	//内部使用
	private ActorHolder rootActorHolder = new ActorHolder();
	
	//
	protected AbstractApplication()
	{
		this(new VideoMode(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR_BITS));
	}
	
	protected AbstractApplication(VideoMode videoMode)
	{
		renderWindow = new RenderWindow(videoMode, DEFAULT_TITLE);
		
		backgroundMusic.setLoop(true);
		backgroundMusic.setVolume(80);
	}
	
	//
	public boolean isRunning()
	{
		return isRunning;
	}
	
	@Override
	public boolean hasFocus()
	{
		return hasFocus;
	}
	
	@Override
	public float getAverageFPS()
	{
		return averageFPS;
	}
	
	@Override
	public long getElapsedFrame()
	{
		return elapsedFrame;
	}
	
	@Override
	public void endApplication()
	{
		if(!isRunning)
		{
			return;
		}
		
		renderWindow.close();
	}
	
	@Override
	public IResourceRegister getResourceRegister()
	{
		return resourceRegister;
	}
	
	@Override
	public IApplicationDataBox getApplicationDataBox()
	{
		return applicationDataBox;
	}
	
	@Override
	public RenderWindow getApplicationWindow()
	{
		return renderWindow;
	}
	
	@Override
	public Collection<? extends Event> getCurrentEvents()
	{
		return Collections.unmodifiableCollection(events);
	}
	
	@Override
	public Map<Object, RenderTexture> getRenderTextureMap()
	{
		return renderTextureMap;
	}
	
	@Override
	public Sound getBackgroundMusic()
	{
		return backgroundMusic;
	}
	
	@Override
	public IJSFMLInputStore getInputStore()
	{
		return inputStoreManager;
	}
	
	//
	@Override
	final public void startApplication()
	{
		if(isRunning)
		{
			throw new IllegalStateException();
		}
		
		{
			IActor rootActor = getRootActor();
			if(rootActor == null)
			{
				throw new IllegalStateException();
			}
			
			rootActorHolder.setInnerActor(rootActor);
		}
		
		//ウインドウを画面の中央へ
		ApplicationHelper.setWindowPositionToCenter(renderWindow);
		
		int targetFPS = getTargetFPS();
		averageFPS = targetFPS;
		renderWindow.setFramerateLimit(targetFPS);
	
		isRunning = true;
		while(renderWindow.isOpen())
		{
			final long startNano = System.nanoTime();

			{
				int currentTargetFPS = getTargetFPS();
				if(targetFPS != currentTargetFPS)
				{
					targetFPS = currentTargetFPS;
					renderWindow.setFramerateLimit(targetFPS);
				}
			}
			
			// poll events
			{
				Event event;
				events.clear();
				while ((event = renderWindow.pollEvent()) != null)
				{
					events.add(event);

					if (event.type == Event.Type.CLOSED)
					{
						if(!isClosedEventIgnored())
						{
							renderWindow.close();
						}
					}
					
					if (event.type == Event.Type.GAINED_FOCUS)
					{
						hasFocus = true;
					}	
					
					if (event.type == Event.Type.LOST_FOCUS)
					{
						hasFocus = false;
					}
					
					
					//※Windowをリサイズした際にアスペクト比を維持したいが、
					//　ライブラリのバグで、Window#setSizeでサイズの変更ができない(なぜか位置が変わる)。
					//　ソースを覗いた感じでは、setSize時に誤ってnativeSetPositionされている。
//					if(event.type == Event.Type.RESIZED)
//					{
//						Vector2i newSize = event.asSizeEvent().size;
//						Vector2f viewSize = renderWindow.getDefaultView().getSize();
//
//						if(newSize.x >= newSize.y)
//						{
//							newSize = new Vector2i(newSize.x, (int)(newSize.x * viewSize.y / viewSize.x));
//						}
//						else
//						{
//							newSize = new Vector2i((int)(newSize.y * viewSize.x / viewSize.y), newSize.y);
//						}
//						
//						renderWindow.setSize(newSize);
//					}
					
					
				}
			}

			// フォーカスが無い場合の処理
			if (!hasFocus)
			{
				if (isUpdateNeedsFocus())
				{
					continue;
				}
			}

			
			//input
			inputStoreManager.updateInputs(hasFocus);

			//update
			//自分自身の更新。
			onUpdate();

			//子（rootActorHolderの中身
			{
				//update
				ConstView defaultView = renderWindow.getDefaultView();
				ComponentHelper.setSizeIfNeedsUpdate(rootActorHolder, defaultView.getSize());
				rootActorHolder.update();
				
				//draw
				int interval = getDrawSkipInterval();
				
				if(interval == 0 || elapsedFrame % (interval + 1) == 0)
				{
					renderWindow.setView(defaultView);
					renderWindow.clear();
					
					rootActorHolder.draw(renderWindow, RenderStates.DEFAULT,
								ComponentHelper.DEFAULT_COMPONENT_STATE);

					//
					renderWindow.display();
					//ここでJSFML側のFPS設定によってディレイがかかる。
					//内部的にはsleep(16)ほどに精度が悪いらしい。
					//displayはclearを介さずに続けて呼び出すとカクつくので
					//少し不恰好なやり方になっている。
				}
				
				if(interval > 0)
				{
					//突貫工事。
					//display()をスキップするとJSFML側で行ってるFPS管理が狂うので
					//余分にsleepする。
					//（こっちが自前でスリープすると、次のdisplay時にJSFML側が
					//　めちゃくちゃ遅れていると判断して、待機せずにdisplayを抜けてくる）
					long frameNano = NANO_PER_SECOND / targetFPS ;					
					long targetNano = startNano + frameNano;

					while(System.nanoTime() < targetNano)
					{
						try
						{
							TimeUnit.NANOSECONDS.sleep(SLEEP_INTERVAL);
						}
						catch(InterruptedException e)
						{
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}	

			
			//FPS計測
			{
				long elapsedNano = System.nanoTime() - startNano;
				double currentFPS = (double)NANO_PER_SECOND / (elapsedNano > 0 ? elapsedNano : 1);
				
				averageFPS = (float) ((averageFPS * AVERAGE_FPS_SAMPLE_NUM + currentFPS) /
						(AVERAGE_FPS_SAMPLE_NUM + 1));
			}
			
			//1フレーム経過。カウントアップ。
			elapsedFrame++;
		}
		
		isRunning = false;
		
		onDestroy();
	}
	
	
	//	
	protected IJSFMLInputStoreManager getInputStoreManager()
	{
		return inputStoreManager;
	}
	
	//
	abstract protected IActor getRootActor();
	abstract protected void onUpdate();
	abstract protected void onDestroy();
	
	//
	private static final int AVERAGE_FPS_SAMPLE_NUM = 60;
	private static final long NANO_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
	private static final long SLEEP_INTERVAL = TimeUnit.MILLISECONDS.toNanos(1) / 10;
}
