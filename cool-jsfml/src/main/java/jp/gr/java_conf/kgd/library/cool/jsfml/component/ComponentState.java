package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

public class ComponentState
extends DrawState
implements IComponentState
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3224792810072600973L;
	
	//
	private Vector2f size = Vector2f.ZERO;

	private boolean isVisible = true;
	
	//
	@Override
	public boolean isVisible()
	{
		return this.isVisible;
//		return this.isVisible && super.getAlphaMaskRate() > 0;
		//ここの仕様は悩みどころ。
	}
	
	@Override
	public void setVisible(boolean flag)
	{
		this.isVisible = flag;
	}
	
	@Override
	public Vector2f getSize()
	{
		return size;
	}

	@Override
	public FloatRect getLocalBounds()
	{
		return new FloatRect(Vector2f.ZERO, size);
	}

	@Override
	public FloatRect getGlobalBounds()
	{
		return new FloatRect(getPosition(), size);
	}

	@Override
	public void setSize(Vector2f size)
	{
		this.size = size;
	}
	
	@Override
	final public void setSize(float width, float height)
	{
		this.setSize(new Vector2f(width, height));
	}
}
