package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.scale;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.ActorHolder;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;

public class KeepInnerWorldLayout
extends ActorHolder
implements IKeepInnerWorldLayout
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
		setInnerSize(new Vector2f(width, height));
	}

	@Override
	protected void onUpdateInnerActor()
	{
		IActor inner = getInnerActor();
		ComponentHelper.setFitSizeAndOriginToCenter(inner, innerSize);
	
		Vector2f outerSize = getSize();
		
		float scaleX = outerSize.x / innerSize.x;
		float scaleY = outerSize.y / innerSize.y;
		
		inner.setScale(scaleX, scaleY);
		
		inner.update();
	}
}
