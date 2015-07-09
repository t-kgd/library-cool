package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.LinkedList;
import java.util.List;

public class ListHelper
{
	protected ListHelper()
	{
	}
	
	
	//
	public static List<Integer> createIndexList(int indexNum)
	{
		List<Integer> list = new LinkedList<>();
		
		for(int i = 0; i < indexNum; ++i)
		{
			list.add(i);
		}
		
		return list;
	}
}
