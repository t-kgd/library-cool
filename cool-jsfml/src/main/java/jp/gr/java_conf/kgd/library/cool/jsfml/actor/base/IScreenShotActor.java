package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import org.jsfml.graphics.RenderTexture;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponent;

public interface IScreenShotActor
extends IRectangleActor
{
	void printScreen(IComponent component);
	
	RenderTexture getRenderTexture();
	void setRenderTexture(RenderTexture renderTexture);
}
