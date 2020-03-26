public class StaticArray <T> implements Iterable<T> {

    private T[] arr;
    private int n = 0;
    private int size = 0;

    public StaticArray(){ this(20); } //Size of array is 20 if no other size was given.

    public StaticArray(int size){
        if(size < 0) throw new IllegalArgumentException("Size cannot be negative, Entered size: " + size);
        this.size = size;
        arr = (T[]) new Object[size];
    }

    public int size(){ return size; }
    public boolean isEmpty() { return size == 0; }

    public T get(int x){ return arr[x]; } //Return element in position "x"
    public void set(int x, T elem){ arr[x] = elem; }

    public void clear(){
        for(int i = 0; i < size; i++)
            arr[i] = null;
        n = 0;
    }

    

}