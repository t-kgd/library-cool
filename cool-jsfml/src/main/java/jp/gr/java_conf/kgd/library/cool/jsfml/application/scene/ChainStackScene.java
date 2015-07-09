package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationContext;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneResultData;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.IBlinkShapeState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.IShakeAnimationState;
import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

/**
 * シーンマネージャ実装のためのクラスです。
 * あまりにも不恰好なのでパッケージスコープで隠蔽しています。
 * @author taisa
 *
 */
class ChainStackScene
implements IChainStackScene
{
	private IChainStackScene chainPrevScene;
	
	private IScene currentScene;

	
	//
	public ChainStackScene()
	{
	}
	
	public ChainStackScene(IScene currentScene)
	{
		this.currentScene = currentScene;
	}
	
	public ChainStackScene(IChainStackScene chainPrevScene, IScene currentScene)
	{
		this.chainPrevScene = chainPrevScene;
		this.currentScene = currentScene;
	}
	
	@Override
	public IChainStackScene getChainPrevScene()
	{
		return chainPrevScene;
	}
	
	@Override
	public void setChainPrevScene(IChainStackScene prevScene)
	{
		this.chainPrevScene = prevScene;
	}
	
	@Override
	public IScene getCurrentScene()
	{
		return currentScene;
	}
	
	@Override
	public void setCurrentScene(IScene scene)
	{
		this.currentScene = scene;
	}
	
	
//	//	
//	public void onResizeScene(Vector2f newSize)
//	{
//		if(currentScene == null)
//		{
//			return;
//		}
//		
//		IChainStackScene chainBuf = this;
//		while(chainBuf != null)
//		{
//			chainBuf.setSize(newSize);
//			
//			chainBuf = chainBuf.getChainPrevScene();
//		}
//	}
	
	@Override
	public void setSize(Vector2f size)
	{
		if(currentScene == null)
		{
			return;
		}
		
		currentScene.setSize(size);
		
		if(chainPrevScene != null)
		{
			chainPrevScene.setSize(size);
		}
	}
	
	
	@Override
	public void update()
	{
		if(currentScene == null)
		{
			return;
		}
		
		currentScene.update();
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(currentScene == null)
		{
			return;
		}
		
		if(!isVisible())
		{
			return;
		}
		
		if(chainPrevScene != null)
		{
			ComponentHelper.drawChildComponent(target, states, mask, currentScene, chainPrevScene);
		}
		
		currentScene.draw(target, states, mask);
	}
	
	
	public boolean isActive()
	{
		return currentScene.isActive();
	}

	public boolean isVisible()
	{
		return currentScene.isVisible();
	}

	public void setActive(boolean flag)
	{
		currentScene.setActive(flag);
	}

	public void setVisible(boolean flag)
	{
		currentScene.setVisible(flag);
	}

	public Color getColorMask()
	{
		return currentScene.getColorMask();
	}

	public Vector2f getSize()
	{
		return currentScene.getSize();
	}

	public float getAlphaMaskRate()
	{
		return currentScene.getAlphaMaskRate();
	}

	public void setColorMask(Color color)
	{
		currentScene.setColorMask(color);
	}

	public Vector2f getOrigin()
	{
		return currentScene.getOrigin();
	}

	public void setSize(float width, float height)
	{
		currentScene.setSize(width, height);
	}

	public float getRotation()
	{
		return currentScene.getRotation();
	}

	public Vector2f getScale()
	{
		return currentScene.getScale();
	}

	public void setAlphaMaskRate(float rate)
	{
		currentScene.setAlphaMaskRate(rate);
	}

	public Vector2f getPosition()
	{
		return currentScene.getPosition();
	}

	public FloatRect getLocalBounds()
	{
		return currentScene.getLocalBounds();
	}

	public Transform getTransform()
	{
		return currentScene.getTransform();
	}

	public FloatRect getGlobalBounds()
	{
		return currentScene.getGlobalBounds();
	}

	public void setPosition(float x, float y)
	{
		currentScene.setPosition(x, y);
	}

	public void setAnimationEnabled(boolean flag)
	{
		currentScene.setAnimationEnabled(flag);
	}

//	public ISnapAnimationState getSnapAnimationState()
//	{
//		return currentScene.getSnapAnimationState();
//	}

	public IShakeAnimationState getShakeAnimationState()
	{
		return currentScene.getShakeAnimationState();
	}

	public boolean isAnimationEnabled()
	{
		return currentScene.isAnimationEnabled();
	}

	public IBlinkShapeState getMaskShapeState()
	{
		return currentScene.getMaskShapeState();
	}

	public void setPosition(Vector2f v)
	{
		currentScene.setPosition(v);
	}

	public void setRotation(float angle)
	{
		currentScene.setRotation(angle);
	}

	public void setScale(float x, float y)
	{
		currentScene.setScale(x, y);
	}

	public void setScale(Vector2f factors)
	{
		currentScene.setScale(factors);
	}

	public void setOrigin(float x, float y)
	{
		currentScene.setOrigin(x, y);
	}

	public void setOrigin(Vector2f v)
	{
		currentScene.setOrigin(v);
	}

	public void move(float x, float y)
	{
		currentScene.move(x, y);
	}

	public void move(Vector2f v)
	{
		currentScene.move(v);
	}

	public void rotate(float angle)
	{
		currentScene.rotate(angle);
	}

	public void scale(float x, float y)
	{
		currentScene.scale(x, y);
	}

	public void scale(Vector2f factors)
	{
		currentScene.scale(factors);
	}

	public Transform getInverseTransform()
	{
		return currentScene.getInverseTransform();
	}

	public void startScene(IConstObjectBox args)
	{
		currentScene.startScene(args);
	}

	public void resumeScene(int registerdID, int resultCode,
			IConstObjectBox resultValues)
	{
		currentScene.resumeScene(registerdID, resultCode, resultValues);
	}

	public SceneMoveState getSceneMoveState()
	{
		return currentScene.getSceneMoveState();
	}

	public IConstSceneMoveData getSceneMoveData()
	{
		return currentScene.getSceneMoveData();
	}

	public IConstSceneResultData getSceneResultData()
	{
		return currentScene.getSceneResultData();
	}
	
	public void clearMoveSceneState()
	{
		currentScene.clearMoveSceneState();
	}

	public IApplicationContext getApplicationContext()
	{
		return currentScene.getApplicationContext();
	}

	public void setApplicationContext(IApplicationContext context)
	{
		currentScene.setApplicationContext(context);
	}
	
	
}
