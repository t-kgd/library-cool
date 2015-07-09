package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IListenableActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IOnUpdateListener;

public class ListenableEmptyActor
extends AbstractEmptyActor
implements IListenableActor
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
