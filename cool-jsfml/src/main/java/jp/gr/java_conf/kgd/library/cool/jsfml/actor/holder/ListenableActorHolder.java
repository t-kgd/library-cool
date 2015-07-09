package jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IOnUpdateListener;

public class ListenableActorHolder
extends AbstractSimpleActorHolder
implements IListenableActorHolder
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
	final protected void onUpdate()
	{
		if(listener != null)
		{
			listener.onUpdate();
		}
	}
}
