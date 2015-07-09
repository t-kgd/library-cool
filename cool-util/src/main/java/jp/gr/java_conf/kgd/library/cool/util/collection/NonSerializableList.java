package jp.gr.java_conf.kgd.library.cool.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class NonSerializableList<E>
implements List<E>
{
	private List<E> impl;
	
	public NonSerializableList()
	{
		this(new LinkedList<E>());
	}
	
	public NonSerializableList(List<E> implList)
	{
		this.impl = Objects.requireNonNull(implList);
	}

	public int size()
	{
		return impl.size();
	}

	public boolean isEmpty()
	{
		return impl.isEmpty();
	}

	public boolean contains(Object o)
	{
		return impl.contains(o);
	}

	public Iterator<E> iterator()
	{
		return impl.iterator();
	}

	public Object[] toArray()
	{
		return impl.toArray();
	}

	public <T> T[] toArray(T[] a)
	{
		return impl.toArray(a);
	}

	public boolean add(E e)
	{
		return impl.add(e);
	}

	public boolean remove(Object o)
	{
		return impl.remove(o);
	}

	public boolean containsAll(Collection<?> c)
	{
		return impl.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c)
	{
		return impl.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends E> c)
	{
		return impl.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c)
	{
		return impl.removeAll(c);
	}

	public boolean retainAll(Collection<?> c)
	{
		return impl.retainAll(c);
	}

	public void clear()
	{
		impl.clear();
	}

	public boolean equals(Object o)
	{
		return impl.equals(o);
	}

	public int hashCode()
	{
		return impl.hashCode();
	}

	public E get(int index)
	{
		return impl.get(index);
	}

	public E set(int index, E element)
	{
		return impl.set(index, element);
	}

	public void add(int index, E element)
	{
		impl.add(index, element);
	}

	public E remove(int index)
	{
		return impl.remove(index);
	}

	public int indexOf(Object o)
	{
		return impl.indexOf(o);
	}

	public int lastIndexOf(Object o)
	{
		return impl.lastIndexOf(o);
	}

	public ListIterator<E> listIterator()
	{
		return impl.listIterator();
	}

	public ListIterator<E> listIterator(int index)
	{
		return impl.listIterator(index);
	}

	public List<E> subList(int fromIndex, int toIndex)
	{
		return impl.subList(fromIndex, toIndex);
	}
	
	
}
