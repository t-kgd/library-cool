package jp.gr.java_conf.kgd.library.cool.jsfml.application;

import java.util.Objects;

public class ApplicationSafeLauncher
implements IApplication
{
	private IApplication application;
	
	public ApplicationSafeLauncher(IApplication application)
	{
		this.application = Objects.requireNonNull(application);
	}
	
	@Override
	public void startApplication()
	{
		this.application.startApplication();
		
		
		//
		
		//
		this.application = null;

		
		//
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		//JSFMLのサウンドリソースが解放されないままVMが終了すると
		//ネイティブのOpenALのエラーが出てしまうので、防止する。
		System.gc();

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		//念入りに
		System.gc();

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
