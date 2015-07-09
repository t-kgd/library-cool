package jp.gr.java_conf.kgd.library.cool.io;

import java.util.Collection;

public interface IVirtualDirectoryLoader
{
	//
	boolean addArchiveExtension(String extension);
	boolean removeArchiveExtension(String extension);
	
	Collection<String> getArchiveExtensions();
	
	void setPassword(char[] password);
	void erasePassword();

	//
	IVirtualDirectory openDirectry(String directoryPath);
	IVirtualDirectory openDirectries(String... directoryPaths);
}
