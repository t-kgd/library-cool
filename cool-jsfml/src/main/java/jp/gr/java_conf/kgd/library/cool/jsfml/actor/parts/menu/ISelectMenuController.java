package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.menu;

public interface ISelectMenuController
extends IConstSelectMenuController
{
	void setSelectIndex(int index);
	void addSelectIndex(int diff);
}
