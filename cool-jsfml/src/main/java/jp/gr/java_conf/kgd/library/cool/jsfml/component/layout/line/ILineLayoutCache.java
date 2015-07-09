package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.ILayoutCache;

public interface ILineLayoutCache
extends IConstLineLayoutCache, ILayoutCache
{
	void setLineNumX(int num);
	void setLineNumY(int num);
	
	void setComponentSize(Vector2f size);
}
