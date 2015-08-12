package baseClasses;

import java.util.Collection;

/**
 * An Iterator that let's one access to the current object of the iterator. 
 * (Convenient when accessing to a final Iterator in a anonymous class)
 * @author domme
 *
 * @param <T>
 */
public class Iterator <T>{

	private T current;
	private java.util.Iterator<T> iterator;
	
	public Iterator(Collection<T> list) {
		this.iterator = list.iterator();
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}

	public T next() {
		current = iterator.next();
		return current;
	}

	public void remove() {
		iterator.remove();
	}

	public T getCurrent() {
		return current;
	}
}
