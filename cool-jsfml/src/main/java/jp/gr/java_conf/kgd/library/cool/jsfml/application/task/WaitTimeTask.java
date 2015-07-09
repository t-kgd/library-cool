package jp.gr.java_conf.kgd.library.cool.jsfml.application.task;

public class WaitTimeTask
implements ISequentialTask
{
	private final int waitTime;
	private int countTime;

	public WaitTimeTask(int waitTime)
	{
//		this.waitTime = Math.max(0, waitTime);
		this.waitTime = waitTime;
	}
	
	@Override
	public boolean updateTask()
	{
		if(countTime >= waitTime)
		{
			return true;
		}
		
		countTime++;
		return false;
	}
}