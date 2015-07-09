package jp.gr.java_conf.kgd.library.cool.framework.input.keys;

import jp.gr.java_conf.kgd.library.cool.framework.input.key.IConstKeyStateConfig;

public interface IConstKeyStateStoreConfig
extends IConstKeyStateConfig
{
	int getJoystickID();
	float getJoystickThresholdRate();
}
