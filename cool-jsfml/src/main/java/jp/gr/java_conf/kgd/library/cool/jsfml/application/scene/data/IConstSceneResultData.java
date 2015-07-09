package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

public interface IConstSceneResultData
extends IConstObjectBox, IConstTransitionsData
{
	int RESULT_SUCCEED = 0;
	int RESULT_FAILED = -1;
	
	//
	int getResultCode();
	IConstObjectBox getResultValues();
	
	//
	boolean isTransitionsOverWrite();
}
