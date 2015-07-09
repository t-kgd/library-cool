package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Map;

import jp.gr.java_conf.kgd.library.cool.util.function.IFunction;

public interface ISharedKeyMapCreator<E>
{
	<V> Map<E, V> createSharedKeyMap();
	
//	<V> Map<E, V> createSharedKeyMap(IFunction<? extends V> defaultValueCreator);
	<V, S extends V> Map<E, V> createSharedKeyMap(IFunction<? extends S> defaultValueCreator);
	//こうすると、受け手の変数のジェネリクス引数(インターフェースorスーパークラス)と
	//ファクトリの引数（実装クラスorサブクラス）が違っても対応できる。
}
