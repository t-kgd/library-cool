package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import org.jsfml.graphics.Color;

public interface IColorMaskState
extends IConstColorMaskState
{
//	void setVisibled(boolean flag);
	
	void setColorMask(Color color);
	void setAlphaMaskRate(float rate);
}
