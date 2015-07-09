package jp.gr.java_conf.kgd.library.cool.jsfml.actor.view;

import java.util.Objects;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.EmptyActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.RectangleActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public abstract class AbstractProtectedView
extends AbstractParentActor
implements IActor
{
	//これがビュー内に表示する中身。初期値はダミー。
	private IActor innerView = new EmptyActor();
	
	//実際にはコレが枠にはまっており、中身の世界を描画したテクスチャを貼る。
	private RectangleActor viewBuffer = new ViewBuffer();
	
	
	//カメラの動作に制限を加える。
	//　カメラの拡縮と回転が不可になり、移動時に内部の大きさ以上にはみ出ることがなくなる。
	//　内側の世界の大きさが枠側の大きさより小さくならないようにする。
	private boolean isViewControllLimited = false;
	
	
	//
	//フラグと連動したインナークラスのインスタンス。
	private InnerDrawState cameraState = new InnerDrawState();

	private boolean isViewOriginKeepedCenter = true;
	
	
	//これに中身の世界を描画する。
	//再生成が重い（かつ、たまにエラーになる）のでリサイズは慎重にする。
	private RenderTexture renderTexture;
	private boolean isNeedsTextureRect = false;
	
	
	
	
	private boolean isRenderTextureNeedsResize = true;
	
	//	
	protected AbstractProtectedView()
	{
		this(new RenderTexture());
	}
	
	private AbstractProtectedView(RenderTexture renderTexture)
	{
		this.renderTexture = renderTexture;
		

		//ダミーなので見えなくする。
		innerView.setVisible(false);

		//デフォルトでアンチエイリアス無効なので有効にしておく。
		//（拡縮や回転が多くなると思われるので有効の方が良いはず）
		renderTexture.setSmooth(true);
	}

	
	//
//	//これとsetのオーバーライドで大きく仕組みが変わるので注意。
//	@Override
	protected IActor getInnerActor()
	{
		return this.innerView;
	}
	
//	//親のメソッドに転送せず、独自に実装する。
//	@Override
	protected void setInnerActor(IActor actor)
	{
		Vector2f size = innerView.getSize();
		
		this.innerView = Objects.requireNonNull(actor);
		
		ComponentHelper.resetAffineState(this.innerView);

		this.setInnerSize(size);
	}
	//
	
	//
	@Override
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		
		checkLimitedState();
		
		if(isViewOriginKeepedCenter)
		{
			cameraState.setOriginToCenter();
		}
		
		isRenderTextureNeedsResize = true;
	}
	
	//	
	
	protected Vector2f getViewPosition()
	{
		return cameraState.getPosition();
		
//		return cameraState.getOrigin();
	}
	
	
	protected Vector2f getViewOrigin()
	{
		return cameraState.getOrigin();
		
//		return cameraState.getPosition();
	}
	
	
	protected boolean isViewOriginKeepedCenter()
	{
		return isViewOriginKeepedCenter;
	}
	
	
	protected void setViewPosition(Vector2f position)
	{
		cameraState.setPosition(position);
	}
	
	
	protected void setViewPosition(float x, float y)
	{
		this.setViewPosition(new Vector2f(x, y));
	}
	
	
	protected void setViewOrigin(Vector2f origin)
	{
		cameraState.setOrigin(origin);
	}
	
	
	protected void setViewOrigin(float x, float y)
	{
		this.setViewOrigin(new Vector2f(x, y));
	}
	
	
	protected void setViewOriginKeepedCenter(boolean flag)
	{
		this.isViewOriginKeepedCenter = flag;
		
		if(flag)
		{
			cameraState.setOriginToCenter();
		}
	}
	
	
	protected void moveViewPosition(Vector2f diff)
	{
		this.setViewPosition(Vector2f.add(getViewPosition(), diff));
	}
	
	
	protected void moveViewPosition(float x, float y)
	{
		this.moveViewPosition(new Vector2f(x, y));
	}
	
	
	protected boolean isViewControllLimited()
	{
		return isViewControllLimited;
	}
	
	protected void setViewControllLimited(boolean flag)
	{
		this.isViewControllLimited = flag;
		
		if(flag)
		{
			checkLimitedState();
		}
	}
	
	
	protected IDrawState getCameraState()
	{
		return cameraState;
	}
	
	
	protected Vector2f getInnerSize()
	{
		return innerView.getSize();
	}
	
	protected void setInnerSize(Vector2f size)
	{		
//		innerView.setSize(size);		
		if(!ComponentHelper.setSizeIfNeedsUpdate(innerView, size))
		{
			return;
		}
		
		if(isViewControllLimited)
		{
			checkLimitedState();
		}
	}
	
	protected void setInnerSize(float width, float height)
	{
		this.setInnerSize(new Vector2f(width, height));
	}
	
	
	//
	@Override
	protected void onUpdate()
	{
		if(isRenderTextureNeedsResize)
		{
			if(!getSize().equals(Vector2f.ZERO))
			{
				isRenderTextureNeedsResize = false;

				resizeRenderTexture();
			}
		}
	}
	
	//ここでオーバーライドしないと、親側で中身が更新される。
//	@Override
//	final protected void updateInnerActor()
//	{
//		ComponentHelper.setSizeIfNeedsUpdate(viewBuffer, getSize());
//		viewBuffer.update();
//	}
	
	@Override
	final protected void onUpdateChildren()
	{
		ComponentHelper.setSizeIfNeedsUpdate(viewBuffer, getSize());
		viewBuffer.update();
	}
	
	@Override
	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		viewBuffer.draw(target, states, mask);
	}
	
	
	//
	private static final int TEXTURE_RESIZE_CONDITION_RATIO = 4;
	
	private final void resizeRenderTexture()
	{	
		Vector2f outerSize = getSize();
		
		Vector2i maxSize = renderTexture.getSize();
		Vector2i minSize = Vector2i.div(maxSize, TEXTURE_RESIZE_CONDITION_RATIO);
		
		if(outerSize.x < maxSize.x && outerSize.y < maxSize.y)
		{
			if(minSize.x < outerSize.x && minSize.y < outerSize.y)
			{
				isNeedsTextureRect = true;
				
				return;
			}
		}
		
		isNeedsTextureRect = false;
		
		try
		{
			renderTexture.create((int)outerSize.x, (int) outerSize.y);
		}
		catch (TextureCreationException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			return;
		}
	}
	
	
	//	
	private final void checkLimitedState()
	{		
		if(isViewControllLimited)
		{
			Vector2f outerSize = this.getSize();
			Vector2f innerSize = innerView.getSize();
			
			if(outerSize.x > innerSize.x || outerSize.y > innerSize.y)
			{
				innerView.setSize(
						Math.max(outerSize.x, innerSize.x),
						Math.max(outerSize.y, innerSize.y));
				
				cameraState.setPositionToInner();
			}

			cameraState.setRotation(0);
			cameraState.setScale(1, 1);
		}
	}
	
	
	//
	private final class InnerDrawState extends DrawState
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -808851147614155250L;

		@Override
		public void setOrigin(Vector2f v)
		{
			if(isViewOriginKeepedCenter)
			{
				setOriginToCenter();
			}
			else
			{
				super.setOrigin(v);
				
				if(isViewControllLimited)
				{
					setPositionToInner();
				}
			}
		}
		
		public void setOriginToCenter()
		{
			super.setOrigin(Vector2f.div(AbstractProtectedView.this.getSize(), 2));
						
			if(isViewControllLimited)
			{
				setPositionToInner();
			}
		}
		
		//
		@Override
		public void setPosition(Vector2f v)
		{
			if(isViewControllLimited)
			{
				setPositionToInner(v);
			}
			else
			{
				super.setPosition(v);
			}
		}
		
		public void setPositionToInner(Vector2f position)
		{
			Vector2f outerSize = AbstractProtectedView.this.getSize();
			Vector2f innerSize = innerView.getSize();
			
			Vector2f origin = getOrigin();
			
			//Vector2fで渡さないと無限ループになる。(親側オーバーロードが委譲形式のため)	
			super.setPosition(new Vector2f(
					MathHelper.minMax(origin.x, position.x, innerSize.x - (outerSize.x - origin.x)),
					MathHelper.minMax(origin.y, position.y, innerSize.y - (outerSize.y - origin.y))));
		}
		
		final public void setPositionToInner()
		{
			setPositionToInner(getPosition());
		}
		
		
		//
		@Override
		public void setRotation(float angle)
		{
			if(isViewControllLimited)
			{
				return;
			}
			
			super.setRotation(angle);
		}
		
		@Override
		public void setScale(Vector2f factors)
		{
			if(isViewControllLimited)
			{
				return;
			}
			
			super.setScale(factors);
		}
	}
	
	private final class ViewBuffer extends RectangleActor
	{
		@Override
		protected void onUpdate()
		{
			super.onUpdate();
			
			if(isViewControllLimited)
			{
				ActorHelper.updateIgnoreAnimation(innerView);
			}
			else
			{
				innerView.update();
			}
		}
		
		@Override
		protected void onDraw(RenderTarget target, RenderStates states,
				IConstColorMaskState mask)
		{			
			if(!innerView.isVisible())
			{
				return;
			}
			
			Vector2f outerSize = getSize();
			if(outerSize.equals(Vector2f.ZERO))
			{
				return;
			}
			
			RenderStates states2 = new RenderStates(cameraState.getInverseTransform());
			
			renderTexture.clear();
			innerView.draw(renderTexture, states2, cameraState);
			renderTexture.display();
			
			this.setTexture(renderTexture.getTexture(), !isNeedsTextureRect);
			
			if(isNeedsTextureRect)
			{
				this.setTextureRect(new IntRect(Vector2i.ZERO, new Vector2i(getSize())));
			}
			
			super.onDraw(target, states, mask);
		}
	}
}
