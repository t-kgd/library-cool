package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class SnapAnimationManager
extends SnapAnimationState
implements ISnapAnimationManager
{
	@Override
	public void applyAnimation(IDrawState state)
	{
		if(getSnapTarget() == null)
		{
			return;
		}
		
		DrawStateElement element;
			
		element = DrawStateElement.ROTATION;
		if(isEnabled(element))
		{
			float snap = getRotation();
			float speed = getSpeedRate(element);
			
			float current = state.getRotation();
			
			float diff = (snap - current) ;
			
			
			//ライブラリ側で統一されていないので統一。
			// BasicTransformable:rotationが0~360
			// Shape系:rotaitonに制限がない(負数含む)
			snap = MathHelper.modToPlus(snap, MathHelper.PI2_DEGREE);
			current = MathHelper.modToPlus(current, MathHelper.PI2_DEGREE);
			
			
			if(diff > MathHelper.PI_DEGREE)
			{
				//左回りにしたい。
				diff = -((MathHelper.PI2_DEGREE - snap) + current);
			}
			else if(diff < -MathHelper.PI_DEGREE)
			{
				//右回りにしたい。
				diff = (MathHelper.PI2_DEGREE - current) + snap;
			}
			
			diff *= speed;
			
			if(MathHelper.isZero(diff))
			{
				state.setRotation(snap);
			}
			else
			{
				state.rotate(diff);
			}
		}
		
		element = DrawStateElement.SCALE;
		if(isEnabled(element))
		{
			Vector2f snap = getScale();
			float speed = getSpeedRate(element);
			
			Vector2f current = state.getScale();
					
			
			float diffX = (snap.x - current.x) * speed;
			float diffY = (snap.y - current.y) * speed;
			
			if(MathHelper.isZero(diffX) && MathHelper.isZero(diffY))
			{
				state.setScale(snap);
			}
			else
			{
				state.scale(1 + diffX, 1 + diffY);
			}
		}
		
		element = DrawStateElement.POSITION;
		if(isEnabled(element))
		{
			Vector2f snap = getPosition();
			float speed = getSpeedRate(element);
			
			Vector2f current = state.getPosition();
					
			
			float diffX = (snap.x - current.x) * speed;
			float diffY = (snap.y - current.y) * speed;
			
			if(MathHelper.isZero(diffX) && MathHelper.isZero(diffY))
			{
				state.setPosition(snap);
			}
			else
			{
				state.move(diffX, diffY);
			}
		}
		
		
		element = DrawStateElement.COLOR_MASK;
		if(isEnabled(element))
		{
			Color snap = getColorMask();
			float speed = getSpeedRate(element);
			
			Color current = state.getColorMask();
			
			int diffR = (int) ((snap.r - current.r) * speed);
			int diffG = (int) ((snap.g - current.g) * speed);
			int diffB = (int) ((snap.b - current.b) * speed);
			int diffA = (int) ((snap.a - current.a) * speed);
			
			if(diffR == 0 && 
					diffG == 0 &&
					diffB == 0 &&
					diffA == 0)
			{
				state.setColorMask(snap);
			}
			else
			{
				state.setColorMask(new Color(
						current.r + diffR,
						current.g + diffG,
						current.b + diffB,
						current.a + diffA));
			}
		}
		
		element = DrawStateElement.ALPHA_MASK;
		if(isEnabled(element))
		{
			float snap = getAlphaMaskRate();
			float speed = getSpeedRate(element);
			
			float current = state.getAlphaMaskRate();
					
			
			float diff = (snap - current) * speed;
			
			if(MathHelper.isZero(diff))
			{
				state.setAlphaMaskRate(snap);
			}
			else
			{
				state.setAlphaMaskRate(current + diff);
			}
		}
	}
}
