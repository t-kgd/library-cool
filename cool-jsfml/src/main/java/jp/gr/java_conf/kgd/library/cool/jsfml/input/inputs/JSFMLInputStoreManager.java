package jp.gr.java_conf.kgd.library.cool.jsfml.input.inputs;

import java.util.Collection;
import java.util.Collections;

import jp.gr.java_conf.kgd.library.cool.framework.input.VirtualKeys;
import jp.gr.java_conf.kgd.library.cool.framework.input.inputs.AbstractInputStoreManager;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.bind.KeyBindHelper;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.IConstJSFMLInput;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.IJSFMLInput;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.IJSFMLInputManager;
import jp.gr.java_conf.kgd.library.cool.jsfml.input.keys.JSFMLInputManager;

public class JSFMLInputStoreManager
extends AbstractInputStoreManager<IJSFMLInputManager>
implements IJSFMLInputStoreManager
{
	public static final int DEFAULT_INPUT_NUM = 1;
	
	public JSFMLInputStoreManager()
	{
		this(DEFAULT_INPUT_NUM);
	}

	public JSFMLInputStoreManager(int inputStateNum)
	{
		getKeyIDs().addAll(VirtualKeys.getAllKeys());
		
		addInput(inputStateNum);
	}

	//
	@Override
	public Collection<? extends IConstJSFMLInput> getUnmodifiableInputs()
	{
		return Collections.unmodifiableCollection(getInputManagers());
	}
	
	@Override
	public IJSFMLInput getInput(int no)
	{
		return getInputManagers().get(no);
	}
	
	@Override
	protected IJSFMLInputManager createInputManager()
	{
		int num = getInputNum();
		if(num >= INPUT_NUM_MAX)
		{
			return null;
		}
		
		IJSFMLInputManager inputStateManager = new JSFMLInputManager(this, num);
		
		KeyBindHelper.setBasicJoyStickKeyBind(inputStateManager);
		
		if(num == 0)
		{
			KeyBindHelper.appendBasicKeybordKeyBind(inputStateManager);
		}
		
		return inputStateManager;
	}
}
