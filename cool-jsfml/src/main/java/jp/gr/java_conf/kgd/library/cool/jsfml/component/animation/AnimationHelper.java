package jp.gr.java_conf.kgd.library.cool.jsfml.component.animation;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IDrawState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.blink.IConstBlinkAnimationState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.shake.IConstShakeAnimationState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.animation.snap.IConstSnapAnimationState;
import jp.gr.java_conf.kgd.library.cool.util.math.MathHelper;

public class AnimationHelper
{
	public static final void applyShakeAnimation(IDrawState target, IConstShakeAnimationState animation)
	{
		DrawStateElement element;
			
		element = DrawStateElement.ROTATION;
		if(animation.isEnabled(element))
		{
			float shake = animation.getRotation();
			float factor = animation.getCurrentShakeFactor(element);
			
			float current = target.getRotation();
					
			target.setRotation(current + shake * factor); 
			
//			animation.advanceShakeFactor(element);
		}
		
		element = DrawStateElement.SCALE;
		if(animation.isEnabled(element))
		{
			Vector2f shake = animation.getScale();
			float factor = animation.getCurrentShakeFactor(element);
			
			Vector2f current = target.getScale();
					
			target.setScale(current.x + shake.x * factor, current.y + shake.y * factor);
			
//			animation.advanceShakeFactor(element);
		}
		
		element = DrawStateElement.POSITION;
		if(animation.isEnabled(element))
		{
			Vector2f shake = animation.getPosition();
			float factor = animation.getCurrentShakeFactor(element);
			
			Vector2f current = target.getPosition();
					
			target.setPosition(current.x + shake.x * factor, current.y + shake.y * factor);
			
//			animation.advanceShakeFactor(element);
		}
		
		element = DrawStateElement.COLOR_MASK;
		if(animation.isEnabled(element))
		{
			Color shake = animation.getColorMask();
			float factor = animation.getCurrentShakeFactor(element);
			
			Color current = target.getColorMask();
				
			target.setColorMask(Color.add(current, Color.mul(shake, factor)));
			
//			animation.advanceShakeFactor(element);
		}
		
		element = DrawStateElement.ALPHA_MASK;
		if(animation.isEnabled(element))
		{
			float shake = animation.getAlphaMaskRate();
			float factor = animation.getCurrentShakeFactor(element);
			
			float current = target.getAlphaMaskRate();
					
			target.setAlphaMaskRate(current + shake * factor); 
			
//			animation.advanceShakeFactor(element);
		}
	}
	
	public static final void applySnapAnimation(IDrawState target, IConstSnapAnimationState animation)
	{
		if(animation.getSnapTarget() == null)
		{
			return;
		}
		
		DrawStateElement element;
			
		element = DrawStateElement.ROTATION;
		if(animation.isEnabled(element))
		{
			float snap = animation.getRotation();
			float speed = animation.getSpeedRate(element);
			
			float current = target.getRotation();
			
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
				target.setRotation(snap);
			}
			else
			{
				target.rotate(diff);
			}
		}
		
		element = DrawStateElement.SCALE;
		if(animation.isEnabled(element))
		{
			Vector2f snap = animation.getScale();
			float speed = animation.getSpeedRate(element);
			
			Vector2f current = target.getScale();
					
			
			float diffX = (snap.x - current.x) * speed;
			float diffY = (snap.y - current.y) * speed;
			
			if(MathHelper.isZero(diffX) && MathHelper.isZero(diffY))
			{
				target.setScale(snap);
			}
			else
			{
				target.scale(1 + diffX, 1 + diffY);
			}
		}
		
		element = DrawStateElement.POSITION;
		if(animation.isEnabled(element))
		{
			Vector2f snap = animation.getPosition();
			float speed = animation.getSpeedRate(element);
			
			Vector2f current = target.getPosition();
					
			
			float diffX = (snap.x - current.x) * speed;
			float diffY = (snap.y - current.y) * speed;
			
			if(MathHelper.isZero(diffX) && MathHelper.isZero(diffY))
			{
				target.setPosition(snap);
			}
			else
			{
				target.move(diffX, diffY);
			}
		}
		
		
		element = DrawStateElement.COLOR_MASK;
		if(animation.isEnabled(element))
		{
			Color snap = animation.getColorMask();
			float speed = animation.getSpeedRate(element);
			
			Color current = target.getColorMask();
			
			int diffR = (int) ((snap.r - current.r) * speed);
			int diffG = (int) ((snap.g - current.g) * speed);
			int diffB = (int) ((snap.b - current.b) * speed);
			int diffA = (int) ((snap.a - current.a) * speed);
			
			if(diffR == 0 && 
					diffG == 0 &&
					diffB == 0 &&
					diffA == 0)
			{
				target.setColorMask(snap);
			}
			else
			{
				target.setColorMask(new Color(
						current.r + diffR,
						current.g + diffG,
						current.b + diffB,
						current.a + diffA));
			}
		}
		
		element = DrawStateElement.ALPHA_MASK;
		if(animation.isEnabled(element))
		{
			float snap = animation.getAlphaMaskRate();
			float speed = animation.getSpeedRate(element);
			
			float current = target.getAlphaMaskRate();
					
			
			float diff = (snap - current) * speed;
			
			if(MathHelper.isZero(diff))
			{
				target.setAlphaMaskRate(snap);
			}
			else
			{
				target.setAlphaMaskRate(current + diff);
			}
		}
	}
	
	public static final void applyBlinkAnimation(IDrawState target, IConstBlinkAnimationState animation)
	{
		if(!animation.isBlinkEnabled())
		{
			return;
		}
		
		target.setAlphaMaskRate(animation.getCurrentBlinkFactor());
	}
}
