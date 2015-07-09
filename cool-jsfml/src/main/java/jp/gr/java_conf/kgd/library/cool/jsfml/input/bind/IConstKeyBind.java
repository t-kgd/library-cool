package jp.gr.java_conf.kgd.library.cool.jsfml.input.bind;

import java.io.Serializable;
import java.util.Collection;

import jp.gr.java_conf.kgd.library.cool.jsfml.input.IKeyDirection;

import org.jsfml.window.Keyboard.Key;

public interface IConstKeyBind
extends Serializable
{
	Collection<? extends Key> getUnmodifiableKeyboardKeyBinds();	
	Collection<? extends IKeyDirection> getUnmodifiableJoystickDirectionBinds();
	Collection<? extends Integer> getUnmodifiableJoystickButtonBinds();
}
