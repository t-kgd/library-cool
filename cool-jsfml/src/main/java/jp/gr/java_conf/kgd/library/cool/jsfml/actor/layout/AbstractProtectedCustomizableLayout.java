package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.container.AbstractActorContainer;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.ILayoutManager;

public class AbstractProtectedCustomizableLayout
extends AbstractActorContainer
implements IContainerLayout
{
	private ILayoutManager layoutManager;
	
	//
	protected AbstractProtectedCustomizableLayout()
	{
	}


	//
	protected ILayoutManager getLayoutManager()
	{
		return layoutManager;
	}

	protected void setLayoutManager(ILayoutManager layoutManager)
	{
		this.layoutManager = layoutManager;
	}
	
	//
	protected void onUpdate()
	{
		if(layoutManager != null)
		{
			layoutManager.applyLayoutToComponents(getActors(), getSize());
		}
	}
}
