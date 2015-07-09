package jp.gr.java_conf.kgd.library.cool.jsfml.component;

import java.util.Collection;

public interface IComponentContainer
extends IComponent
{
	Collection<IComponent> getComponents();
	//オブザーバじゃないので変更に弱い。
	
	//内部のコンポーネントの参照に対して外部で変更を加えたことを
	//明示的に伝えて、更新を促す。本来ならばオブザーバで対応したい。
	void dispatchStateChangedEvent();
}
