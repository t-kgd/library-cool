package jp.gr.java_conf.kgd.library.cool.util;

public class OptionalHelper
{
	protected OptionalHelper()
	{
	}
	
	//失敗
	public static <V> IOptional<V> create()
	{
		return new Optional<V>();
	}
	
	public static <V> IOptional<V> create(int resultCode)
	{
		return new Optional<V>(resultCode);
	}
	
	public static <V> IOptional<V> create(int resultCode, String resultString)
	{
		return new Optional<V>(resultCode, resultString);
	}
	
	
	//成功？
	public static <V, S extends V> IOptional<V> create(S obj)
	{
		return new Optional<V>(obj);
	}
	
	public static <V, S extends V> IOptional<V> create(S obj, int resultCode)
	{
		return new Optional<V>(obj, resultCode);
	}
	
	public static <V, S extends V> IOptional<V> create(S obj, String resultString)
	{
		return new Optional<V>(obj, resultString);
	}
	
	public static <V, S extends V> IOptional<V> create(S obj, int resultCode, String resultString)
	{
		return new Optional<V>(obj, resultCode, resultString);
	}
}
