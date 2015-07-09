package jp.gr.java_conf.kgd.library.cool.jsfml.input.keys;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.IKeyStateManager;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.AbstractInputManager;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IConstInputConfig;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.IConstKeyBind;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.IKeyBind;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.KeyBind;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.KeyBindHelper;
import jp.gr.java_conf.kgd.library.cool.util.collection.ISharedKeyMapCreator;
import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public class JSFMLInputManager
extends AbstractInputManager
implements IJSFMLInputManager
{
	private Map<Object, IKeyBind> keyBindMap;

	//
	private final void commonInit()
	{
		ISharedKeyMapCreator<Object> mapCreator = getBindedInputConfig().getKeyIDMapCreator();
		
		this.keyBindMap = mapCreator.createSharedKeyMap(KEY_BIND_CREATOR);
	}
	
	public JSFMLInputManager(IConstInputConfig config, int joystickID)
	{
		super(config, joystickID);
		
		commonInit();
	}

	public JSFMLInputManager(IConstInputConfig config)
	{
		super(config);
		
		commonInit();
	}
	
	//
	@Override
	public IKeyBind getKeyBind(Object keyID)
	{
		return keyBindMap.get(keyID);
	}

	@Override
	public Collection<? extends IKeyBind> getUnmodifiableKeyBinds()
	{
		return Collections.unmodifiableCollection(keyBindMap.values());
	}

	//
	@Override
	public void updateKeyStates(boolean hasFocus)
	{
		//
		boolean isWindowFocusIgnored = getBindedInputConfig().isWindowFocusIgnored();
		
		boolean isForceUp = (!hasFocus) && (!isWindowFocusIgnored);
		
		//	
		int joystickID = getJoystickID();
		float joystickThresholdRate = getJoystickThresholdRate();
		
		for(Object keyID : getBindedInputConfig().getUnmodifiableKeyIDs())
		{
			IKeyStateManager keyStateManager = getKeyStateManager(keyID);
			
			if(isForceUp)
			{
				keyStateManager.updateKeyState(false);
			}
			else
			{
				IConstKeyBind keyBind = getKeyBind(keyID);
				
				keyStateManager.updateKeyState(
						KeyBindHelper.isAnyBindDown(joystickID, joystickThresholdRate, keyBind));
			}
		}
	}

	
	//
	private static final class KeyBindCreator
	implements IFunction<IKeyBind>
	{
		@Override
		public IKeyBind apply()
		{
			return new KeyBind();
		}
	}
	
	private static final KeyBindCreator KEY_BIND_CREATOR = new KeyBindCreator();	
}
