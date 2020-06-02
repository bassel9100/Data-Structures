import java.util.*;
/**
 * Priority Queue that implements a binary heap.
 * @author basselkanaan, bassel.9100@gmail.com
 **/

public class PrioQueue<T extends Comparable<T>> {

    private int size = 0; // Number of current elements inside the binary heap
    private int capacity = 0; // The total capacity of the binary heap
    private ArrayList<T> heap;
    private HashMap<T, TreeSet<Integer>> map = new HashMap<>(); // We use a hashmap to minimize removing and contain function time from the binary heap
    // All element positions will be in the TreeSet. We used a set here to keep track if the heap contains multiple nodes of the same value.

    // Empty priority queue
    public PrioQueue(){};

    public PrioQueue(int size){
        heap = new ArrayList<>(size);
    }

    // Create a priority queue from existing elements from an array
    // O(n) time complexity
    public PrioQueue(T[] arr){
        size = arr.length;
        capacity = arr.length;
        heap = new ArrayList<>(capacity);

        for(int i = 0; i < size; i++){
            heap.add(arr[i]); // Add all elements to the heap and to the hashmap
            addMap(arr[i], i);
        }
    }

    private List<?> ls = new LinkedList<>();
}
