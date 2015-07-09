package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene;

import java.util.LinkedList;
import java.util.Objects;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IUpdatable;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.IApplicationContext;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.IConstSceneResultData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.ISceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.SceneMoveData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data.SceneResultData;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.factory.IConstSceneFactory;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.transition.ITransition;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

/**
 * シーンの遷移を管理するシーンクラスです。
 * 自身もシーンクラスのため、入れ子にすることが可能です。
 * シーンファクトリーはシーン間の依存を無くしたい時に利用してください。
 * また、同パッケージ内の抽象シーンクラスはこのクラスへの機能的（実質的）な依存度がとても高いです。
 * @author taisa
 *
 */
public abstract class AbstractSceneManager
extends AbstractParentScene
{
	private static final IConstSceneResultData EMPTY_RESULT_DATA =
			new SceneResultData(IConstSceneResultData.RESULT_FAILED);
	
	//
	private IConstSceneFactory sceneFactory;
	
	private LinkedList<IConstSceneMoveData> moveDataStack = new LinkedList<>();
	
	private IChainStackScene chainPrevScene;
	private IScene currentScene;
	
	private IUpdatable currentTask = new InitializeTask();
	//
	
	//
	private ISceneMoveData firstSceneMoveData = new SceneMoveData();
	
	
	//
	protected AbstractSceneManager(IApplicationActor other)
	{
		super(other);
	}

	protected AbstractSceneManager(IApplicationContext context)
	{
		super(context);
	}
	
	
	//
	protected IConstSceneFactory getSceneFactory()
	{
		return sceneFactory;
	}

	protected void setSceneFactory(IConstSceneFactory sceneFactory)
	{
		this.sceneFactory = sceneFactory;
	}
	
	protected ISceneMoveData getFirstSceneMoveData()
	{
		return firstSceneMoveData;
	}
	
	protected void setFirstScene(IScene firstScene)
	{
		firstSceneMoveData.setNextScene(Objects.requireNonNull(firstScene));
	}
	
	//
	@Override
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		
		if(chainPrevScene != null)
		{
			chainPrevScene.setSize(size);
		}
		
		if(currentScene != null)
		{
			currentScene.setSize(size);
		}
	}
	
	
//	@Override
//	protected void onUpdate()
//	{
//		currentTask.update();
//	}
	@Override
	protected void onUpdateScene()
	{
		currentTask.update();
	}
	
	@Override
	protected void onUpdateSceneChildren()
	{
//		if(currentScene != null)
//		{
//			currentScene.update();
//		}
	}

	@Override
	protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		if(chainPrevScene != null)
		{
			chainPrevScene.draw(target, states, mask);
		}
		
		if(currentScene != null)
		{
			currentScene.draw(target, states, mask);
		}
	}
	
	
	private final IScene createScene(String name)
	{
		if(sceneFactory == null)
		{
			return null;
		}
		
		return sceneFactory.createScene(name);
	}
	
	
	//
	private final class InitializeTask
	implements IUpdatable
	{
		@Override
		public void update()
		{
			DummyScene scene = new DummyScene(getApplicationContext());
			
			if(scene.moveNextScene(firstSceneMoveData))
			{
				firstSceneMoveData.getOutTransition().setTransitionTime(0);
				firstSceneMoveData.setTransitionInterval(0);
				currentScene = scene;
			}
			
			firstSceneMoveData = null;
			
			currentTask = new NormalTask();
		}
	}
	
	private final class NormalTask
	implements IUpdatable
	{
		@Override
		public void update()
		{
			if(getSceneMoveState() != SceneMoveState.CONTINUE)
			{
				return;
			}
			
			if(currentScene == null)
			{
				onFinishScene();
				return;
			}
			
			//
//			currentScene.update();
			//アップデート方法をどうするか悩みどころ。
			ActorHelper.updateStrictFixInnerActor(currentScene, getSize());
			
			
			
			SceneMoveState state = currentScene.getSceneMoveState();
			
			if(state != SceneMoveState.CONTINUE)
			{
				
				if(state == SceneMoveState.FINISHED)
				{
					IConstSceneMoveData frontMoveData = moveDataStack.peek();
					
					while(true)
					{
						if(chainPrevScene == null)
						{
							currentScene = null;
							return;
						}
												
						IConstSceneResultData resultData = currentScene.getSceneResultData();

						if(resultData == null)
						{
							//ここには来ないはず。
							System.err.println("シーン終了時のリザルトデータが設定されていません。デフォルト値を代入します。");
							resultData = EMPTY_RESULT_DATA;
						}

						IConstSceneMoveData moveData = moveDataStack.pop();

						chainPrevScene.clearMoveSceneState();
						
						//再開
						chainPrevScene.resumeScene(moveData.getRegisterID(),
								resultData.getResultCode(), resultData.getResultValues());
						
						
						if(chainPrevScene.getSceneMoveState() != SceneMoveState.FINISHED)
						{
							if(resultData.isTransitionsOverWrite())
							{
								currentTask = createFinishTransitionTask(currentScene, chainPrevScene,
										resultData.getOutTransition(), resultData.getInTransition(), 
										resultData.isTransitionCrossed(),
										resultData.getTransitionInterval());
							}
							else
							{
								currentTask = createFinishTransitionTask(currentScene, chainPrevScene,
										frontMoveData.getInTransition(), moveData.getOutTransition(), 
										moveData.isTransitionCrossed(),
										moveData.getTransitionInterval());
							}
							
							return;
						}
												
						chainPrevScene = chainPrevScene.getChainPrevScene();	
						//トランジション用に前面を残し、後ろのシーンだけ更に巻き戻していく。
					}
				}

				
				if(state == SceneMoveState.MOVE)
				{
					IConstSceneMoveData moveData = currentScene.getSceneMoveData();
					
					if(moveData == null)
					{
						throw new IllegalStateException();
					}
					
					IScene nextScene = moveData.getNextScene();
					
					if(nextScene == null)
					{
						nextScene = createScene(moveData.getNextSceneName());
						
						if(nextScene == null)
						{
							throw new IllegalStateException();
						}
					}

					
					chainPrevScene = new ChainStackScene(chainPrevScene, currentScene);
					currentScene = nextScene;
					
	
					moveDataStack.push(moveData);
					
					//シーン開始
					nextScene.setApplicationContext(getApplicationContext());
					nextScene.setSize(getSize());
					nextScene.startScene(moveData.getMoveArgs());	
					
					
					currentTask = createMoveTransitionTask(chainPrevScene, currentScene, moveData);		
					return;
				}


			}
		}
	}
	
	
	
	
	private TransitionTask createFinishTransitionTask(IScene outScene, IScene inScene,
			ITransition outTransition, ITransition inTransition,
			boolean isCrossed, int intervalTime)
	{
		return new TransitionTask(outScene, inScene, outTransition, inTransition,
				isCrossed, intervalTime);
	}
	
	private TransitionTask createMoveTransitionTask(IScene outScene, IScene inScene,
			IConstSceneMoveData moveData)
	{
		return new TransitionTask(outScene, inScene, moveData);
	}
	
	private final class TransitionTask
	implements IUpdatable
	{
		//
		private IScene outScene;
		private IScene inScene;
		
		//
		private boolean isMoveTransition;
		private boolean isCurrentDestroyed = false;
		
		private ITransition outTransition;
		private ITransition inTransition;
		
		//
		private int delayTime;
		private int currentCount = 0;
		
		//		
//		public TransitionTask(IScene outScene, IScene inScene,
//				ITransition outTransition, ITransition inTransition,
//				boolean isCrossed, int intervalTime,
//				boolean isMoveTransition, boolean isCurrentDestroyed)
//		{
//			this.outScene = outScene;
//			this.inScene = inScene;
//			
////			outScene.getSnapAnimationState().setSnapTarget(null);
////			inScene.getSnapAnimationState().setSnapTarget(null);
//			
//			//
//			this.isMoveTransition = isMoveTransition;
//			this.isCurrentDestroyed = isCurrentDestroyed;
//			
//			this.outTransition = outTransition;
//			this.inTransition = inTransition;
//			
//			outTransition.setTargetActorState(outScene);
//			outTransition.startTransition(true);
//							
//			inTransition.setTargetActorState(inScene);
//			inTransition.startTransition(false);
//			
//			delayTime = intervalTime;
//			
//			if(!isCrossed)
//			{
//				delayTime += outTransition.getTransitionTime();
//			}
//		}
		
		//フィニッシュ側
		public TransitionTask(IScene outScene, IScene inScene,
				ITransition outTransition, ITransition inTransition,
				boolean isCrossed, int intervalTime)
		{
			this.outScene = outScene;
			this.inScene = inScene;
			
//			outScene.getSnapAnimationState().setSnapTarget(null);
//			inScene.getSnapAnimationState().setSnapTarget(null);
			
			//
			this.isMoveTransition = false;
			
			this.outTransition = outTransition;
			this.inTransition = inTransition;
			
			
			outTransition.setTargetActorState(outScene);
			outTransition.startTransition(true);
			
					
			inTransition.setTargetActorState(inScene);
			inTransition.startTransition(false);
			
			delayTime = intervalTime;
			
			if(!isCrossed)
			{
				delayTime += outTransition.getTransitionTime();
			}
		}
		
		//ムーブ側
		public TransitionTask(IScene outScene, IScene inScene,
				IConstSceneMoveData moveData)
		{
			this.outScene = outScene;
			this.inScene = inScene;
			
//			outScene.getSnapAnimationState().setSnapTarget(null);
//			inScene.getSnapAnimationState().setSnapTarget(null);
			
			//
			this.isMoveTransition = true;
			this.isCurrentDestroyed = moveData.isCurrentSceneDestroyed();
			
			if(isMoveTransition)
			{
				outTransition = moveData.getOutTransition();
				inTransition = moveData.getInTransition();
			}
			else
			{
				outTransition = moveData.getInTransition();
				inTransition = moveData.getOutTransition();
			}
			
			outTransition.setTargetActorState(outScene);
			outTransition.startTransition(true);
			
					
			inTransition.setTargetActorState(inScene);
			inTransition.startTransition(false);
			
//			int outTime = outTransition.getTransitionTime();
//			int inTime = inTransition.getTransitionTime();
			int intervalTime = moveData.getTransitionInterval();
			
			delayTime = intervalTime;
			
			if(!moveData.isTransitionCrossed())
			{
				delayTime += outTransition.getTransitionTime();
			}
		}
		
		@Override
		public void update()
		{
			
			if(outTransition.advanceTransition())
			{
				outScene.update();
			}
			
			if(currentCount >= delayTime)
			{
				if(inTransition.advanceTransition())
				{
					inScene.update();
					
					//トランジションが完了する前にトランジション先がさらに遷移予約された時
					//残りのトランジションアニメを１フレーム中に消化してスキップする
					if(inScene.getSceneMoveState() != SceneMoveState.CONTINUE)
					{
						//不恰好すぎるけど再起ループも怖い。
						while(true)
						{
							if(outTransition.advanceTransition())
							{
								outScene.update();
							}
							
							if(inTransition.advanceTransition())
							{
								inScene.update();
							}
							
							if(!outTransition.isTransitionAdvancing() &&
									!inTransition.isTransitionAdvancing())
							{
								break;
							}
						}
					}
				}
			}
			
			
			if(!outTransition.isTransitionAdvancing() && !inTransition.isTransitionAdvancing())
			{
				if(isMoveTransition)
				{
					if(isCurrentDestroyed)
					{
						chainPrevScene = chainPrevScene.getChainPrevScene();
					}
				}
				else
				{
					currentScene = chainPrevScene.getCurrentScene();
					chainPrevScene = chainPrevScene.getChainPrevScene();	
				}
				
				currentTask = new NormalTask();
				return;
			}
									
			currentCount++;
			
			//
		}
	}

	//
	abstract protected void onFinishScene();
	
	//
	//設定した最初の遷移の効果を適用するために、
	//最初に代入されるダミーのシーン。
	private final class DummyScene
	extends AbstractParentScene
	{
		protected DummyScene(IApplicationContext context)
		{
			super(context);
		}

		@Override
		public void startScene(IConstObjectBox args)
		{
		}

		@Override
		public void resumeScene(int registerdID, int resultCode,
				IConstObjectBox resultValues)
		{
			finishScene();
			//ここがfinishなのでここに到達するとそのまま終了される。
		}

//		@Override
//		public void onResizeScene(Vector2f newSize)
//		{
//		}
		
		@Override
		protected void onUpdateSceneChildren()
		{
		}

		@Override
		protected void onDrawChildren(RenderTarget target, RenderStates states,
				IConstColorMaskState mask)
		{
		}

//		@Override
//		protected void onUpdate()
//		{
//		}		
		@Override
		protected void onUpdateScene()
		{
		}
		
		//
		@Override
		public boolean moveNextScene(IConstSceneMoveData moveData)
		{
			return super.moveNextScene(moveData);
		}
	}
}