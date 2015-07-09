package jp.gr.java_conf.kgd.library.cool.util;

public class Optional<T>
implements IOptional<T>
{
	private final T result;
	
	private final int resultCode;
	private final String resultString;
	
	//
	//失敗
	public Optional()
	{
		this(RESULT_FAILED, "");
	}
	
	public Optional(int resultCode)
	{
		this(resultCode, "");
	}
	
	public Optional(int resultCode, String resultString)
	{
		this(null, resultCode, resultString);
	}
	
	//成功？
	public Optional(T obj)
	{
		this(obj, RESULT_SUCCEED);
	}
	
	public Optional(T obj, int resultCode)
	{
		this(obj, resultCode, "");
	}
	
	public Optional(T obj, String resultString)
	{
		this(obj, RESULT_SUCCEED, resultString);
	}
	
	public Optional(T obj, int resultCode, String resultString)
	{
		this.result = obj;
		this.resultCode = resultCode;
		this.resultString = resultString;
	}
	
	//
	@Override
	public T get()
	{
		return result;
	}
	
	@Override
	public boolean isValid()
	{
		return result != null;
	}
	
	@Override
	public int getResultCode()
	{
		return resultCode;
	}
	
	@Override
	public String getResultString()
	{
		return resultString;
	}
}
