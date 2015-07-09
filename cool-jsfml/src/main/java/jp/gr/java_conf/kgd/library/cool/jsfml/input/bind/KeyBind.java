package jp.gr.java_conf.kgd.library.cool.jsfml.input.bind;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import jp.gr.java_conf.kgd.library.cool.jsfml.input.IKeyDirection;

import org.jsfml.window.Keyboard.Key;

public class KeyBind
implements IKeyBind
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7481038562595408709L;
	
	//
	private Set<Key> keyboardKeyBinds = new LinkedHashSet<>();
	private Set<IKeyDirection> joystickDirectionBinds = new LinkedHashSet<>();
	private Set<Integer> joystickButtonBinds = new LinkedHashSet<>();

	//

	//
	@Override
	public Collection<? extends Key> getUnmodifiableKeyboardKeyBinds()
	{
		return Collections.unmodifiableCollection(keyboardKeyBinds);
	}

	@Override
	public Collection<? extends IKeyDirection> getUnmodifiableJoystickDirectionBinds()
	{
		return Collections.unmodifiableCollection(joystickDirectionBinds);
	}

	@Override
	public Collection<? extends Integer> getUnmodifiableJoystickButtonBinds()
	{
		return Collections.unmodifiableCollection(joystickButtonBinds);
	}

	@Override
	public Collection<Key> getKeyboardKeyBinds()
	{
		return keyboardKeyBinds;
	}

	@Override
	public Collection<IKeyDirection> getJoystickDirectionBinds()
	{
		return joystickDirectionBinds;
	}

	@Override
	public Collection<Integer> getJoystickButtonBinds()
	{
		return joystickButtonBinds;
	}
}
