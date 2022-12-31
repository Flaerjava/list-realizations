

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.*;

public class LinkedList<T> {

    // node class
    static class Node<T> {
        private T element;

        private Node<T> next;


        public Node(T element){
            this.element = element;

        }


    }

    private int size;

    private Node<T> last;
    private Node<T> first;

    public Node<T> getCurrentNode(int index){
        Objects.checkIndex(index, size);
        Node<T> currentNode = new Node(null);
        if (index == 0){
            currentNode = first;
        } else if (index == size-1){
            currentNode = last;
        } else {
            currentNode = first;
            for (int i = 0; i < index; i++){
                currentNode = currentNode.next;
            }
        }

        return currentNode;
    }


    public void add(T element){
        Node<T> newNode = new Node(element);
        if (size == 0){
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void add(int index, T element){
        Objects.checkIndex(index,size+1);

        Node<T> node = new Node<>(element);
        if (index == 0){
            node.next = first;
            first = node;
        } else if (index == size){
            add(element);
        } else {
            Node<T> prevNode = getCurrentNode(index-1);
            node.next = getCurrentNode(index);
            prevNode.next = node;

        }
        size++;
    }


    public void addAll(Collection<? extends T> coll){
        for (T elem: coll){
            add(elem);
        }
    }

    public void addAll(int index, List<? extends T> coll){
        Objects.checkIndex(index,size+1);

        if (index == size){
            addAll(coll);
        } else if (index == 0){
            Collections.reverse(coll);
            for (T elem : coll){
                add(0,elem);
            }
        } else {
            Collections.reverse(coll);
            for (T elem : coll){
                add(index, elem);
            }
        }
    }

    public void remove(T element){
        Node<T> currentNode = first;
        if (!first.element.equals(element)){
            for (int i = 0; i < size; i++) {
                if (currentNode.next.element.equals(element)) {
                    break;
                }
                currentNode = currentNode.next;
            }

            currentNode.next = currentNode.next.next;
        } else {
            first = first.next;
        }
        size--;

    }

    public void remove(int index){
        Objects.checkIndex(index,size);
        if (index == 0){
            remove(get(0));
        } else if (index == size-1){
            Node<T> prev = getCurrentNode(index-1);
            prev.next = null;
            last = prev;
        } else {
            Node<T> prev = getCurrentNode(index-1);
            prev.next = prev.next.next;
        }
        size--;
    }

    public void clear(){
        first = last = null;
    }

    public boolean isEmpty(){
        return first == null ? true : false;
    }

    public boolean contains(T element){
        Node<T> current = first;
        for (int i = 0; i < size; i++){
            if (current.element.equals(element)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T peekFirst(){
        return first.element;
    }

    public T peekLast(){
        return last.element;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        return getCurrentNode(index).element;
    }

    public void set(int index,T element){
        getCurrentNode(index).element = element;
    }


    public String toString(){
        String string = "";
        Node<T> current = first;
        for (int i = 0; current != null; i++){
            string += current.element + " ";
            current = current.next;
        }

        return string.trim();
    }







}
