package jp.gr.java_conf.kgd.library.cool.jsfml.application;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

public class ApplicationHelper
{
	protected ApplicationHelper()
	{
	}
	
	//
//	public static void setDefaultWindowRectRate(IApplicationInitialConfig config, Vector2i windowSize)
//	{
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		
//		float widthRate = (float)windowSize.x / screenSize.width;
//		float heightRate = (float)windowSize.y / screenSize.height;
//		
//		float xRate = (1.0f - widthRate) / 2;
//		float yRate = (1.0f - heightRate) / 2;
//		
//		config.setWindowRectRate(new FloatRect(xRate, yRate, widthRate, heightRate));
//	}
//	
//	public static IConstApplicationInitialConfig getDefaultApplicationInitialConfig(Vector2i windowSize)
//	{
//		IApplicationInitialConfig config = new ApplicationInitialConfig();
//		setDefaultWindowRectRate(config, windowSize);
//		
//		return config;
//	}
	
	public static IntRect getRelativeWindowRect(FloatRect rectRate)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		return new IntRect(
				(int)(screenSize.width * rectRate.left), (int)(screenSize.height * rectRate.top),
				(int)(screenSize.width * rectRate.width), (int)(screenSize.height * rectRate.height));
	}
	
	public static void setWindowPositionToCenter(Window window)
	{
		Dimension desktopSize = Toolkit.getDefaultToolkit().getScreenSize();
		Vector2i windowSize = window.getSize();
		
		int diffX = desktopSize.width - windowSize.x;
		int diffY = desktopSize.height - windowSize.y;
		
		window.setPosition(new Vector2i(diffX / 2, diffY / 2));
	}
	
	
	public static void sleepAndGC(int sleep)
	{
		System.gc();
		
		try
		{
			Thread.sleep(sleep);
		}
		catch (InterruptedException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
