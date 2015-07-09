package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.IConstLayoutCache;

public interface IConstLineLayoutCache
extends IConstLayoutCache
{
	int getLineNumX();
	int getLineNumY();
	
	Vector2f getComponentSize();
}
