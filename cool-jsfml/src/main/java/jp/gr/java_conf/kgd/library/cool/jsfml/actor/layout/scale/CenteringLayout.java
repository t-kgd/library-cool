package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.ActorHolder;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;

import org.jsfml.system.Vector2f;

public class CenteringLayout
extends ActorHolder
implements ICenteringLayout
{
	private Vector2f innerSize = Vector2f.ZERO;

	@Override
	public Vector2f getInnerSize()
	{
		return this.innerSize;
	}

	@Override
	public void setInnerSize(Vector2f size)
	{
		this.innerSize = size;
	}

	@Override
	final public void setInnerSize(float width, float height)
	{
		this.setInnerSize(new Vector2f(width, height));
	}

	@Override
	protected void onUpdateInnerActor()
	{
		IActor inner = getInnerActor();
		ComponentHelper.setSizeIfNeedsUpdate(inner, innerSize);
		ComponentHelper.setOriginToCenter(inner);

		//
		Vector2f outerSize = getSize();		
		inner.setPosition(outerSize.x / 2, outerSize.y / 2);
		
		inner.update();
	}
}