package jp.gr.java_conf.kgd.library.cool.jsfml.actor.base;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponent;

public class ScreenShotActor
extends RectangleActor
implements IScreenShotActor
{
	private RenderTexture renderTexture;
	
	public ScreenShotActor()
	{
	}
	
	public ScreenShotActor(RenderTexture renderTexture)
	{
		this.renderTexture = renderTexture;
	}


	@Override
	public RenderTexture getRenderTexture()
	{
		return this.renderTexture;
	}


	@Override
	public void setRenderTexture(RenderTexture renderTexture)
	{
		this.renderTexture = renderTexture;
	}
	
	@Override
	public void printScreen(IComponent component)
	{
		if(renderTexture == null || component == null)
		{
			return;
		}
		
		Vector2f componentSize = component.getSize();
		if(componentSize.x < 1 || componentSize.y < 1)
		{
			return;
		}
		
		Vector2i textureSize = renderTexture.getSize();
		
		if(textureSize.x < componentSize.x || textureSize.y < componentSize.y)
		{
			try
			{
				renderTexture.create((int)componentSize.x, (int)componentSize.y);
			}
			catch(TextureCreationException e)
			{
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				
				return;
			}
		}
		
		component.draw(renderTexture, RenderStates.DEFAULT, ComponentHelper.DEFAULT_COMPONENT_STATE);
		
		setTexture(renderTexture.getTexture());
		setTextureRect(new IntRect(0, 0, (int)componentSize.x, (int)componentSize.y));
	}
}
