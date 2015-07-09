package jp.gr.java_conf.kgd.library.cool.jsfml.input.bind;

import java.util.Collection;

import org.jsfml.window.Joystick;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;

import jp.gr.java_conf.kgd.library.cool.framework.input.VirtualKeys;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.IKeyDirection;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.KeyDirection4Way;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.IJSFMLInput;

public class KeyBindHelper
{
	protected KeyBindHelper()
	{
	}
	
	//
	public static final float JOYSTICK_THRESHOLD_FACTOR = 100;
	
	//
	public static boolean isAnyBindDown(
			int joystickID, float joystickThresholdRate,
			IConstKeyBind keyBind)
	{
		if(isAnyKeyboardKeyDown(keyBind.getUnmodifiableKeyboardKeyBinds()))
		{
			return true;
		}
		
		if(isAnyJoystickDirectionDown(joystickID, joystickThresholdRate,
				keyBind.getUnmodifiableJoystickDirectionBinds()))
		{
			return true;
		}
		
		if(isAnyJoystickButtonDown(joystickID,
				keyBind.getUnmodifiableJoystickButtonBinds()))
		{
			return true;
		}
		
		return false;
	}
	
	//
	public static boolean isAnyKeyboardKeyDown(Collection<? extends Key> keyboardKeys)
	{
		for(Key key : keyboardKeys)
		{
			if(Keyboard.isKeyPressed(key))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isAnyJoystickDirectionDown(
			int joystickID,
			float joystickThresholdRate,
			Collection<? extends IKeyDirection> joystickDirections)
	{
		for(IKeyDirection direction : joystickDirections)
		{
			float position = 
					Joystick.getAxisPosition(joystickID, direction.getAxis());

			int sign = direction.getSign();
			float input = joystickThresholdRate * JOYSTICK_THRESHOLD_FACTOR * sign;
					
			if (position > input && sign > 0)
			{
				return true;
			}
			else if (position < input && sign < 0)
			{
				return true;
			}
		}

		return false;
	}
	
	public static boolean isAnyJoystickButtonDown(
			int joystickID,
			Collection<? extends Integer> joystickButtons)
	{
		for(Integer button : joystickButtons)
		{
			if (Joystick.isButtonPressed(joystickID, button))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	//基本的なキーコンフィグ
	public static void setBasicJoyStickKeyBind(IJSFMLInput inputState)
	{
//		Collection<Object> keyIDs = inputState.getKeyIDs();
//		keyIDs.clear();
//		
//		keyIDs.addAll(VirtualKey.ALL_KEYS);
//		
		//
		for(Object keyID : VirtualKeys.getDirectionKeys())
		{
			IKeyBind keyBind = inputState.getKeyBind(keyID);
			
			if(keyBind == null)
			{
				continue;
			}
			
			Collection<IKeyDirection> directions = keyBind.getJoystickDirectionBinds();
			
			if(keyID == VirtualKeys.UP)
			{
				directions.add(KeyDirection4Way.KEY_UP);
				directions.add(KeyDirection4Way.STICK_UP);
				
				continue;
			}
			
			if(keyID == VirtualKeys.DOWN)
			{
				directions.add(KeyDirection4Way.KEY_DOWN);
				directions.add(KeyDirection4Way.STICK_DOWN);

				
				continue;
			}
			
			if(keyID == VirtualKeys.LEFT)
			{
				directions.add(KeyDirection4Way.KEY_LEFT);
				directions.add(KeyDirection4Way.STICK_LEFT);

				
				continue;
			}
			
			if(keyID == VirtualKeys.RIGHT)
			{
				directions.add(KeyDirection4Way.KEY_RIGHT);
				directions.add(KeyDirection4Way.STICK_RIGHT);

				continue;
			}
		}
		
		int i = 0;
		for(Object keyID : VirtualKeys.getButtonKeys())
		{
			IKeyBind keyBind = inputState.getKeyBind(keyID);
			
			if(keyBind == null)
			{
				continue;
			}
			
			Collection<Integer> buttons = keyBind.getJoystickButtonBinds();
			
			if(i < 4)
			{
				buttons.add(3 - i);
			}
			else
			{
				buttons.add(i);
			}
			
			i++;
		}
		//4 決定
		//3 キャンセル
		//2 サブ１
		//1 サブ２
	}
	
	public static void appendBasicKeybordKeyBind(IJSFMLInput inputState)
	{		
		for(Object keyID : VirtualKeys.getAllKeys())
		{			
			IKeyBind keyBind = inputState.getKeyBind(keyID);
			
			if(keyBind == null)
			{
				continue;
			}
			
			Collection<Key> keys = keyBind.getKeyboardKeyBinds();
		
			if(keyID == VirtualKeys.UP)
			{
				keys.add(Key.UP);
				keys.add(Key.NUMPAD8);
				
				continue;
			}
			
			if(keyID == VirtualKeys.DOWN)
			{
				keys.add(Key.DOWN);
				keys.add(Key.NUMPAD2);

				
				continue;
			}
			
			if(keyID == VirtualKeys.LEFT)
			{
				keys.add(Key.LEFT);
				keys.add(Key.NUMPAD4);

				
				continue;
			}
			
			if(keyID == VirtualKeys.RIGHT)
			{
				keys.add(Key.RIGHT);
				keys.add(Key.NUMPAD6);

				continue;
			}
			
			
			//
			if(keyID == VirtualKeys.OK)
			{
				keys.add(Key.Z);
				keys.add(Key.RETURN);
				keys.add(Key.SPACE);

				continue;
			}
			
			if(keyID == VirtualKeys.CANCEL)
			{
				keys.add(Key.X);
				keys.add(Key.ESCAPE);
				keys.add(Key.NUMPAD0);

				continue;
			}
			
			if(keyID == VirtualKeys.SUB1)
			{
				keys.add(Key.C);
				keys.add(Key.RSHIFT);

				continue;
			}
			
			if(keyID == VirtualKeys.SUB2)
			{
				keys.add(Key.V);

				continue;
			}
			
			if(keyID == VirtualKeys.L1)
			{
				keys.add(Key.Q);
				keys.add(Key.PAGEUP);

				continue;
			}
			
			if(keyID == VirtualKeys.R1)
			{
				keys.add(Key.W);
				keys.add(Key.PAGEDOWN);

				continue;
			}
			
			if(keyID == VirtualKeys.L2)
			{
				keys.add(Key.A);

				continue;
			}
			
			if(keyID == VirtualKeys.R2)
			{
				keys.add(Key.S);

				continue;
			}
			
			if(keyID == VirtualKeys.SELECT)
			{
				keys.add(Key.D);

				continue;
			}
			
			if(keyID == VirtualKeys.PAUSE)
			{
				keys.add(Key.F);

				continue;
			}
			
			if(keyID == VirtualKeys.L3)
			{
				keys.add(Key.E);

				continue;
			}
			
			if(keyID == VirtualKeys.R3)
			{
				keys.add(Key.R);

				continue;
			}
		}
	}
}