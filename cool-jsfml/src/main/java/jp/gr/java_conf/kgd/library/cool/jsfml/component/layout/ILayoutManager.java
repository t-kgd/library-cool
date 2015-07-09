package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout;

import java.util.Collection;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponentState;

public interface ILayoutManager
{
	void applyLayoutToComponents(Collection<? extends IComponentState> states, Vector2f layoutSize);
	
	IConstLayoutCache getLayoutCache();
}