package jp.gr.java_conf.kgd.library.cool.jsfml.input.bind;

import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.jsfml.input.IKeyDirection;

import org.jsfml.window.Keyboard.Key;

public interface IKeyBind
extends IConstKeyBind
{
	Collection<Key> getKeyboardKeyBinds();	
	Collection<IKeyDirection> getJoystickDirectionBinds();
	Collection<Integer> getJoystickButtonBinds();
}
