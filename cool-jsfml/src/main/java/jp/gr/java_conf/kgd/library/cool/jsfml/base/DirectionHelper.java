package jp.gr.java_conf.kgd.library.cool.jsfml.base;

import static jp.gr.java_conf.kgd.library.cool.framework.input.VirtualKeys.*;

public class DirectionHelper
{
//	public static final IDirection getDirectionFromVirtualKey(VirtualKeys key)
//	{
//		switch (key)
//		{
//		case UP:
//			return Direction8Way.TOP;
//			
//		case DOWN:
//			return Direction8Way.BOTTOM;
//		
//		case LEFT:
//			return Direction8Way.LEFT;
//			
//		case RIGHT:
//			return Direction8Way.RIGHT;
//			
//		
//		case L1:
//			return Direction8Way.LEFT;
//			
//		case R1:
//			return Direction8Way.RIGHT;
//			
//		case L2:
//			return Direction8Way.LEFT;
//			
//		case R2:
//			return Direction8Way.RIGHT;
//			
//		case L3:
//			return Direction8Way.LEFT;
//			
//		case R3:
//			return Direction8Way.RIGHT;
//
//		default:
//			return null;
//		}
//	}
	
	public static final IDirection getDirectionFromVirtualKey(Object key)
	{
		if(key.equals(UP))
		{
			return Direction8Way.TOP;
		}
		
		if(key.equals(DOWN))
		{
			return Direction8Way.BOTTOM;
		}
		
		if(key.equals(LEFT))
		{
			return Direction8Way.LEFT;
		}
		
		if(key.equals(RIGHT))
		{
			return Direction8Way.RIGHT;
		}
		
		return Direction8Way.CENTER;
	}
}
