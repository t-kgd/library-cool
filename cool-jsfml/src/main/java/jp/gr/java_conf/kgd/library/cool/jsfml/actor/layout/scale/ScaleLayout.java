package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.ActorHolder;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;

import org.jsfml.system.Vector2f;

public class ScaleLayout
extends ActorHolder
implements IScaleLayout
{
	private Vector2f innerScale = ComponentHelper.VECTOR_IDENTITY;
	private Vector2f innerOriginRatio = ComponentHelper.VECTOR_HALF_RATE;

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
	
	@Override
	public Vector2f getInnerScaleOrigin()
	{
		return innerOriginRatio;
	}
	
	@Override
	public void setInnerScaleOrigin(Vector2f originRatio)
	{
		this.innerOriginRatio = originRatio;
	}
	
	@Override
	final public void setInnerScaleOrigin(float x, float y)
	{
		this.setInnerScaleOrigin(new Vector2f(x, y));
	}

	@Override
	protected void onUpdateInnerActor()
	{
		Vector2f outerSize = getSize();
	
		IActor inner = getInnerActor();
		
		ComponentHelper.setSizeIfNeedsUpdate(inner, outerSize);
		
		inner.setPosition(Vector2f.ZERO);
		inner.setOrigin(Vector2f.ZERO);
		ComponentHelper.setOriginAndAdjustMove(inner, outerSize.x * innerOriginRatio.x, outerSize.y * innerOriginRatio.y);

		inner.setScale(innerScale);
		
		
		inner.update();
	}
}
