package jp.gr.java_conf.kgd.library.cool.io;

import java.io.File;
import java.io.InputStream;

public interface IVirtualDirectory
{
	File toFile();
	
	InputStream getInputStream(String filename);
}
