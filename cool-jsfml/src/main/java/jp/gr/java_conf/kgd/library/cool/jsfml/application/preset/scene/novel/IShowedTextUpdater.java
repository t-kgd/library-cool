package jp.gr.java_conf.kgd.library.cool.jsfml.application.preset.scene.novel;

//
public interface IShowedTextUpdater
{
	IConstShowTextConfig getBindedShowTextConfig();
	void bindShowTextConfig(IConstShowTextConfig config);
	
	String getScheduledString();
	void setScheduledString(String str);
	
	
	void updateShowedText();
	String getCurrentShowedText();
}
