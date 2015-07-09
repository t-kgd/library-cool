package jp.gr.java_conf.kgd.library.cool.jsfml.application.task;

import java.util.List;

public interface ISequentialTaskContainer
extends ISequentialTask
{
	List<ISequentialTask> getTasks();
	
	boolean addTask(ISequentialTask task);
}
