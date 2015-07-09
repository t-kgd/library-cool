package jp.gr.java_conf.kgd.library.cool.jsfml.resource.font;

import jp.gr.java_conf.kgd.library.cool.jsfml.resource.IResourceMap;

import org.jsfml.graphics.ConstFont;

public interface IFontResourceMap
extends IResourceMap<ConstFont>
{
	String SYSTEM_FONT = "system_font";
	String NUMERICAL_FONT = "numerical_font";
	
	
	ConstFont getSystemFont();
	void setSystemFont(ConstFont font);
	
	ConstFont getNumericalFont();
	void setNumericalFont(ConstFont font);
}
