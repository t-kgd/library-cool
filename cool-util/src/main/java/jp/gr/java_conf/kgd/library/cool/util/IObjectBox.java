package jp.gr.java_conf.kgd.library.cool.util;


public interface IObjectBox
extends IConstObjectBox
{
	public Boolean put(String key, boolean value);
	public Integer put(String key, int value);
	public Double put(String key, double value);
	
	public String put(String key, String value);
	
	//
	public Object putObject(String key, Object value);
	
	public IObjectBox putObjectBox(String key, IObjectBox value);
	
	//
	@Override
	public IObjectBox getObjectBox(String key);
}
