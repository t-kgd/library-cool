package jp.gr.java_conf.kgd.library.cool.jsfml.application.task;

import java.util.ArrayList;
import java.util.List;

public class ProtectedSequentialTaskContainer
implements ISequentialTask
{
	private List<ISequentialTask> tasks = new ArrayList<>();

	protected ProtectedSequentialTaskContainer()
	{
	}
	
	@Override
	final public boolean updateTask()
	{
		for(ISequentialTask task : tasks)
		{
			if(!task.updateTask())
			{
				return false;
			}
		}
		
		return true;
	}
	
	protected List<ISequentialTask> getTasks()
	{
		return tasks;
	}
	
	protected boolean addTask(ISequentialTask task)
	{
		return tasks.add(task);
	}
}
