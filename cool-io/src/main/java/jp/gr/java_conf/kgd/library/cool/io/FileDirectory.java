package jp.gr.java_conf.kgd.library.cool.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileDirectory
implements IVirtualDirectory
{
	private File file;
	
	//
	public FileDirectory(File file)
	{
		this.file = file;
	}

	
	//
	@Override
	public File toFile()
	{
		return file;
	}

	@Override
	public InputStream getInputStream(String filename)
	{
		try
		{
			return new FileInputStream(file.getPath() + File.separator + filename);
		}
		catch (FileNotFoundException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			return null;
		}
	}
}
