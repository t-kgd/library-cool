package jp.gr.java_conf.kgd.library.cool.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class FileHelper
{
	protected FileHelper()
	{
	}
	
	//
	public static final Path OUTSIDE_PATH;
	
	static
	{
		URI uri = null;
		
		try
		{
			uri = FileHelper.class.getClassLoader().getResource("").toURI();
		}
		catch (URISyntaxException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		File file = new File(uri);		
		OUTSIDE_PATH = file.toPath();
	}
	
	//
	public static final String getOutsideDirectoryPath()
	{
		return OUTSIDE_PATH.toString() + File.separator;
	}
	
	//
	public static String createStringFromStream(InputStream stream)
			throws IOException
	{
		BufferedReader reader =
				new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[1024];
		int line;
		while (0 <= (line = reader.read(buffer)))
		{
			stringBuilder.append(buffer, 0, line);
		}
		
		return stringBuilder.toString();
	}
}
