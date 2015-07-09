package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.ILayoutManager;

public interface ILineLayoutManager
extends ILayoutManager, ILineLayoutState
{
	IConstLineLayoutCache getLayoutCache();
}
