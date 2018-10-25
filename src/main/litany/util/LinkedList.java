package litany.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Eug Kenny
 *
 */
/**
 * @author ekenny
 * 
 * @param <E>
 */
public class LinkedList<E> implements List<E>{

    private Node<E> first;
    private Node<E> last;

    private int size;

    public LinkedList(){
        first = last = null;
        size = 0;
    }

    @Override
    public void add(E element){
        addLast(element);
    }

    @Override
    public void add(int index, E element){
        checkPositionIndex(index);

        if(index == 0){
            addFirst(element);
        } else if(index == size){
            addLast(element);
        } else{
            addBefore(element, nodeAt(index));
        }
    }

    private void addFirst(E element){
        Node<E> oldFirst = first;
        Node<E> newFirst = new Node<E>(element, null, oldFirst);
        first = newFirst;

        if(oldFirst == null){
            last = newFirst;
        } else{
            oldFirst.prev = newFirst;
        }
        size++;
    }

    private void addBefore(E element, Node<E> nextNode){
        Node<E> prevNode = nextNode.prev;
        Node<E> newNode = new Node<E>(element, prevNode, nextNode);
        nextNode.prev = newNode;
        prevNode.next = newNode;
        size++;
    }

    private void addLast(E element){
        Node<E> oldLast = last;
        Node<E> newLast = new Node<E>(element, oldLast, null);
        last = newLast;

        if(oldLast == null){
            first = newLast;
        } else{
            oldLast.next = newLast;
        }
        size++;
    }

    @Override
    public void clear(){
        first = last = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    @Override
    public E get(int index){
        checkElementIndex(index);

        return nodeAt(index).element;
    }

    @Override
    public int indexOf(Object o){
        int index = -1;
        int currentIndex = 0;

        if(o == null){
            for(Node<E> current = first; current != null; current = current.next){
                if(current.element == null){
                    index = currentIndex;
                    break;
                }
                currentIndex++;
            }
        } else{
            for(Node<E> current = first; current != null; current = current.next){
                if(o.equals(current.element)){
                    index = currentIndex;
                    break;
                }
                currentIndex++;
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
        return new LinkedListIterator();
    }

    @Override
    public E remove(int index){
        checkElementIndex(index);
        E element;

        if(index == 0){
            element = removeFirst();
        } else if(index == size - 1){
            element = removeLast();
        } else{
            element = remove(nodeAt(index));
        }
        return element;
    }

    @Override
    public boolean remove(Object o){
        boolean removed = false;

        if(o == null){
            for(Node<E> current = first; current != null; current = current.next){
                if(current.element == null){
                    remove(current);
                    removed = true;
                    break;
                }
            }
        } else{
            for(Node<E> current = first; current != null; current = current.next){
                if(o.equals(current.element)){
                    remove(current);
                    removed = true;
                    break;
                }
            }
        }

        return removed;
    }

    private E removeFirst(){
        Node<E> oldFirst = first;
        if(oldFirst == null){
            throw new NoSuchElementException();
        }
        E element = oldFirst.element;

        Node<E> newFirst = oldFirst.next;

        oldFirst.element = null;
        oldFirst.next = null;

        first = newFirst;

        if(newFirst == null){
            last = null;
        } else{
            newFirst.prev = null;
        }
        size--;

        return element;
    }

    private E removeLast(){
        Node<E> oldLast = last;
        if(oldLast == null){
            throw new NoSuchElementException();
        }
        E element = oldLast.element;

        Node<E> newLast = oldLast.prev;

        oldLast.element = null;
        oldLast.prev = null;

        last = newLast;

        if(newLast == null){
            first = null;
        } else{
            newLast.next = null;
        }
        size--;

        return element;
    }

    private E remove(Node<E> removedNode){
        E element = removedNode.element;

        Node<E> nextNode = removedNode.next;
        Node<E> prevNode = removedNode.prev;

        if(prevNode == null){
            first = nextNode;
        } else{
            prevNode.next = nextNode;
            removedNode.prev = null;
        }

        if(nextNode == null){
            last = prevNode;
        } else{
            nextNode.prev = prevNode;
            removedNode.next = null;
        }
        removedNode.element = null;

        size--;
        return element;
    }

    @Override
    public E set(int index, E element){
        checkElementIndex(index);

        Node<E> x = nodeAt(index);
        E oldVal = x.element;
        x.element = element;

        return oldVal;
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

    @Override
    public Object[] toArray(){
        Object[] array = new Object[size];
        int i = 0;
        for(Node<E> current = first; current != null; current = current.next){
            array[i] = current.element;
            i++;
        }
        return array;
    }

    /*
     * public boolean equals(Object o){ if(o == this){ return true; } if(!(o
     * instanceof List)){ return false; }
     * 
     * if (this.size != ((List<?>)o).size()){ return false; }
     * 
     * Iterator<E> it1 = iterator(); Iterator<E> it2 = return false; }
     */

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

    private Node<E> nodeAt(int index){
        if(index < (size / 2)){
            Node<E> current = first;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            return current;
        } else{
            Node<E> current = last;
            for(int i = size - 1; i > index; i--){
                current = current.prev;
            }
            return current;
        }
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

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(E element, Node<E> prev, Node<E> next){
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<E>{

        private Node<E> current = first;

        @Override
        public boolean hasNext(){
            return current != null;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

        @Override
        public E next(){
            E item = current.element;
            current = current.next;
            return item;
        }
    }
}