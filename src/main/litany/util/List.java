package litany.util;

import java.util.Iterator;

/**
 * A List is an ordered collection of elements. The user of this interface has
 * precise control over where in the list each element is inserted.
 * 
 * The user can access elements by their integer index (position in the list),
 * and search for elements in the list.
 * 
 * @param <E>
 *            the type of elements in this list
 * 
 * @author Eug Kenny
 */

public interface List<E> extends Iterable<E>{

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param element
     *            element to be appended to this list
     * 
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     */
    public void add(E element);

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * 
     * @param index
     *            index at which the specified element is to be inserted
     * @param element
     *            element to be inserted
     * 
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     *             {@code (index < 0 || index >= size())}
     */
    public void add(int index, E element);

    /**
     * Removes all of the elements from this list.
     */
    public void clear();

    /**
     * Returns {@code true} if this list contains the specified element.
     * 
     * @param o
     *            element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * 
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     */
    public boolean contains(Object o);

    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     *            index of the element to return
     * @return the element at the specified position in this list
     * 
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     *             {@code (index < 0 || index > size())}
     */
    public E get(int index);

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     * 
     * @param element
     *            element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     * 
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     */
    public int indexOf(Object o);

    /**
     * Determines whether this list contains any elements.
     * 
     * @return {@code true} if this list contains no elements, {@code false}
     *         otherwise
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<E> iterator();

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     * 
     * @param index
     *            the index of the element to be removed
     * @return the element previously at the specified position
     * 
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     *             {@code (index < 0 || index >= size())}
     */
    public E remove(int index);

    /**
     * 
     * Removes the first occurrence of the specified element from this list, if
     * it is present. If this list does not contain the element, it is
     * unchanged.
     * 
     * @param o
     *            element to be removed from this list, if present
     * 
     * @return {@code true} if this list contained the specified element
     * 
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     */
    public boolean remove(Object o);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * 
     * @param index
     *            index of the element to replace
     * @param element
     *            element to be stored at the specified position
     * 
     * @return the element previously at the specified position
     * 
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     *             {@code (index < 0 || index >= size())}
     */
    public E set(int index, E element);

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    public int size();

    /**
     * 
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.) The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.
     * 
     * @param fromIndex
     *            low endpoint (inclusive) of the subList
     * @param toIndex
     *            high endpoint (exclusive) of the subList
     * 
     * @return a view of the specified range within this list
     * 
     * @throws IndexOutOfBoundsException
     *             for an illegal endpoint index value (
     *             {@code fromIndex < 0 || toIndex > size || fromIndex > toIndex}
     *             )
     */
    public List<E> subList(int fromIndex, int toIndex);

    /**
     * 
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     * 
     * The returned array will be "safe" in that no references to it are
     * maintained by this list. (In other words, this method must allocate a new
     * array even if this list is backed by an array). The caller is thus free
     * to modify the returned array.
     * 
     * @return an array containing all of the elements in this list in proper
     *         sequence
     */
    public Object[] toArray();
}