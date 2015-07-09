package jp.gr.java_conf.kgd.library.cool.jsfml.application.task;

import java.util.LinkedHashMap;
import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.util.function.IBooleanFunction;

public abstract class AbstractSequentialTask
implements ISequentialTask
{
	private Map<IBooleanFunction, ISequentialTask> interruptTaskMap = new LinkedHashMap<>();
	private ISequentialTask currentInterruptTask;

	@Override
	public boolean updateTask()
	{
		if(currentInterruptTask == null)
		{
			for(Map.Entry<IBooleanFunction, ISequentialTask> entry : interruptTaskMap.entrySet())
			{
				if(entry.getKey().apply())
				{
					currentInterruptTask = entry.getValue();
					break;
				}
			}
		}
		
		if(currentInterruptTask != null)
		{
			if(currentInterruptTask.updateTask())
			{
				currentInterruptTask = null;
			}
			else
			{
				return false;
			}
		}
		
		return onUpdateTask();
	}
	
	
	//
	protected Map<IBooleanFunction, ISequentialTask> getInterruptTaskMap()
	{
		return interruptTaskMap;
	}
	
	//
	abstract protected boolean onUpdateTask();
}
