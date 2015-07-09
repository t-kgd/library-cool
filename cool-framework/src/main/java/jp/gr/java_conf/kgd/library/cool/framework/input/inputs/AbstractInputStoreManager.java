package jp.gr.java_conf.kgd.library.cool.framework.input.inputs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IConstInput;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IInput;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IInputManager;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.InputConfig;

public abstract class AbstractInputStoreManager<T extends IInputManager>
extends InputConfig
implements IInputStateStoreManager
{	
	private ArrayList<T> inputManagers = new ArrayList<>();
	
	//
	protected AbstractInputStoreManager()
	{
	}
	
	//	
	@Override
	public int getInputNum()
	{
		return inputManagers.size();
	}
	
	@Override
	public Collection<? extends IConstInput> getUnmodifiableInputs()
	{
		return Collections.unmodifiableCollection(inputManagers);
	}

	@Override
	public int addInput(final int num)
	{
		if(num <= 0)
		{
			return 0;
		}
				
		int count = 0;
		while(count < num)
		{
			T inputStateManager = createInputManager();
			
			if(inputStateManager != null)
			{
				inputManagers.add(inputStateManager);
				count++;
			}
			else
			{
				break;
			}
		}
		
		return count;
	}
	
	@Override
	public IInput getInput(int no)
	{
		return inputManagers.get(no);
	}


	//
	@Override
	public int removeInput(int num)
	{
		if(num <= 0)
		{
			return 0;
		}
		
		int count = 0;
		int size = inputManagers.size();		
		while(count < num && size > 0)
		{
			if(inputManagers.remove(size - 1) == null)
			{
				break;
			}
		
			count++;
			size = inputManagers.size();
		}
		
		return count;
	}
	
	@Override
	public void updateInputs(boolean hasFocus)
	{
		for(IInputManager inputState : inputManagers)
		{
			inputState.updateKeyStates(hasFocus);
		}
	}

	
	//
	abstract protected T createInputManager();

	protected List<T> getInputManagers()
	{
		return inputManagers;
	}
}

