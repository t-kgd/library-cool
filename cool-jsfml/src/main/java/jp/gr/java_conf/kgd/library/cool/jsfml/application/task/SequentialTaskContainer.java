package jp.gr.java_conf.kgd.library.cool.jsfml.application.task;

import java.util.List;

public class SequentialTaskContainer
extends ProtectedSequentialTaskContainer
implements ISequentialTaskContainer
{
	@Override
	public List<ISequentialTask> getTasks()
	{
		return super.getTasks();
	}
	
	@Override
	public boolean addTask(ISequentialTask task)
	{
		return super.addTask(task);
	}
}
