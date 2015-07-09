package jp.gr.java_conf.kgd.library.cool.jsfml.application;

import java.util.Objects;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;

/**
 * アプリケーションコンテキストを持つアクターです。
 * @author taisa
 *
 */
public abstract class AbstractApplicationActor
extends AbstractParentActor
implements IApplicationActor
{
	private IApplicationContext context;
	
	public AbstractApplicationActor()
	{
	}
	
	public AbstractApplicationActor(IApplicationContext context)
	{
		this.context = context;
	}
	
	public AbstractApplicationActor(IApplicationActor other)
	{
		this(other.getApplicationContext());
	}
	
	@Override
	public IApplicationContext getApplicationContext()
	{
		return context;
	}
	
	@Override
	public void setApplicationContext(IApplicationContext context)
	{
		this.context = Objects.requireNonNull(context);
	}
}
