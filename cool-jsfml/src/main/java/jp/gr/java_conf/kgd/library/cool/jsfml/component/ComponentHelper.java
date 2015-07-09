package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public final class ComponentHelper
{
	public static final Vector2f VECTOR_IDENTITY = new Vector2f(1, 1);
	public static final Vector2f VECTOR_HALF_RATE = new Vector2f(0.5f, 0.5f);
	
	public static final ImmutableComponentState DEFAULT_COMPONENT_STATE =
			new ImmutableComponentState(new ComponentState());
	
	//
	protected ComponentHelper()
	{
	}
	
	
	//Affine
	public static final void resetAffineState(IAffineState state)
	{
		state.setOrigin(0, 0);
		
		state.setRotation(0);
		state.setScale(1, 1);
		state.setPosition(0, 0);
	}
	
	public static final void copyAffineState(IAffineState destination, IConstAffineState source)
	{
		destination.setOrigin(source.getOrigin());
		
		destination.setRotation(source.getRotation());
		destination.setScale(source.getScale());
		destination.setPosition(source.getPosition());
	}
	
	public static final void applyParentAffineState(IAffineState child, IConstAffineState parent)
	{
		child.setPosition(parent.getTransform().transformPoint(child.getPosition()));
		child.rotate(parent.getRotation());
		child.scale(parent.getScale());
	}
	
	
	
	//mask_color
	public static final void resetColorMaskState(IColorMaskState state)
	{
		state.setColorMask(Color.WHITE);
		state.setAlphaMaskRate(1);
	}
	
	public static final void copyColorMaskState(IColorMaskState destination, IConstColorMaskState source)
	{
		destination.setColorMask(source.getColorMask());
		destination.setAlphaMaskRate(source.getAlphaMaskRate());
	}
	
	public static final void applyParentColorMaskState(IColorMaskState child, IConstColorMaskState parent)
	{
		child.setColorMask(Color.mul(parent.getColorMask(), child.getColorMask()));
		child.setAlphaMaskRate(parent.getAlphaMaskRate() * child.getAlphaMaskRate());
	}
	
	
	public static final IColorMaskState combineColorMaskState(IConstColorMaskState base, IConstColorMaskState append)
	{		
		Color color = Color.mul(base.getColorMask(), append.getColorMask());
		float alphaRate = base.getAlphaMaskRate() * append.getAlphaMaskRate();
		
		return new ColorMaskState(color, alphaRate);
		
//		return new ColorMaskState(
//				Color.mul(base.getColorMask(), append.getColorMask()),
//				base.getAlphaMaskRate() * append.getAlphaMaskRate());
	}
	
	
	public static final boolean isColorMaskTransparent(IConstColorMaskState mask)
	{
		return mask.getColorMask().a <= 0 || mask.getAlphaMaskRate() <= 0;
	}
	
	public static final Color getBlendColor(Color color, float alphaRate)
	{
		if(alphaRate >= 1)
		{
			return color;
		}
		
		int blendAlpha = (int) (color.a * alphaRate);
		
//		if(blendAlpha <= 0)
//		{
//			return Color.TRANSPARENT;
//		}
		
		return new Color(color, blendAlpha);
	}
	
	public static final Color getBlendColor(IConstColorMaskState mask)
	{		
		return getBlendColor(mask.getColorMask(), mask.getAlphaMaskRate());
	}
	
	public static final Color getBlendColor(IConstColorMaskState base, IConstColorMaskState append)
	{
		return getBlendColor(combineColorMaskState(base, append));
		
		
//		return Color.mul(getBlendColor(base), getBlendColor(append));
	}
	
	
	
	
	
	
	//draw
	public static final void resetDrawState(IDrawState state)
	{
		resetAffineState(state);
		resetColorMaskState(state);
		
//		state.setVisible(true);
	}
	
	public static final void copyDrawState(IDrawState destination, IConstDrawState source)
	{
		copyAffineState(destination, source);
		copyColorMaskState(destination, source);
		
//		destination.setVisible(source.isVisible());
	}
	
	public static final void applyParentDrawState(IDrawState child, IConstDrawState parent)
	{
		applyParentAffineState(child, parent);
		applyParentColorMaskState(child, parent);
		
//		child.setVisible(child.isVisible() && parent.isVisible());
	}
	
	
	
	//rectangle
	public static final void resetRectangleState(IRectangleState state)
	{
		state.setPosition(Vector2f.ZERO);
		state.setOrigin(Vector2f.ZERO);
		state.setSize(Vector2f.ZERO);
	}
	
	public static final void copyRectangleState(IRectangleState destination, IConstRectangleState source)
	{
		destination.setPosition(source.getPosition());
		destination.setOrigin(source.getOrigin());
		destination.setSize(source.getSize());
	}
	
	
	
	
	
	
	//component
	public static final void resetComponentState(IComponentState state)
	{
		resetDrawState(state);
		
		state.setSize(Vector2f.ZERO);
	}
	
	public static final void copyComponentState(IComponentState destination, IConstComponentState source)
	{
		copyDrawState(destination, source);
		
		Vector2f size = source.getSize();
		if(destination.getSize().equals(size))
		{
			return;
		}
		
		destination.setSize(size);
	}
	
	
	public static final Vector2f getLocalCenter(IConstComponentState state)
	{
		Vector2f size = state.getSize();
		
		return Vector2f.div(size, 2);
	}
	
	public static final Vector2f getGrobalCenter(IConstComponentState state)
	{
		Vector2f position = state.getPosition();
		Vector2f size = state.getSize();
		Vector2f origin = state.getOrigin();
		
		return new Vector2f(position.x + size.x / 2 - origin.x, position.y + size.y / 2 - origin.y);
	}
	
	public static final void setOriginAndAdjustMove(IComponentState state, Vector2f toOrigin)
	{
		Vector2f fromOrigin = state.getOrigin();
		
		float diffX = toOrigin.x - fromOrigin.x;
		float diffY = toOrigin.y - fromOrigin.y;
		
		state.setOrigin(toOrigin);
		state.move(diffX, diffY);
	}
	
	public static final void setOriginAndAdjustMove(IComponentState state, float toOriginX, float toOriginY)
	{
		setOriginAndAdjustMove(state, new Vector2f(toOriginX, toOriginY));
	}
	
	public static final void setOriginToCenter(IComponentState state)
	{
		state.setOrigin(getLocalCenter(state));
	}
	
	public static final void setOriginToCenterAndAdjustMove(IComponentState state)
	{
		setOriginAndAdjustMove(state, getLocalCenter(state));
	}
	
	public static final boolean setSizeIfNeedsUpdate(IComponentState state, Vector2f size)
	{
		if(state.getSize().equals(size))
		{
			return false;
		}
		else
		{
			state.setSize(size);
			
			return true;
		}
	}
	
	public static final boolean setSizeIfNeedsUpdate(IComponentState state,
			float width, float height)
	{
		Vector2f size = state.getSize();
		
		if(MathHelper.equals(width, size.x) && MathHelper.equals(height, size.y))
		{
			return false;
		}
		else
		{
			state.setSize(width, height);
			
			return true;
		}
	}
	
	//
	/***
	 * 指定したサイズの親に当てはまるように状態を設定します。
	 * なので、指定したサイズを親とみなし、その中心を設定し、同時にローカル座標も当該位置に設定します。
	 * @param state
	 * @param size
	 */
	public static final void setFitSizeAndOriginToCenter(IComponentState state, Vector2f size)
	{
		setSizeIfNeedsUpdate(state, size);
		
		Vector2f center = Vector2f.div(size, 2);
		state.setPosition(center);
		state.setOrigin(center);
	}
	
	public static final void setFitSizeAndOriginToCenter(IComponentState state, IConstDimensionState base)
	{
		setFitSizeAndOriginToCenter(state, base.getSize());
	}
	
	/***
	 * 描画状態をリセットし、完全に親のサイズに当てはめます。
	 * @param state
	 * @param size
	 */
	public static final void setStrictFixAndOriginToCenter(IComponentState state, Vector2f size)
	{
		resetDrawState(state);
		
		setFitSizeAndOriginToCenter(state, size);
	}
	
	public static final void setStrictFixAndOriginToCenter(IConstDimensionState base, IComponentState state)
	{
		setStrictFixAndOriginToCenter(state, base.getSize());
	}
	
	
	//
	public static final RenderStates combineRenderStates(RenderStates states, IConstAffineState affine)
	{
		return new RenderStates(states, Transform.combine(states.transform, affine.getTransform()));
	}
	
	public static final void drawChildComponent(RenderTarget target, RenderStates states,
			IConstColorMaskState mask, IConstComponentState parentState, IDrawable child)
	{
		RenderStates states2 = combineRenderStates(states, parentState);
		IConstColorMaskState mask2 = combineColorMaskState(mask, parentState);
		
		child.draw(target, states2, mask2);
	}
}
