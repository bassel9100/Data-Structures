import java.util.Iterator;

public class StaticArray<T> implements Iterable<T> {

    private T[] arr;
    private int n = 0; // The length user thinks the array is
    private int size = 0; // Actual size of the array

    public StaticArray(){ this(20); } // Size of array is 20 if no other size was given.

    public StaticArray(int size){
        if(size < 0) throw new IllegalArgumentException("Size cannot be negative, Entered size: " + size);
        this.size = size;
        arr = (T[]) new Object[size];
    }

    public int size(){ return n; } // Bounced check needed eventually
    public boolean isEmpty() { return size() == 0; }

    public T get(int x){ return arr[x]; } // Return element in position "x"
    public void set(int x, T elem){ arr[x] = elem; }

    public void clear(){
        for(int i = 0; i < size; i++)
            arr[i] = null;
        n = 0;
    }

    public void add(T elem){
        if(n+1 >= size){ // If n+1 is equal to the actual size of the array then we want to double the size of the array
            if(size == 0) size = 1; // Special case
            else size *= 2;
            T[] newArr = (T[]) new Object[size];
            for(int i = 0; i < n; i++)
                newArr[i] = arr[i];
            arr = newArr;
        }

        arr[n++] = elem;

    }

    public T removeAt(int x){ // Removes item from position "x" and shifts the array to the left.
        if(x >= n || x < 0) throw new IndexOutOfBoundsException(); // Check if index is valid
        T elem = arr[x];
         T[] newArr = (T[]) new Object[n-1];
         for(int i = 0, j = 0; i < n; i++, j++){ // Loop until i == x then skip one place by decreasing j by one.
            if(i == x) j--;
            else newArr[j] = arr[i];
         }
         arr = newArr;
         size = --n;
         return elem;
    }

    public void remove(Object obj){
        for(int i = 0; i < n; i++){
            if(arr[i].equals(obj))
                removeAt(i);
        }
    }

    public int indexOf(Object obj){
        for(int i = 0; i < n; i++)
            if(arr[i].equals(obj))
                return i;
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() { return index < n; }
            @Override
            public T next() { return arr[index++]; }
        };
    }

    @Override
    public String toString(){
        if(n == 0) return "[]";
        else{
            StringBuilder sb = new StringBuilder(n).append("[");
            for(int i = 0; i < n-1; i++)
                sb.append(arr[i] + ", ");
            return sb.append(arr[n-1] + "]").toString();
        }
    }
}