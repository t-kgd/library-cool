package jp.gr.java_conf.kgd.library.cool.jsfml.application.preset.scene.novel;

public class ShowTextConfig
implements IShowTextConfig
{
	private int textLineCharacterSize = 1;
	private int textLineNum = 1;
	
	private float timePerCharacter = 1;

	
	@Override
	public int getTextLineCharacterSize()
	{
		return textLineCharacterSize;
	}

	@Override
	public int getTextLineNum()
	{
		return textLineNum;
	}

	@Override
	public float getTimePerCharacter()
	{
		return timePerCharacter;
	}

	@Override
	public void setTextLineCharacterize(int size)
	{
		this.textLineCharacterSize = size;
	}

	@Override
	public void setTextLineNum(int num)
	{
		this.textLineNum = num;
	}

	@Override
	public void setTimePerCharacter(float time)
	{
		this.timePerCharacter = time;
	}
}
