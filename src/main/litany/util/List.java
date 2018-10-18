/**
 * 
 */
package litany.util;

import java.util.Iterator;

/**
 * @author ekenny
 *
 */
public interface List<E> extends Iterable<E>{
	
	public void add(E element);
	public void add(int index, E element);
	public void clear();
	public boolean contains(Object o);
	public E get(int index);
	public int indexOf(Object o);
	public boolean isEmpty();
	public Iterator<E> iterator();
	public E remove(int index);
	public boolean remove(Object o);
	public E set(int index, E element);
	public int size();
	public List<E> subList(int fromIndex, int toIndex);
	public Object[] toArray();
	public <T> T[] toArray(T[] arr);
	

}
