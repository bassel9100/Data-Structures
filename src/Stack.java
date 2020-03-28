import java.util.Iterator;
import java.util.*;

public class Stack<T> implements Iterable<T> {

    private LinkedList<T> ls = new LinkedList<>();

    public Stack(){}; // Empty constructor

    public Stack(T elem){ // Create an empty stack with a first element
        push(elem);
    }

    public int size(){
        return ls.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void push(T elem){
        ls.addLast(elem);
    }

    public T pop(){
        if(ls.isEmpty()) throw new EmptyStackException();
        return ls.removeLast();
    }

    public T getPeek(){
        if(ls.isEmpty()) throw new EmptyStackException();
        return ls.peekLast();
    }

    @Override
    public Iterator<T> iterator(){
        return ls.iterator();
    }

    // No tostring Method needed for stacks
}
