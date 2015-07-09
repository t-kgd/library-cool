package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.IKeyStateConfig;

public interface IKeyStateStoreConfig
extends IConstKeyStateStoreConfig, IKeyStateConfig
{
	void setJoystickID(int joystickID);
	void setJoystickThresholdRate(float rate);
}
