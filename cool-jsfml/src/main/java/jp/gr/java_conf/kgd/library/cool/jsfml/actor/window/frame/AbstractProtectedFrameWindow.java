package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.ActorHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;

/***
 * フレーム付きアクターを組み立てるための抽象クラスです。
 * フレームウインドウの、ホルダーとしてのメソッドを内部に隠蔽しています。
 * @author taisa
 *
 */
public abstract class AbstractProtectedFrameWindow
extends AbstractParentActor
implements IFrameActor
{
	private FrameWindow frameWindow = new FrameWindow();

	//
	protected AbstractProtectedFrameWindow()
	{

	}
	
	//
	protected IActor getInnerActor()
	{
		return frameWindow.getInnerActor();
	}
	
	protected void setInnerActor(IActor actor)
	{
		frameWindow.setInnerActor(actor);
	}
		
	@Override
	final protected void onUpdateChildren()
	{
		ActorHelper.updateStrictFixInnerActor(frameWindow, getSize());
	}
	
	@Override
 	final protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		frameWindow.draw(target, states, mask);
	}
	
	//
	public boolean isFrameVisible()
	{
		return frameWindow.isFrameVisible();
	}
	public void setFrameVisible(boolean flag)
	{
		frameWindow.setFrameVisible(flag);
	}
	public float getFrameSize()
	{
		return frameWindow.getFrameSize();
	}
	public void setFrameSize(float size)
	{
		frameWindow.setFrameSize(size);
	}
	public IColorMaskState getFrameColorState()
	{
		return frameWindow.getFrameColorState();
	}
	public boolean isBackgroundVisible()
	{
		return frameWindow.isBackgroundVisible();
	}
	public void setBackgroundVisible(boolean flag)
	{
		frameWindow.setBackgroundVisible(flag);
	}
	public IColorMaskState getBackgroundColorState()
	{
		return frameWindow.getBackgroundColorState();
	}
	public ConstTexture getFrameTexture()
	{
		return frameWindow.getFrameTexture();
	}
	public void setFrameTexture(ConstTexture texture)
	{
		frameWindow.setFrameTexture(texture);
	}
}
