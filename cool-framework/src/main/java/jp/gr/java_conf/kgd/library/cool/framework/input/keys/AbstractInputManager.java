package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.IConstKeyState;
import jp.gr.java_conf.kgd.library.cool.framework.input.key.IKeyState;
import jp.gr.java_conf.kgd.library.cool.framework.input.key.IKeyStateManager;
import jp.gr.java_conf.kgd.library.cool.framework.input.key.KeyStateManager;
import jp.gr.java_conf.kgd.library.cool.util.collection.ISharedKeyMapCreator;
import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public abstract class AbstractInputManager
extends KeyStateStoreConfig
implements IInputManager
{
	//
	private IConstInputConfig bindedInputConfig;

	//
	private Map<Object, IKeyStateManager> keyStateManagerMap;
	
	
	//
	protected AbstractInputManager(
			IConstInputConfig config,
			int joystickID)
	{
		super(joystickID);
		
		bindInputConfig(config);

		//
		ISharedKeyMapCreator<Object> mapCreator = config.getKeyIDMapCreator();
		
		this.keyStateManagerMap = mapCreator.createSharedKeyMap(new KeyStateManagerCreator());
	}
	
	protected AbstractInputManager(IConstInputConfig config)
	{
		this(config, DEFAULT_JOYSTICK_ID);
	}
	
	
	//
	@Override
	public Collection<? extends IKeyState> getUnmodifiableKeyStates()
	{
		return Collections.unmodifiableCollection(keyStateManagerMap.values());
	}
	
	@Override
	public IKeyState getKeyState(Object keyID)
	{	
		return keyStateManagerMap.get(keyID);
	}

	@Override
	public IConstInputConfig getBindedInputConfig()
	{
		return bindedInputConfig;
	}

	@Override
	public void bindInputConfig(IConstInputConfig config)
	{
		this.bindedInputConfig = Objects.requireNonNull(config);
	}
	
	@Override
	public Object isAnyKeyPressed()
	{
		return isAnyKeyPressed(getBindedInputConfig().getUnmodifiableKeyIDs());
	}
	
	@Override
	public Object isAnyKeyPressed(Collection<? extends Object> filterKeyIDs)
	{
		for(Object keyID : filterKeyIDs)
		{
			IConstKeyState state = getKeyState(keyID);
			
			if(state == null)
			{
				continue;
			}
			
			if(state.isPressed())
			{
				return keyID;
			}
		}
		
		return null;
	}
	
	//
	protected Collection<? extends IKeyStateManager> getUnmodifiableKeyStateManagers()
	{
		return Collections.unmodifiableCollection(keyStateManagerMap.values());
	}
	
	protected IKeyStateManager getKeyStateManager(Object keyID)
	{
		return keyStateManagerMap.get(keyID);
	}
	
	//
	private final class KeyStateManagerCreator
	implements IFunction<IKeyStateManager>
	{
		@Override
		public KeyStateManager apply()
		{
			return new KeyStateManager(AbstractInputManager.this);
		}
	}
}
