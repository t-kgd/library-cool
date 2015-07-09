package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.stack;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.AbstractProtectedCustomizableLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.stack.StackLayoutManager;

public class StackLayout
extends AbstractProtectedCustomizableLayout
implements IStackLayout
{
	private StackLayoutManager stackLayoutManager = new StackLayoutManager();
	
	public StackLayout()
	{
		setLayoutManager(stackLayoutManager);
	}
}
