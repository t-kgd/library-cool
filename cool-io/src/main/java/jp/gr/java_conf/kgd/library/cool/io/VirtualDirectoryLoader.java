package jp.gr.java_conf.kgd.library.cool.io;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class VirtualDirectoryLoader
implements IVirtualDirectoryLoader, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4922701423729822312L;
	
	//
	private Set<String> archiveExtensions = new LinkedHashSet<>();
	private char[] password;
	
	//
	public VirtualDirectoryLoader()
	{
		archiveExtensions.add("zip");
	}
	
	public boolean addArchiveExtension(String extension)
	{
		return archiveExtensions.add(extension);
	}
	
	public boolean removeArchiveExtension(String extension)
	{
		return archiveExtensions.remove(extension);
	}
	
	public Collection<String> getArchiveExtensions()
	{
		return Collections.unmodifiableCollection(archiveExtensions);
	}
	
	public void setPassword(char[] password)
	{
		this.password = password;
	}
	
	@Override
	public void erasePassword()
	{
		Random rand = new Random();
		
		for(int i = 0; i < password.length; ++i)
		{
			password[i] = (char) rand.nextInt(Character.MAX_VALUE);
		}
		
		rand = null;
	}
	
	
	//
	public IVirtualDirectory openDirectry(String directoryPath)
	{
		//普通にフォルダがあるかどうか探す。
		{
			File file = new File(directoryPath);

			if(file.exists())
			{
				return new FileDirectory(file);
			}
		}
		
		
		//フォルダがなかった時、アーカイブを探す。
		File file = null;
		
		for(String extension : archiveExtensions)
		{
			file = new File(directoryPath + "." + extension);
			
			if(file.exists())
			{
				//見つかった。
				break;
			}
		}
		
		if(!file.exists())
		{
			//結局なかったとき。
			return null;
		}
		
		
		ZipFile zipFile;
		try
		{
			zipFile = new ZipFile(file);
		}
		catch (ZipException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			//Fileの存在は確認しているので基本的にこっちには来ないはず。
			//「zipじゃない例外」で来る可能性はアリ。
			return null;
		}
		
		try
		{
			if(zipFile.isEncrypted())
			{
				if(password == null)
				{
//					System.err.println("指定したアーカイブは暗号化されているのに、パスワードが設定されていません。");
					
					return null;
				}
				
				zipFile.setPassword(password);
			}
		}
		catch (ZipException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
			return null;
		}
		
		return new ZipDirectory(zipFile);
	}
	
	@Override
	public IVirtualDirectory openDirectries(String... directoryPaths)
	{
		IVirtualDirectory directory = null;
		
		for(String path : directoryPaths)
		{
			directory = openDirectry(path);
			
			if(directory != null)
			{
				break;
			}
		}
		
		return directory;
	}
}
