package jp.gr.java_conf.kgd.library.cool.jsfml.application;

import jp.gr.java_conf.kgd.library.cool.io.IVirtualDirectoryLoader;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame.IFrameState;

public class ApplicationDataHelper
{
	protected ApplicationDataHelper()
	{
	}
	
	//
	public static final String DIRECTORY_LOADER = "directory_loader";
	
	public static IVirtualDirectoryLoader getDirectoryLoader(IApplicationContext context)
	{
		return (IVirtualDirectoryLoader) context.getApplicationDataBox().
				getObject(DIRECTORY_LOADER);
	}
	
	public static void setDirectoryLoader(IApplicationContext context, IVirtualDirectoryLoader loader)
	{
		context.getApplicationDataBox().putObject(DIRECTORY_LOADER, loader);
	}
	
	
	//
	public static final String COMMON_FRAME_WINDOW_STATE = "common_frame_window_state";
	
	public static IFrameState getCommonFrameWindowState(IApplicationContext context)
	{
		return (IFrameState) context.getApplicationDataBox().getObject(COMMON_FRAME_WINDOW_STATE);
	}
	
	public static void setCommonFrameWindowState(IApplicationContext context, IFrameState frameState)
	{
		context.getApplicationDataBox().putObject(COMMON_FRAME_WINDOW_STATE, frameState);
	}
}
