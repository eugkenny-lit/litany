package litany.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>{

    private E[] list;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity){
        if(capacity >= 0){
            list = (E[]) new Object[capacity];
            size = 0;
        } else{
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    @Override
    public void add(E element){
        add(size, element);
    }

    @Override
    public void add(int index, E element){
        checkPositionIndex(index);

        if(size == list.length){
            grow();
        }

        System.arraycopy(list, index, list, index + 1, size - index);

        /*
         * if (index < size) { for (int i = size; i > index; i--) { list[i] =
         * list[i - 1]; } }
         */

        list[index] = element;
        size++;
    }

    private void grow(){
        list = Arrays.copyOf(list, list.length * 2);
        // E[] temp = (E[]) new Object[list.length * 2];
        // System.arraycopy(list, 0, temp, 0, size);
        // list = temp;
    }

    @Override
    public void clear(){
        for(int i = 0; i < size; i++){
            list[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    @Override
    public E get(int index){
        checkElementIndex(index);

        return list[index];
    }

    @Override
    public int indexOf(Object o){
        int index = -1;

        if(o == null){
            for(int i = 0; i < size; i++){
                if(list[i] == null){
                    index = i;
                    break;
                }
            }
        } else{
            for(int i = 0; i < size; i++){
                if(list[i].equals(o)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public Iterator<E> iterator(){
        return new ArrayListIterator();
    }

    @Override
    public E remove(int index){
        checkElementIndex(index);

        E old = list[index];
        fastRemove(index);

        return old;
    }

    @Override
    public boolean remove(Object o){
        boolean removed = false;

        if(o == null){
            for(int index = 0; index < size; index++){
                if(list[index] == null){
                    fastRemove(index);
                    removed = true;
                    break;
                }
            }
        } else{
            for(int index = 0; index < size; index++){
                if(list[index].equals(o)){
                    fastRemove(index);
                    removed = true;
                    break;
                }
            }
        }
        return removed;
    }

    private void fastRemove(int index){
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        list[size] = null;
        size--;
    }

    @Override
    public E set(int index, E element){
        checkElementIndex(index);

        E old = list[index];
        list[index] = element;

        return old;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex){
        // TODO Auto-generated method stub
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Iterator<E> it = iterator();

        while(it.hasNext()){
            sb.append(it.next());
            sb.append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append("]");

        return sb.toString();
    }

    @Override
    public Object[] toArray(){
        return Arrays.copyOf(list, size);
    }

    private void checkElementIndex(int index){
        if(!isElementIndex(index))
            throw new IndexOutOfBoundsException(badIndexMsg(index));
    }

    private void checkPositionIndex(int index){
        if(!isPositionIndex(index))
            throw new IndexOutOfBoundsException(badIndexMsg(index));
    }

    /*
     * Is argument a valid index of an existing element?
     */
    private boolean isElementIndex(int index){
        return(index >= 0 && index < size);
    }

    /*
     * Is argument a valid index for insertion?
     */
    private boolean isPositionIndex(int index){
        return(index >= 0 && index <= size);
    }

    /*
     * Message for invalid index
     */
    private String badIndexMsg(int index){
        return "Index: " + index + ", Size: " + size;
    }

    private class ArrayListIterator implements Iterator<E>{

        private int index;

        @Override
        public boolean hasNext(){
            return index != size;
        }

        @Override
        public E next(){
            E next;
            if(index < list.length){
                next = list[index];
                index++;
            } else{
                throw new NoSuchElementException();
            }

            return next;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
}