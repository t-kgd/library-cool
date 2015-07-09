package jp.gr.java_conf.kgd.library.cool.jsfml.component.layout.line;

public interface ILineLayoutState
extends IConstLineLayoutState
{
	void setMajorOrderHorizontal(boolean flag);
	
	void setOrderNum(int orderNum);
	
	void setMarginWidth(float width);

	void setMarginHeight(float height);
}
