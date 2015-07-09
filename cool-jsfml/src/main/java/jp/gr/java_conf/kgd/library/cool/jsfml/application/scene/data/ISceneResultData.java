package jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.data;

import jp.gr.java_conf.kgd.library.cool.util.IObjectBox;

public interface ISceneResultData
extends IObjectBox, ITransitionsData, IConstSceneResultData
{
	void setResultCode(int resultCode);
	IObjectBox getResultValues();
	
	//
	void setTransitionsOverWrite(boolean flag);
}
