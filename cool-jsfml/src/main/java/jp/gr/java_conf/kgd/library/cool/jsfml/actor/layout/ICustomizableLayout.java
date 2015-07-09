package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.ILayoutManager;

public interface ICustomizableLayout
extends IContainerLayout
{
	ILayoutManager getLayoutManager();
	void setLayoutManager(ILayoutManager layoutManager);
}
