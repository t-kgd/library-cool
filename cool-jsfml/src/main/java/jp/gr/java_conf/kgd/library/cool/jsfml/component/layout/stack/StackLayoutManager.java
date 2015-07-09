package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.stack;

import java.util.Collection;

import org.jsfml.system.Vector2f;

import jp.gr.java_conf.kgd.library.cool.jsfml.component.ComponentHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.IComponentState;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.IConstLayoutCache;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.LayoutCache;

public class StackLayoutManager
implements IStackLayoutManager
{
	private LayoutCache layoutCache = new LayoutCache();
	
	@Override
	public void applyLayoutToComponents(
			Collection<? extends IComponentState> states, Vector2f layoutSize)
	{
		layoutCache.setLayoutSize(layoutSize);
		
		int count = 0;
		for(IComponentState state : states)
		{
			ComponentHelper.setFitSizeAndOriginToCenter(state, layoutSize);

			count++;
		}
		
		layoutCache.setComponentNum(count);
	}

	@Override
	public IConstLayoutCache getLayoutCache()
	{
		return layoutCache;
	}
}
