package jp.gr.java_conf.kgd.library.cool.util;

//戻り値のOptional自体がnull可能なため使いにくい
public interface IOptional<T>
{
	int RESULT_SUCCEED = 0;
	int RESULT_FAILED = -1;
	
	T get();
	
	boolean isValid();
	int getResultCode();
	String getResultString();
}
