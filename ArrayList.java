import java.util.List;


/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> {

    private Object [] elementsData;

    private int size;
    private int capacity;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0){
            throw new IllegalArgumentException();
        }
        elementsData = new Object[initCapacity];
        capacity = initCapacity;
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        capacity = 5;
        elementsData = new Object[capacity];

    }


    private void resize(){
        Object [] tempElementsData = new Object[capacity*3/2+1];
        System.arraycopy(elementsData,0,tempElementsData,0,size);
        elementsData = tempElementsData;
        capacity = capacity*3/2+1;
    }

    private boolean resizeDown(){
        if (size < capacity/3*2-1) {
            Object[] tempElementsData = new Object[capacity / 3 * 2 - 1];
            System.arraycopy(elementsData, 0, tempElementsData, 0, size);
            elementsData = tempElementsData;
            capacity = capacity / 3 * 2 - 1;
            return true;
        }
        return false;
    }


    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */

    public void add(T element) {
        if (size == capacity){
            resize();
        }
        elementsData[size++] = element;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    public void add(int index, T element) {
        if (size == capacity){
            resize();
        }

        if (index == 0){
            System.arraycopy(elementsData,0,elementsData,1,size);
            elementsData[0] = element;
            size++;
        } else if (index < size && index > 0){
            System.arraycopy(elementsData,index,elementsData,index+1,size-index);
            elementsData[index] = element;
            size++;
        } else if (index == size){
            add(element);
        } else {
            throw new IndexOutOfBoundsException();
        }


    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    public T get(int index) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        return (T)elementsData[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */

    public T getFirst() {
        return (T) elementsData[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    public T getLast() {
        return (T) elementsData[size-1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    public void set(int index, T element) {
        if (index < 0 || index > size-1){
            throw new IndexOutOfBoundsException();
        }

        elementsData[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    public void remove(int index) {
        if (index < 0 || index > size-1){
            throw new IndexOutOfBoundsException();
        }

        if (index == 0){
            System.arraycopy(elementsData,1,elementsData,0,--size);
        } else if (index == size-1){
            elementsData[index] = null;
            size--;
        } else if (index > 0 && index < size-1){
            System.arraycopy(elementsData,index+1,elementsData,index,size-index);
            size--;
        }
        resizeDown();
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    public boolean contains(T element) {
        for (Object elem : elementsData){
            if (elem.equals(element)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return amount of saved elements
     */

    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */

    public void clear() {
        elementsData = new Object[5];
    }

    public String toString(){
        String string = "";
        for (Object elem : elementsData){
            string += elem + " ";
        }
        return string;
    }
}
