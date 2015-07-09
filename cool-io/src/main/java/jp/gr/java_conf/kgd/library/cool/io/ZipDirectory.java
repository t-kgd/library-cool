package jp.gr.java_conf.kgd.library.cool.io;

import java.io.File;
import java.io.InputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class ZipDirectory
implements IVirtualDirectory
{
	private ZipFile file;

	public ZipDirectory(ZipFile file)
	{
		this.file = file;
	}
	
	//	
	@Override
	public File toFile()
	{
		return file.getFile();
	}

	@Override
	public InputStream getInputStream(String filename)
	{
		FileHeader header;
		try
		{
			header = file.getFileHeader(filename);
		}
		catch (ZipException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			//パスワードが違う場合？
			return null;
		}
		
		if(header == null)
		{
//			System.err.println("ディレクトリ内に指定したファイルが存在しません。");
			return null;
		}
		
		try
		{
			return file.getInputStream(header);
		}
		catch (ZipException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			//パスワードが違う場合？
			return null;
		}
	}
}
