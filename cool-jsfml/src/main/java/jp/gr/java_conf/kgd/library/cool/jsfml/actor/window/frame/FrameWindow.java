package jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.IShapeActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.RectangleActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.holder.AbstractParentActorHolder;
import jp.gr.java_conf.kgd.library.cool.jsfml.base.Direction8Way;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.IShapeComponent;

public class FrameWindow
extends AbstractParentActorHolder
implements IFrameWindow
{
	//委譲
	private FrameState frameState = new FrameState();
	
	//
	private Map<Direction8Way, IShapeActor> frameShapeMap = new EnumMap<>(Direction8Way.class);
	
	
	//
	public FrameWindow()
	{
//		Collection<IActor> fronts = getFrontChildren();
		
		for(Direction8Way direction : DIRECTIONS)
		{
			IShapeActor shape = new RectangleActor();
			
			frameShapeMap.put(direction, shape);
			
			
			if(direction == Direction8Way.CENTER)
			{
//				getBackChildren().add(shape);
				addBackChild(shape);
			}
			else
			{
//				fronts.add(shape);				
				addFrontChild(shape);
			}
		}
	}
	
	//
	@Override
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		
		frameState.setFrameNeedsUpdate(true);
	}
	
	//委譲
	public boolean isFrameVisible()
	{
		return frameState.isFrameVisible();
	}

	public float getFrameSize()
	{
		return frameState.getFrameSize();
	}

	public boolean isBackgroundVisible()
	{
		return frameState.isBackgroundVisible();
	}

	public void setFrameVisible(boolean flag)
	{
		frameState.setFrameVisible(flag);
	}

	public void setFrameSize(float size)
	{
		frameState.setFrameSize(size);
	}

	public void setBackgroundVisible(boolean flag)
	{
		frameState.setBackgroundVisible(flag);
	}

	public IColorMaskState getFrameColorState()
	{
		return frameState.getFrameColorState();
	}

	public IColorMaskState getBackgroundColorState()
	{
		return frameState.getBackgroundColorState();
	}

	public ConstTexture getFrameTexture()
	{
		return frameState.getFrameTexture();
	}

	public void setFrameTexture(ConstTexture texture)
	{
		frameState.setFrameTexture(texture);
		
		if(!isFrameNeedsUpdate())
		{
			return;
		}
		
		Vector2i size = (texture != null ? texture.getSize() : Vector2i.ZERO);
		
		int width = size.x / 3;
		int height = size.y / 3;
		
		//本来 width と height は等しくなければならないので、とりあえず片方である width を設定
		setFrameSize(width);
		
		
//		int i = 0;
		for(Direction8Way direction : DIRECTIONS)
		{
			IShapeComponent shape = frameShapeMap.get(direction);
			
			if(texture == null)
			{
				shape.setTexture(null, true);
				continue;
			}

			shape.setTexture(texture, true);
			
//			int ix = i % 3;
//			int iy = i / 3;
			
			int ix = 1 + direction.getDirectionSignX();
			int iy = 1 + direction.getDirectionSignY();
			
			shape.setTextureRect(
					new IntRect(
							width * ix,
							height * iy,
							width,
							height));
		}
	}

	protected boolean isFrameNeedsUpdate()
	{
		return frameState.isFrameNeedsUpdate();
	}

	protected void setFrameNeedsUpdate(boolean flag)
	{
		frameState.setFrameNeedsUpdate(flag);
	}
	//
	

	//
	@Override
	public Vector2f getOuterSize()
	{
		Vector2f size = getSize();
		float frameSize = getFrameSize();
		
		return new Vector2f(size.x + frameSize, size.y + frameSize);
	}
	
	
	//
	@Override
	protected void onUpdate()
	{
		if(isFrameNeedsUpdate())
		{
			setFrameNeedsUpdate(false);
			
			updateFrames();
		}
	}
	
	//
	protected Map<Direction8Way, IShapeActor> getUnmodifiableFrameShapeMap()
	{
		return Collections.unmodifiableMap(frameShapeMap);
	}

	//
	private final void updateFrames()
	{
		Vector2f parentSize = getSize();

		
		boolean isFrameVisible = isFrameVisible();
		boolean isBackgroundVisible = isBackgroundVisible();
		float frameSize = getFrameSize();
		IColorMaskState frameColorState = getFrameColorState();
		IColorMaskState backgroundColorState = getBackgroundColorState();
		
		
		boolean isCurrentFrameVisibled = isFrameVisible &&
				frameSize > 0 &&
				frameColorState.getAlphaMaskRate() > 0;
				
		boolean isCurrentBackgroundVisibled = isBackgroundVisible &&
				backgroundColorState.getAlphaMaskRate() > 0;
		
		
		//
		float parentCenterX = parentSize.x / 2;
		float parentCenterY = parentSize.y / 2;
		
		//
		for(Direction8Way direction : DIRECTIONS)
		{
			IShapeActor shape = frameShapeMap.get(direction);
			
			if(direction == Direction8Way.CENTER)
			{
				if(isCurrentBackgroundVisibled)
				{
					ComponentHelper.copyColorMaskState(shape, backgroundColorState);
					shape.setVisible(true);
				}
				else
				{
					shape.setVisible(false);
					continue;
				}
			}
			else
			{
				if(isCurrentFrameVisibled)
				{
					ComponentHelper.copyColorMaskState(shape, frameColorState);					
					shape.setVisible(true);
				}
				else
				{
					shape.setVisible(false);
					continue;
				}
			}
					
			//位置とサイズ
			int directionSignX = direction.getDirectionSignX();
			int directionSignY = direction.getDirectionSignY();

			float sizeX = (directionSignX != 0 ? frameSize : parentSize.x);
			float sizeY = (directionSignY != 0 ? frameSize : parentSize.y);

			shape.setSize(new Vector2f(sizeX, sizeY));

			shape.setOrigin(sizeX / 2, sizeY / 2);
			shape.setPosition(parentCenterX, parentCenterY);

			shape.move(
					(parentSize.x + sizeX) * directionSignX / 2,
					(parentSize.y + sizeY) * directionSignY / 2);
			
			//
		}
	}

	//
	private static final Direction8Way[] DIRECTIONS = Direction8Way.values();
}
