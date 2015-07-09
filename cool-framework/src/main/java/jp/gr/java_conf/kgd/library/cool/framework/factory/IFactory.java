package jp.gr.java_conf.kgd.library.cool.framework.factory;

import java.util.Map;

public interface IFactory<T> extends IConstFactory<T>, Map<Object, ICreator<T>>
{ 
}
