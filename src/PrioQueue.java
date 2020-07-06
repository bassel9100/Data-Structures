import java.util.ArrayList;
import java.util.HashMap;;
import java.util.Set;
import java.util.TreeSet;
/**
 * Min Priority Queue that implements a binary heap.
 * @author basselkanaan, bassel.9100@gmail.com
 **/

public class PrioQueue<T extends Comparable<T>> {

    private int size = 0; // Number of current elements inside the binary heap
    private int capacity = 0; // The total capacity of the binary heap
    private ArrayList<T> heap = null;
    private HashMap<T, TreeSet<Integer>> map = new HashMap<>(); // We use a hashmap to minimize removing and contain function time from the binary heap
    // All element positions will be in the TreeSet. We used a set here to keep track if the heap contains multiple nodes of the same value.

    // Empty priority queue
    public PrioQueue() {
    }

    public PrioQueue(int size) {
        heap = new ArrayList<>(size);
    }

    // Create a priority queue from existing elements from an array
    // O(n) time complexity
    public PrioQueue(T[] arr) {
        size = arr.length;
        capacity = arr.length;
        heap = new ArrayList<>(capacity);

        for (int i = 0; i < size; i++) {
            addMap(arr[i], i);
            heap.add(arr[i]); // Add all elements to the heap and to the heap
        }

        // Bubble Down O(n)
        for (int i = Math.max(0, (size / 2) - 1); i >= 0; i--)
            bubbleDown(i);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {    // Remove all elements from the queue
        for (int i = 0; i < capacity; i++)
            heap.set(i, null);
        size = 0;
        map.clear();
    }

    public int size() {
        return size;
    }

    // Returns the value with the lowest priority
    public T peek() {
        if (isEmpty())
            return null;
        return heap.get(0);
    }

    // Removes the root of the queue O(log(n))
    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T x) {
        if (x == null)
            return false;
        return map.containsKey(x); //O(1), a linear search would have taken O(n)
    }

    public void add(T x) {
        if (x == null)
            throw new IllegalArgumentException();
        if (size < capacity) {
            heap.set(size, x);
        } else {
            heap.add(x);
            capacity++;
        }
        addMap(x, size);

        bubbleUp(size);
        size++;
    }

    // Helper method that lets you check if node x is less than node y
    private boolean less(int x, int y) {
        return (heap.get(x)).compareTo(heap.get(y)) <= 0;
    }

    // Node will bubble up O(log n)
    private void bubbleUp(int x) {
        int parent = (x - 1) / 2; // index of parent node to x
        while (x > 0 && less(x, parent)) { // Keep bubbling up while not have reached root and less than parent
            swap(parent, x);
            x = parent;
            parent = (x - 1) / 2;
        }
    }

    public void bubbleDown(int x) {
        while (true) {
            int left = 2 * x + 1; //Left node
            int right = 2 * x + 2; //Right node
            int min = left; // Assume the left node is smallest

            if (right < size && less(right, left))
                min = right;

            if (left >= size || less(x, min))  // Stop if you cant bubbleDown anymore
                break;
            swap(min, x);
            x = min;
        }
    }

    //Swap two nodes x and y
    private void swap(int x, int y) {
        T one = heap.get(x);
        T two = heap.get(y);

        heap.set(x, one);
        heap.set(y, two);

        swapMap(one, two, x, y);
    }

    public boolean remove(T x) {
        if (x == null)
            return false;
        Integer indx = getMap(x);
        if (indx != null)        // O(log(n)) removal
            removeAt(indx);
        return indx != null;
    }

    private T removeAt(int x) {
        if (isEmpty())
            return null;
        size--;
        T removed = heap.get(x);
        swap(x, size);
        heap.set(size, null);
        removeMap(removed, size);
        if (x == size)
            return removed;
        T elem = heap.get(x);
        bubbleDown(x);
        if (heap.get(x).equals(elem))
            bubbleUp(x);
        return removed;
    }

    // Add a node value and index to map
    private void addMap(T x, int i) {
        TreeSet<Integer> set = map.get(x);
        if (set == null) { // insert new value
            set = new TreeSet<>();
            set.add(i);
            map.put(x, set);
        } else
            set.add(i);
    }

    private void removeMap(T x, int i) {
        TreeSet<Integer> set = map.get(x);
        set.remove(i); //=(log(n)) remove time because it of treeset
        if (set.size() == 0)
            map.remove(x);
    }

    private Integer getMap(T x) {
        TreeSet<Integer> set = map.get(x);
        if (set != null)
            return set.last();
        return null;
    }

    // Swap index of two nodes
    private void swapMap(T x, T y, int one, int two) {

        Set<Integer> set1 = map.get(x);
        Set<Integer> set2 = map.get(y);

        set1.remove(one);
        set2.remove(two);

        set1.add(two);
        set2.add(one);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}