package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public interface IDrawable
{
	void draw(RenderTarget target, RenderStates states, IConstColorMaskState mask);
}
