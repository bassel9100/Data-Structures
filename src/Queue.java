import java.util.*;

public class Queue<T> implements Iterable<T> {

    private LinkedList<T> ls = new LinkedList<>(); // Our queue will be using linkedlists

    public Queue(){};

    public Queue(T elem){
        enqueue(elem);
    }

    public int size(){
        return ls.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T peek(){ // Return the element in the front of the queue
        if(isEmpty()) throw new RuntimeException("Empty Queue!");
        return ls.peekFirst();
    }

    public T dequeue(){ // Remove elements from the front of the queue
        if(isEmpty()) throw new RuntimeException("Empty Queue!");
        return ls.removeFirst();
    }

    public void enqueue(T elem){
        ls.addLast(elem);
    }

    @Override
    public Iterator<T> iterator(){
        return ls.iterator();
    }
}
