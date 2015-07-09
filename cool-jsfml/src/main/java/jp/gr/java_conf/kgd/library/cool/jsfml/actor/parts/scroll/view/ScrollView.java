package jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.view;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.AbstractParentActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IScrollbar;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.IScrollbarConfig;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.parts.scroll.bar.Scrollbar;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.view.RectangleView;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IConstColorMaskState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.base.IShapeState;

public class ScrollView
extends AbstractParentActor
implements IScrollView
{
	private RectangleView rectangleView = new RectangleView();
	
	private IScrollbar horizontalScrollbar = new Scrollbar();
	private IScrollbar verticalScrollbar = new Scrollbar();
	
	private IScrollbarConfig scrollbarConfig = new ObservalScrollbarConfig();

	//
	private float scrollbarSize = 8;
	
	//
	private boolean isLayoutNeedsUpdate = true;
	
	//
	public ScrollView()
	{
		horizontalScrollbar.setVertical(false);
		verticalScrollbar.setVertical(true);
	}

	
	//
	@Override
	public float getScrollbarSize()
	{
		return this.scrollbarSize;
	}
	
	@Override
	public void setScrollbarSize(float size)
	{
		this.scrollbarSize = Math.max(1, size);
		
		isLayoutNeedsUpdate = true;
	}
	
	@Override
	public IScrollbarConfig getScrollbarConfig()
	{
		return scrollbarConfig;
	}
	
	//
	@Override
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		
		isLayoutNeedsUpdate = true;
	}
	
	
	//
	@Override
	protected void onUpdate()
	{
		if(isLayoutNeedsUpdate)
		{
			isLayoutNeedsUpdate = false;
			updateLayout();
		}
		
		//
		Vector2f outerSize = getSize();		
		Vector2f innerSize = rectangleView.getInnerSize();		
		
		float rateX = outerSize.x / innerSize.x;
		float rateY = outerSize.y / innerSize.y;
		
		horizontalScrollbar.setSliderLengthRate(rateX);
		verticalScrollbar.setSliderLengthRate(rateY);
		
		Vector2f origin = rectangleView.getViewOrigin();
		Vector2f position = rectangleView.getViewPosition();
		
		float left = position.x - origin.x;
		float top = position.y - origin.y;
		
		float rangeX = innerSize.x - outerSize.x;
		float rangeY = innerSize.y - outerSize.y;
		
		horizontalScrollbar.setSliderPositionRate(left / rangeX);
		verticalScrollbar.setSliderPositionRate(top / rangeY);
	}
	
	private final void updateLayout()
	{
		Vector2f baseSize = getSize();
		
		float viewWidth = baseSize.x - scrollbarSize;
		float viewHeight = baseSize.y - scrollbarSize;
		
		rectangleView.setSize(viewWidth, viewHeight);	
//		rectangleView.setViewOriginKeepedCenter(rectangleView.isViewOriginKeepedCenter());
//		rectangleView.setSize(new Vector2f(viewWidth, viewHeight));	
//		ComponentHelper.setSizeIfNeedsUpdate(rectangleView, viewWidth, viewHeight);
		//委譲で実装したクラスの委譲オーバーライドメソッドに委譲オーバーロードしてるメソッドで事故が起きていた。
		
		float scrollbarFrameSize = horizontalScrollbar.getFrameSize();
		float scrollbarInnerSize = Math.max(1, scrollbarSize - scrollbarFrameSize * 2);
		
		horizontalScrollbar.setSize(viewWidth, scrollbarInnerSize);
//		ComponentHelper.setSizeIfNeedsUpdate(horizontalScrollbar, viewWidth, scrollbarInnerSize);
		
		verticalScrollbar.setSize(scrollbarInnerSize, viewHeight);
//		ComponentHelper.setSizeIfNeedsUpdate(verticalScrollbar, scrollbarInnerSize, viewHeight);

		
		horizontalScrollbar.setPosition(0, viewHeight + scrollbarFrameSize);
		verticalScrollbar.setPosition(viewWidth + scrollbarFrameSize, 0);
	}
	
	@Override
	protected void onUpdateChildren()
	{
		rectangleView.update();
		
		horizontalScrollbar.update();
		verticalScrollbar.update();
	}
	
	@Override
	protected void onDrawChildren(RenderTarget target, RenderStates states,
			IConstColorMaskState mask)
	{
		rectangleView.draw(target, states, mask);
		
		horizontalScrollbar.draw(target, states, mask);
		verticalScrollbar.draw(target, states, mask);
	}
	
	
	//
	private class ObservalScrollbarConfig
	implements IScrollbarConfig
	{
		private IShapeState shapeState = new ObservalShapeState();
		
		private IColorMaskState frameColorMaskState =
				new ObservalColorMaskState(
						horizontalScrollbar.getFrameColorState(),
						verticalScrollbar.getFrameColorState());
		
		private IColorMaskState backgroundColorMaskState =
				new ObservalColorMaskState(
						horizontalScrollbar.getBackgroundColorState(),
						verticalScrollbar.getBackgroundColorState());
		
		public float getSliderWidthRate()
		{
			return horizontalScrollbar.getSliderWidthRate();
		}

		public void setSliderWidthRate(float rate)
		{
			horizontalScrollbar.setSliderWidthRate(rate);
			verticalScrollbar.setSliderWidthRate(rate);
		}

		public boolean isFrameVisible()
		{
			return horizontalScrollbar.isFrameVisible();
		}

		public void setFrameVisible(boolean flag)
		{
			horizontalScrollbar.setFrameVisible(flag);
			verticalScrollbar.setFrameVisible(flag);
		}

		public float getFrameSize()
		{
			return horizontalScrollbar.getFrameSize();
		}

		public IShapeState getSliderShapeState()
		{
			return shapeState;
		}

		public void setFrameSize(float size)
		{
			horizontalScrollbar.setFrameSize(size);
			verticalScrollbar.setFrameSize(size);
			
			isLayoutNeedsUpdate = true;
		}

		public IColorMaskState getFrameColorState()
		{
			return frameColorMaskState;
		}

		public boolean isBackgroundVisible()
		{
			return horizontalScrollbar.isBackgroundVisible();
		}

		public void setBackgroundVisible(boolean flag)
		{
			horizontalScrollbar.setBackgroundVisible(flag);
			verticalScrollbar.setBackgroundVisible(flag);
		}

		public IColorMaskState getBackgroundColorState()
		{
			return backgroundColorMaskState;
		}

		public ConstTexture getFrameTexture()
		{
			return horizontalScrollbar.getFrameTexture();
		}

		public void setFrameTexture(ConstTexture texture)
		{
			horizontalScrollbar.setFrameTexture(texture);
			verticalScrollbar.setFrameTexture(texture);
		}
	}
	
	private class ObservalShapeState
	implements IShapeState
	{
		private IShapeState horizontalState = horizontalScrollbar.getSliderShapeState();
		private IShapeState verticalState = verticalScrollbar.getSliderShapeState();

		public void setFillColor(Color color)
		{
			horizontalState.setFillColor(color);
			verticalState.setFillColor(color);
		}

		public ConstTexture getTexture()
		{
			return horizontalState.getTexture();
		}

		public Color getFillColor()
		{
			return horizontalState.getFillColor();
		}

		public void setTexture(ConstTexture texture, boolean resetRect)
		{
			horizontalState.setTexture(texture, resetRect);
			verticalState.setTexture(texture, resetRect);
		}

		public void setOutlineColor(Color color)
		{
			horizontalState.setOutlineColor(color);
			verticalState.setOutlineColor(color);
		}

		public IntRect getTextureRect()
		{
			return horizontalState.getTextureRect();
		}

		public Color getOutlineColor()
		{
			return horizontalState.getOutlineColor();
		}

		public void setOutlineThickness(float thickness)
		{
			horizontalState.setOutlineThickness(thickness);
			verticalState.setOutlineThickness(thickness);
		}

		public float getOutlineThickness()
		{
			return horizontalState.getOutlineThickness();
		}

		public void setTexture(ConstTexture texture)
		{
			horizontalState.setTexture(texture);
			verticalState.setTexture(texture);
		}

		public Vector2f getPoint(int i)
		{
			return horizontalState.getPoint(i);
		}

		public void setTextureRect(IntRect rect)
		{
			horizontalState.setTextureRect(rect);
			verticalState.setTextureRect(rect);
		}

		public int getPointCount()
		{
			return horizontalState.getPointCount();
		}

		public Vector2f[] getPoints()
		{
			return horizontalState.getPoints();
		}
	}

	private class ObservalColorMaskState
	implements IColorMaskState
	{
		private IColorMaskState horizontalColorMaskState;
		private IColorMaskState verticalColorMaskState;
		
		public ObservalColorMaskState(IColorMaskState horizontal, IColorMaskState vertical)
		{
			this.horizontalColorMaskState = horizontal;
			this.verticalColorMaskState = vertical;
		}
		
		public Color getColorMask()
		{
			return horizontalColorMaskState.getColorMask();
		}
		public float getAlphaMaskRate()
		{
			return horizontalColorMaskState.getAlphaMaskRate();
		}
		public void setColorMask(Color color)
		{
			horizontalColorMaskState.setColorMask(color);
			verticalColorMaskState.setColorMask(color);
		}
		public void setAlphaMaskRate(float rate)
		{
			horizontalColorMaskState.setAlphaMaskRate(rate);
			verticalColorMaskState.setAlphaMaskRate(rate);
		}
		
		
	}
	
	//
	public IActor getInnerActor()
	{
		return rectangleView.getInnerActor();
	}

	public void setInnerActor(IActor actor)
	{
		rectangleView.setInnerActor(actor);
	}

	public Vector2f getInnerSize()
	{
		return rectangleView.getInnerSize();
	}

	public Vector2f getViewPosition()
	{
		return rectangleView.getViewPosition();
	}

	public void setInnerSize(Vector2f size)
	{
		rectangleView.setInnerSize(size);
	}

	public Vector2f getViewOrigin()
	{
		return rectangleView.getViewOrigin();
	}

	public final void setInnerSize(float width, float height)
	{
		rectangleView.setInnerSize(width, height);
	}

	public boolean isViewOriginKeepedCenter()
	{
		return rectangleView.isViewOriginKeepedCenter();
	}

	public void setViewPosition(Vector2f position)
	{
		rectangleView.setViewPosition(position);
	}

	public final void setViewPosition(float x, float y)
	{
		rectangleView.setViewPosition(x, y);
	}

	public void setViewOrigin(Vector2f origin)
	{
		rectangleView.setViewOrigin(origin);
	}

	public final void setViewOrigin(float x, float y)
	{
		rectangleView.setViewOrigin(x, y);
	}

	public void setViewOriginKeepedCenter(boolean flag)
	{
		rectangleView.setViewOriginKeepedCenter(flag);
	}

	public void moveViewPosition(Vector2f diff)
	{
		rectangleView.moveViewPosition(diff);
	}

	public final void moveViewPosition(float x, float y)
	{
		rectangleView.moveViewPosition(x, y);
	}
}
