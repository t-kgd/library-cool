package jp.gr.java_conf.kgd.library.cool.jsfml.application.util;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstFont;

import jp.gr.java_conf.kgd.library.cool.framework.input.VirtualKeys;
import jp.gr.java_conf.kgd.library.cool.framework.input.keys.IConstInput;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.IActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.IRectangleTextActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.ITextActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.RectangleTextActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.base.TextActor;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line.HorizontalLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line.IHorizontalLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line.IVerticalLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.line.VerticalLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.ratio.IRatioLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.layout.ratio.RatioLayout;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame.FrameWindow;
import jp.gr.java_conf.kgd.library.cool.jsfml.actor.window.frame.IFrameWindow;
import jp.gr.java_conf.kgd.library.cool.jsfml.application.scene.AbstractScene;
import jp.gr.java_conf.kgd.library.cool.jsfml.component.DrawStateElement;
import jp.gr.java_conf.kgd.library.cool.util.IConstObjectBox;

public class CloseWindowDialogScene
extends AbstractScene
{
	private IRatioLayout rootLayout = new RatioLayout();
	
	private IFrameWindow window = new FrameWindow();
	
	private IVerticalLayout verticalLayout = new VerticalLayout();
	private IHorizontalLayout horizontalLayout = new HorizontalLayout();
	
	private IRectangleTextActor textTitle = new RectangleTextActor();
	private ITextActor textOK = new TextActor();
	private ITextActor textCancel = new TextActor();
	
	
	
	
//	private Font baseFont;
	
	//
	private int currentSelect = 0;
	
	
	public CloseWindowDialogScene()
	{
	}
	
	public CloseWindowDialogScene(ConstFont font)
	{
		setTextFont(font);
	}
	
	

	@Override
	public void startScene(IConstObjectBox args)
	{
		//
		textTitle.setString("ウインドウを閉じてよろしいですか？");
		textTitle.setTextColor(Color.WHITE);
		textTitle.setFillColor(new Color(64, 64, 64, 64));
		
		textOK.setString("はい");
		textOK.setTextColor(Color.WHITE);
		
		textCancel.setString("いいえ");
		textCancel.setTextColor(Color.WHITE);
		
		//
		horizontalLayout.setMargin(16);		
		horizontalLayout.addActor(textOK);
		horizontalLayout.addActor(textCancel);
		
		//
		verticalLayout.setMargin(8);		
		verticalLayout.addActor(textTitle);
		verticalLayout.addActor(horizontalLayout);
		
		//
		window.setFrameSize(2);
		window.getBackgroundColorState().setColorMask(new Color(64, 64, 64));
		window.getBackgroundColorState().setAlphaMaskRate(0.75f);
		window.setInnerActor(verticalLayout);
		
		window.getShakeAnimationState().setEnabled(DrawStateElement.SCALE, true);
		window.getShakeAnimationState().setShakeTimer(DrawStateElement.SCALE, 30);
		
		//
		rootLayout.setLayoutOriginRatio(0.5f, 0.5f);
		rootLayout.addActor(window, 0.5f, 0.5f, 0.5f, 0.3f);
		
		//
		setRootActor(rootLayout);
		
		//
		focusControll(textOK, true);
		focusControll(textCancel, false);
	}

	public void setTextFont(ConstFont font)
	{
		textTitle.setFont(font);
		textOK.setFont(font);
		textCancel.setFont(font);
	}
	
	@Override
	public void resumeScene(int registerdID, int resultCode,
			IConstObjectBox resultValues)
	{
	}

	@Override
	protected void onUpdateScene()
	{
		IConstInput input = getApplicationContext().getInputStore().getInput(0);
		
		if(input.getKeyState(VirtualKeys.LEFT).isPressed())
		{
			currentSelect = 0;
			
			focusControll(textOK, true);
			focusControll(textCancel, false);
		}
		else if(input.getKeyState(VirtualKeys.RIGHT).isPressed())
		{
			currentSelect = 1;
			
			focusControll(textOK, false);
			focusControll(textCancel, true);
		}
		
		if(input.getKeyState(VirtualKeys.OK).isPressed())
		{
			if(currentSelect == 0)
			{
				getApplicationContext().endApplication();
			}
			else
			{
				finishScene();
			}
		}
		
		if(input.getKeyState(VirtualKeys.CANCEL).isPressed())
		{
			finishScene();
		}
	}
	
	//
	private final void focusControll(IActor actor, boolean flag)
	{
		actor.getMaskShapeState().setVisible(flag);
		actor.getMaskShapeState().setBlinkEnabled(flag);
	}
}
