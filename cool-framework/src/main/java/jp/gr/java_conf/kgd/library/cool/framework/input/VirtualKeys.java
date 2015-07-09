package jp.gr.java_conf.kgd.library.cool.framework.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 12ボタンゲームパッドを模倣した仮想キーです。
 * @author taisa
 *
 */
//public enum VirtualKeys
//{
//	UP,
//	DOWN,
//	LEFT,
//	RIGHT,
//	
//	OK,
//	CANCEL,
//	SUB1,
//	SUB2,
//	
//	L1,
//	R1,
//	
//	L2,
//	R2,
//	
//	SELECT,
//	PAUSE,
//	
//	L3,
//	R3,
//	
//	;
//	
//	//
//	public static List<VirtualKeys> getAllKeys()
//	{
//		return ALL_KEYS;
//	}
//	
//	public static List<VirtualKeys> getDirectionKeys()
//	{
//		return DIRECTION_KEYS;
//	}
//	
//	public static List<VirtualKeys> getButtonKeys()
//	{
//		return BUTTON_KEYS;
//	}
//	
//	
//	//
//	private static final List<VirtualKeys> ALL_KEYS =
//			Collections.unmodifiableList(Arrays.asList(VirtualKeys.values()));
//	
//	private static final List<VirtualKeys> DIRECTION_KEYS =
//			Collections.unmodifiableList(ALL_KEYS.subList(UP.ordinal(), RIGHT.ordinal() + 1));
//	
//	private static final List<VirtualKeys> BUTTON_KEYS =
//			Collections.unmodifiableList(ALL_KEYS.subList(OK.ordinal(), R3.ordinal() + 1));
//}

public class VirtualKeys
{
	protected VirtualKeys()
	{
	}
	
	//	
	public static final String UP;
	public static final String DOWN;
	public static final String LEFT;
	public static final String RIGHT;
	
	public static final String OK;
	public static final String CANCEL;
	public static final String SUB1;
	public static final String SUB2;
	
	
	public static final String L1;
	public static final String R1;
	
	public static final String L2;
	public static final String R2;
	
	public static final String SELECT;
	public static final String PAUSE;
	
	public static final String L3;
	public static final String R3;

	//
	public static List<String> getAllKeys()
	{
		return ALL_KEYS;
	}
	
	public static List<String> getDirectionKeys()
	{
		return DIRECTION_KEYS;
	}
	
	public static List<String> getButtonKeys()
	{
		return BUTTON_KEYS;
	}
	
	//
	private enum Inner
	{
		UP,
		DOWN,		
		LEFT,
		RIGHT,
		
		OK,
		CANCEL,		
		SUB1,
		SUB2,
		
		L1,
		R1,
		
		L2,
		R2,
		
		SELECT,
		PAUSE,
		
		L3,
		R3,
	}

	private static final List<String> ALL_KEYS;
	private static final List<String> DIRECTION_KEYS;
	private static final List<String> BUTTON_KEYS;

	static
	{
		List<String> keys = new ArrayList<>();
		
		for(Inner key : Inner.values())
		{
			keys.add(key.toString().toLowerCase());
		}
		
		ALL_KEYS = Collections.unmodifiableList(keys);
		DIRECTION_KEYS = Collections.unmodifiableList(ALL_KEYS.subList(Inner.UP.ordinal(), Inner.RIGHT.ordinal() + 1));
		BUTTON_KEYS = Collections.unmodifiableList(ALL_KEYS.subList(Inner.OK.ordinal(), Inner.R3.ordinal() + 1));
		
		//
		int i = 0;
		
		UP = ALL_KEYS.get(i++);
		DOWN = ALL_KEYS.get(i++);
		LEFT = ALL_KEYS.get(i++);
		RIGHT = ALL_KEYS.get(i++);
		
		OK = ALL_KEYS.get(i++);
		CANCEL = ALL_KEYS.get(i++);
		SUB1 = ALL_KEYS.get(i++);
		SUB2 = ALL_KEYS.get(i++);
		
		L1 = ALL_KEYS.get(i++);
		R1 = ALL_KEYS.get(i++);
		
		L2 = ALL_KEYS.get(i++);
		R2 = ALL_KEYS.get(i++);
		
		SELECT = ALL_KEYS.get(i++);
		PAUSE = ALL_KEYS.get(i++);
		
		L3 = ALL_KEYS.get(i++);
		R3 = ALL_KEYS.get(i++);
	}
}
