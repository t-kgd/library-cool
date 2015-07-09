package jp.gr.java_conf.kgd.library.cool.util;

import java.util.ArrayList;
import java.util.List;

/**
 * サイズ調節メソッドはプロポーショナルフォントに対して役割を持てません。
 * @author taisa
 *
 */
public class StringHelper
{
	protected StringHelper()
	{
	}
	
	//
//	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String LINE_SEPARATOR = System.lineSeparator();
	
	public static final String EMPTY_STRING = "";
	public static final String SPACE_HALF = " ";

	
	//文字
	public static boolean isCodePointHalfWidthCharacter(int cp)
	{
		return (0x0 <= cp && cp <= 0x7F) || (0xFF61 <= cp && cp <= 0xFF9F);
	}
	
	
	//文字列
	public static String replaceAllLineSeparator(String str)
	{
		return str.replaceAll(LINE_SEPARATOR, EMPTY_STRING);
	}
	
	public static int getHalfCharacterSize(String str)
	{
		int halfSizeCount = 0;
		int length = str.length();
//		int length = str.codePointCount(0, str.length());
		for(int i = 0; i < length; ++i)
		{
			if(isCodePointHalfWidthCharacter(str.codePointAt(i)))
			{
				halfSizeCount += 1;
			}
			else
			{
				halfSizeCount += 2;
			}
		}
		
		return halfSizeCount;
	}
	
	public static int getLineNum(String str)
	{
		int count = 0;
		
		int currentIndex = 0;
		int length = str.length();
		while(currentIndex < length)
		{
			currentIndex = str.indexOf(LINE_SEPARATOR, currentIndex);
			
			if(currentIndex < 0)
			{
				break;
			}
			
			currentIndex++;
			count++;
		}
		
		return count + 1;
	}
	
	public static String getNonNullString(String str)
	{
		return str != null ? str : EMPTY_STRING;
	}
	
	
	//
	public static String rightJustification(int halfWidthCharacterSize, String str)
	{
		int size = getHalfCharacterSize(str);
		
		String target = str;
		
		if(size > halfWidthCharacterSize)
		{
			int deleteLength = size - halfWidthCharacterSize;
			
			while(deleteLength > 0)
			{
				int cp = str.codePointAt(0);
				
				target = target.substring(1);
				
				if(isCodePointHalfWidthCharacter(cp))
				{
					deleteLength -= 1;
				}
				else
				{
					deleteLength -= 2;
				}
			}
		}
		else
		{
			int appendLength = halfWidthCharacterSize - size;
			
			while(appendLength > 0)
			{
				target = SPACE_HALF + target;
				
				appendLength -= 1;
			}
		}
			
		return target;
	}
	
	
	
	
	
	//
	//
	/**
	 * 文字列の分割.
	 * 指定したサイズに応じて切り分けるだけであり、半角スペースの挿入などは一切行いません。
	 * @param halfWidthCharacterSize
	 * @param str
	 * @return
	 */
	public static List<String> splitStringIfSizeIsOver(int halfWidthCharacterSize, String str)
	{
		return splitStringIfSizeIsOver(halfWidthCharacterSize, str, false);
	}
	
	public static List<String> splitStringIfSizeIsOver(int halfWidthCharacterSize, String str,
			boolean isLineSeparatorIgnored)
	{
		List<String> outList = new ArrayList<>();
		
		if(isLineSeparatorIgnored)
		{
			innerSplitStringIfSizeIsOverIgnoreLineSeparator(outList, halfWidthCharacterSize, str);
		}
		else
		{
			innerSplitStringIfSizeIsOver(outList, halfWidthCharacterSize, str);
		}
		
		return outList;
	}
	
	public static void splitStringIfSizeIsOver(List<String> outList, int halfWidthCharacterSize, String str)
	{
		outList.clear();
		splitStringIfSizeIsOver(outList, halfWidthCharacterSize, str, false);
	}
	
	public static void splitStringIfSizeIsOver(List<String> outList, int halfWidthCharacterSize, String str,
			boolean isLineSeparatorIgnored)
	{
		outList.clear();
		
		if(isLineSeparatorIgnored)
		{
			innerSplitStringIfSizeIsOverIgnoreLineSeparator(outList, halfWidthCharacterSize, str);
		}
		else
		{
			innerSplitStringIfSizeIsOver(outList, halfWidthCharacterSize, str);
		}
	}
	
	private static void innerSplitStringIfSizeIsOverIgnoreLineSeparator(
			List<String> outList, int halfWidthCharacterSize, String str)
	{
		str = replaceAllLineSeparator(str);
		halfWidthCharacterSize = Math.max(2, halfWidthCharacterSize);
		
		//
		int stringLength = str.length();

		//
		int halfSizeCount = 0;		
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < stringLength; ++i)
		{
			if(isCodePointHalfWidthCharacter(str.codePointAt(i)))
			{
				stringBuilder.append(str.charAt(i));
				halfSizeCount += 1;
			}
			else
			{
				if(halfSizeCount == halfWidthCharacterSize - 1)
				{
					//-1 は 半角ぶん突き抜けないようにするため。
//					halfSizeCount += 1;
					halfSizeCount = halfWidthCharacterSize;
					
					//もう一度評価するために戻す。
					i--;
				}
				else
				{
					stringBuilder.append(str.charAt(i));
					halfSizeCount += 2;
				}
			}
			
			if(halfSizeCount >= halfWidthCharacterSize)
			{			
				outList.add(stringBuilder.toString());
				
				stringBuilder.setLength(0);
				halfSizeCount = 0;
			}
		}
		
		if(halfSizeCount > 0)
		{
			outList.add(stringBuilder.toString());
		}
	}
	
	private static void innerSplitStringIfSizeIsOver(
			List<String> outList, int halfWidthCharacterSize, String str)
	{
		halfWidthCharacterSize = Math.max(2, halfWidthCharacterSize);
		
		String[] strings = str.split(LINE_SEPARATOR);
		
		List<String> stringsBuf = new ArrayList<>();
		
		for(int i = 0; i < strings.length; ++i)
		{
			innerSplitStringIfSizeIsOverIgnoreLineSeparator(outList, halfWidthCharacterSize, strings[i]);
			outList.addAll(stringsBuf);
			stringsBuf.clear();
		}		
	}
	
	//
	/**
	 * 文字列の加工.
	 * 文字列が指定した半角サイズになるように加工します。
	 * サイズが足りない場合は半角スペースを詰め、サイズがオーバーしている場合は
	 * オーバーしている部分を切り取ります。全角文字の半分がはみ出している場合は
	 * 代わりに半角スペースが挿入されます。
	 * @param fixedHalfWidthCharacterSize
	 * @param str
	 * @return
	 */
	public static String fixStringSize(int fixedHalfWidthCharacterSize, String str)
	{
		str = replaceAllLineSeparator(str);
		fixedHalfWidthCharacterSize = Math.max(2, fixedHalfWidthCharacterSize);
		
		//
		StringBuilder stringBuilder = new StringBuilder();
		int halfSizeCount = 0;
		int length = str.length();
		for(int i = 0; i < length; ++i)
		{
			if(isCodePointHalfWidthCharacter(str.codePointAt(i)))
			{
				stringBuilder.append(str.charAt(i));
				halfSizeCount += 1;
			}
			else
			{
				if(halfSizeCount == fixedHalfWidthCharacterSize - 1)
				{
					stringBuilder.append(SPACE_HALF);
					halfSizeCount = fixedHalfWidthCharacterSize;
					break;
				}
				else
				{
					stringBuilder.append(str.charAt(i));
					halfSizeCount += 2;
				}
			}
			
			if(halfSizeCount >= fixedHalfWidthCharacterSize)
			{
				break;
			}
		}
		
		while(halfSizeCount < fixedHalfWidthCharacterSize)
		{
			stringBuilder.append(SPACE_HALF);
			halfSizeCount += 1;
		}
		
		return stringBuilder.toString();
	}
	
	
	//
	public static List<String> splitStringFixRectPage(
			int fixedHalfWidthCharacterSize, int fixedLineNum, String str)
	{
		List<String> outList = new ArrayList<>();
		innerSplitStringFixRectPage(outList, fixedHalfWidthCharacterSize, fixedLineNum, str);
		return outList;
	}
	
	public static void splitStringFixRectPage(List<String> outList,
			int fixedHalfWidthCharacterSize, int fixedLineNum, String str)
	{
		outList.clear();
		innerSplitStringFixRectPage(outList, fixedHalfWidthCharacterSize, fixedLineNum, str);
	}
	
	private static void innerSplitStringFixRectPage(List<String> outList,
			int fixedHalfWidthCharacterSize, int fixedLineNum, String str)
	{
		fixedLineNum = Math.max(1, fixedLineNum);
		
		//
		List<String> lines = splitStringIfSizeIsOver(fixedHalfWidthCharacterSize, str);
		int linesLength = lines.size();
		
		String blankLine = null;
		
		StringBuilder stringBuilder = new StringBuilder();	

		int linesIndex = 0;
		while(linesIndex < linesLength || outList.isEmpty())
		{
			for(int i = 0; i < fixedLineNum; ++i)
			{
				if(linesIndex < linesLength)
				{
					stringBuilder.append(fixStringSize(fixedHalfWidthCharacterSize, lines.get(linesIndex)));
					linesIndex++;
				}
				else
				{
					if(blankLine == null)
					{
						//一応、遅延生成。
						blankLine = fixStringSize(fixedHalfWidthCharacterSize, EMPTY_STRING);
					}

					stringBuilder.append(blankLine);
				}

				if(i < fixedLineNum - 1)
				{
					stringBuilder.append(LINE_SEPARATOR);
				}
			}
			
			outList.add(stringBuilder.toString());
			stringBuilder.setLength(0);
		}
	}
}
