package jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.ratio;

import java.util.Collection;
import java.util.Map;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.container.AbstractActorContainer;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.util.collection.CollectionHelper;
import jp.gr.java_conf.kgd.library.cool.util.collection.ISharableKeySet;
import jp.gr.java_conf.kgd.library.cool.util.collection.SharableKeySet;
import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public class RatioLayout
extends AbstractActorContainer
implements IRatioLayout
{
	private ISharableKeySet<IActor> keySet;
	private Map<IActor, FloatRect> ratioMap;
	
	private Vector2f originRatio = Vector2f.ZERO;
	
	/**
	 * コンストラクタ.
	 * 引数に真を与えた場合 {@link #setLayoutOriginRatio(Vector2f)}によって
	 * 原点を中心に設定した状態で生成されます。
	 */
	public RatioLayout()
	{
	}
	
	public RatioLayout(boolean isLayoutOriginCenter)
	{
		if(isLayoutOriginCenter)
		{
			setLayoutOriginRatio(CENTER_RATIO);
		}
	}
	
	private static final Vector2f CENTER_RATIO = new Vector2f(0.5f, 0.5f);
	
	
	@Override
	protected Collection<IActor> createActualContainer()
	{
		keySet = new SharableKeySet<>();
		
		ratioMap = keySet.createSharedKeyMap(FLOAT_RECT_CREATOR);
		
		return keySet;
	}

	@Override
	public boolean addActor(IActor actor, FloatRect ratioRect)
	{
		if(!super.addActor(actor))
		{
			return false;
		}
		
		ratioMap.put(actor, ratioRect);
		return true;
	}
	
	@Override
	final public boolean addActor(IActor actor, Vector2f positionRatio,
			Vector2f sizeRatio)
	{
		return addActor(actor, new FloatRect(positionRatio, sizeRatio));
	}
	
	@Override
	final public boolean addActor(IActor actor, float x, float y, float width,
			float height)
	{
		return addActor(actor, new FloatRect(x, y, width, height));
	}

	@Override
	final public boolean addActorStack(IActor actor, Object key,
			FloatRect ratioRect)
	{		
		return addActorStack(actor, key,
				ratioRect.left, ratioRect.top,
				ratioRect.width, ratioRect.height);
	}
	
	@Override
	final public boolean addActorStack(IActor actor, Object key,
			Vector2f positionRatio, Vector2f sizeRatio)
	{
		return addActorStack(actor, key,
				positionRatio.x, positionRatio.y,
				sizeRatio.x, sizeRatio.y);
	}
	
	@Override
	final public boolean addActorStack(IActor actor, Object key, float x, float y,
			float width, float height)
	{
		return addActorRelative(actor, key, 0, 0, x, y, width, height);
	}
	
	//
	@Override
	public boolean addActorRelative(IActor actor, Object key,
			int relativeSignX, int relativeSignY, FloatRect ratioRect)
	{
		return addActorRelative(actor, key, relativeSignX, relativeSignY,
				ratioRect.left, ratioRect.top,
				ratioRect.width, ratioRect.height);
	}
	
	@Override
	public boolean addActorRelative(IActor actor, Object key,
			int relativeSignX, int relativeSignY, Vector2f positionRatio,
			Vector2f sizeRatio)
	{
		return addActorRelative(actor, key, relativeSignX, relativeSignY,
				positionRatio.x, positionRatio.y,
				sizeRatio.x, sizeRatio.y);
	}
	
	@Override
	final public boolean addActorRelative(IActor actor, Object key,
			int relativeSignX, int relativeSignY, float x, float y,
			float width, float height)
	{
		FloatRect baseRect = getRatioRect(key);
		if(baseRect == null)
		{
			return false;
		}
		
//		relativeSignX = MathHelper.getSign(relativeSignX);
//		relativeSignY = MathHelper.getSign(relativeSignY);
		
		float left = x + baseRect.left;
		float top = y + baseRect.top;
		
		if(relativeSignX > 0)
		{
			left += baseRect.width * originRatio.x + width * (1.0f - originRatio.x);
		}
		else if(relativeSignX < 0)
		{
			left -= baseRect.width * (1.0f - originRatio.x) + width * originRatio.x;
		}
		
		if(relativeSignY > 0)
		{
			top += baseRect.height * originRatio.y + height * (1.0f - originRatio.y);
		}
		else if(relativeSignY < 0)
		{
			top -= baseRect.height * (1.0f - originRatio.y) + height * originRatio.y;
		}
		
		//
		return addActor(actor, left, top, width, height);
	}
	
	@Override
	public FloatRect getRatioRect(Object key)
	{
		return ratioMap.get(key);
	}

	@Override
	public boolean resetRatioRect(IActor actor, FloatRect ratioRect)
	{
		if(!ratioMap.containsKey(actor))
		{
			return false;
		}
			
		ratioMap.put(actor, ratioRect);
		return true;
	}
	
	@Override
	final public boolean resetRatioRect(IActor actor, Vector2f positionRatio,
			Vector2f sizeRatio)
	{
		return resetRatioRect(actor, new FloatRect(positionRatio, sizeRatio));
	}
	
	@Override
	final public boolean resetRatioRect(IActor actor, float x, float y, float width,
			float height)
	{
		return resetRatioRect(actor, new FloatRect(x, y, width, height));
	}
	
	@Override
	public Map<IActor, FloatRect> getUnresizableRatioRectMap()
	{
		return CollectionHelper.unresizableMap(ratioMap);
	}
	
	@Override
	public Vector2f getLayoutOriginRatio()
	{
		return originRatio;
	}
	
	
	@Override
	public void setLayoutOriginRatio(Vector2f originRatio)
	{
		this.originRatio = originRatio;
	}
	
	@Override
	final public void setLayoutOriginRatio(float x, float y)
	{
		setLayoutOriginRatio(new Vector2f(x, y));
	}
	
	@Override
	protected void onUpdate()
	{
		Vector2f layoutSize = getSize();
		
		for(IActor actor : getActors())
		{
			FloatRect ratio = getRatioRect(actor);
			
			Vector2f position = new Vector2f(layoutSize.x * ratio.left, layoutSize.y * ratio.top);
			Vector2f size = new Vector2f(layoutSize.x * ratio.width, layoutSize.y * ratio.height);
			
			ComponentHelper.setSizeIfNeedsUpdate(actor, size);
			actor.setPosition(position);
			actor.setOrigin(size.x * originRatio.x, size.y * originRatio.y);
		}
	}

	
	
	
	
	
	
	
	//
	private static final class FloatRectCreator
	implements IFunction<FloatRect>
	{
		@Override
		public FloatRect apply()
		{
			return FloatRect.EMPTY;
		}
	}
	
	private static final FloatRectCreator FLOAT_RECT_CREATOR = new FloatRectCreator();
}
