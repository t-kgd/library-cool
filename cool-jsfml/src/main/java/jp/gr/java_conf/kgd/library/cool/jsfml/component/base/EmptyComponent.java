package jp.gr.java_conf.kgd.library.cool.jsfml.component.base;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponent;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

public class EmptyComponent
extends ComponentState
implements IComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3295231985155253986L;

	@Override
	public void draw(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
	}
}
