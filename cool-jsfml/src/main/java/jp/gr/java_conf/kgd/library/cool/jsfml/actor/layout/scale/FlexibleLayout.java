package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.ActorHolder;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;

public class FlexibleLayout
extends ActorHolder
implements IFlexibleLayout
{
	private Vector2f innerScale = ComponentHelper.VECTOR_IDENTITY;
//	private Vector2f innerOriginRatio = ComponentHelper.VECTOR_HALF_RATE;

	@Override
	public Vector2f getInnerScale()
	{
		return innerScale;
	}

	@Override
	public void setInnerScale(Vector2f scale)
	{
		this.innerScale = scale;
	}

	@Override
	final public void setInnerScale(float x, float y)
	{
		this.setInnerScale(new Vector2f(x, y));
	}
	
//	@Override
//	public Vector2f getInnerOriginRatio()
//	{
//		return innerOriginRatio;
//	}
//	
//	@Override
//	public void setInnerOriginRatio(Vector2f originRatio)
//	{
//		this.innerOriginRatio = originRatio;
//	}
//	
//	@Override
//	final public void setInnerOriginRatio(float x, float y)
//	{
//		this.setInnerOriginRatio(new Vector2f(x, y));
//	}

	@Override
	protected void onUpdateInnerActor()
	{
		Vector2f outerSize = getSize();
		Vector2f outerOrigin = Vector2f.div(outerSize, 2);
		
		Vector2f innerSize = new Vector2f(outerSize.x * innerScale.x, outerSize.y * innerScale.y);
		
		IActor inner = getInnerActor();
		ComponentHelper.setFitSizeAndOriginToCenter(inner, innerSize);
		inner.setPosition(outerOrigin);
		
		inner.update();
	}
}
