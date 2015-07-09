package jp.gr.java_conf.kgd.library.cool.jsfml.actor.container;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IOnUpdateListener;

public class ListenableActorContainer
extends AbstractActorContainer
implements IListenableActorContainer
{
	private IOnUpdateListener listener;

	@Override
	public IOnUpdateListener getOnUpdateListener()
	{
		return listener;
	}

	@Override
	public void setOnUpdateListener(IOnUpdateListener listener)
	{
		this.listener = listener;
	}

	@Override
	protected void onUpdate()
	{
		listener.onUpdate();
	}
}
