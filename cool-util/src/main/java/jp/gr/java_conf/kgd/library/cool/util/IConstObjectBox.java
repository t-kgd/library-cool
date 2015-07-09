package jp.gr.java_conf.kgd.library.cool.util;


/***
 * 不変でないオブジェクトを取り出せるので厳密にはConstではありません。
 * @author taisa
 *
 */
public interface IConstObjectBox
{
	boolean getBoolean(String key, boolean defaultValue);
	int getInteger(String key, int defaultValue);
	double getDouble(String key, double defaultValue);
	
	String getString(String key);
	
	//
	Object getObject(String key);
	
	IConstObjectBox getObjectBox(String key);
}
