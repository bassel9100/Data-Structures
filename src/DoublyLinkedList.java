import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null; // Empty at beginning
    private Node<T> tail = null;

    // Inner class to represent a Node
    private class Node<T> {
        T x;
        Node<T> prev, next;
        public Node(T x, Node <T> next, Node<T> prev){
            this.x = x;
            this.next = next;
            this.prev = prev;
        }
        @Override
        public String toString(){
            return x.toString();
        }
    }

    // All methods for DoublyLinkedList
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    // Clear the linked list, takes O(n) we need to traverse each node
    public void clear(){
        Node<T> tempNode = head;
        while(tempNode != null){
            Node<T> nextNode = tempNode.next;
            tempNode.prev = tempNode.next = null;
            tempNode.x = null;
            tempNode = nextNode;
        }
        tempNode = head = tail = null;
        size = 0;
    }

    public void addFirst(T x){
        if(isEmpty()){ // If the list is empty
            head = tail = new Node<T> (x, null, null);
        } else{
            head.prev = new Node<T>(x, null, head);
            head = head.prev;
        }

        size++;
    }

    public void addLast(T x){
        if(isEmpty()){
            head = tail = new Node<T>(x, null, null);
        } else{
            tail.next = new Node<T>(x, tail, null);
            tail = tail.next;
        }

        size++;
    }

    // O(1)
    public void add(T x){
        addLast(x);
    }

    public T returnHead(){
        if(isEmpty()) throw new RuntimeException("Empty List!");
        return head.x;
    }

    public T returnTail(){
        if(isEmpty()) throw new RuntimeException("Empty List!");
        return tail.x;
    }

    public T remove(Node<T> node){ // O(1)
        //Check if the input node is either a head or tail node
        if(node.next == null)   //It's a head node
            return removeHead();
        if(node.prev == null) //It's a tail node
            return removeTail();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T temp = node.x;

        node.x = null;
        node = node.prev = node.next = null;

        --size;
        return temp;
    }

    public T removeAt(int x){ // Takes O(n) time, though we need to traverse the list
        // Check if valid index
        if(x < 0 || x >= size) throw new IllegalArgumentException("Index out of bounds");

        Node<T> temp;
        int i;

        // To save searching time we compare the index to check if its in first or second half of the list
        // Similar to Binary Search approach

        if(x < size/2){ //Search first half of the list
            for(i = 0, temp = head; i != x; i++)
                temp = temp.next;
        } else{ // Search the second half of the list
            for(i = size-1, temp = tail; i != x; i--)
                temp = temp.prev;
        }

        return remove(temp);
    }

    // This method also takes O(n) time to run bc of list traversal
    public void remove(Object obj){

        Node<T> temp; // Start searching for obj at the head of list

        for(temp = head; temp != null; temp = temp.next){
            if(temp.x.equals(obj))
                remove(temp);
        }
    }

    public T removeHead(){
        if(isEmpty()) throw new RuntimeException("Empty List!");

        T temp = head.x;
        head = head.next;
        --size;
        //We also need to check if the rest of the list is also possibly empty
        if(isEmpty())
            tail = null;
        else
            head.prev = null;

        return temp;
    }

    public T removeTail(){
        if(isEmpty()) throw new RuntimeException("Empty List!");

        T temp = tail.x;
        tail = tail.prev;
        --size;

        if(isEmpty())
            head = null;
        else
            tail.next = null;

        return temp;
    }

    // Time complexity: O(n) list traversal is necessary in this method
    public int indexOf(Object obj){
        int index = 0;
        Node<T> temp;

        for(temp = head; temp != null; temp = temp.next){
            if(temp.x.equals(obj))
                return index;
            index++;
        }
        // If not found
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private Node<T> temp = head;
            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T x = temp.x;
                temp = temp.next;
                return x;
            }
        };
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> temp = head;
        while(head != null){
            sb.append(temp.x + ", ");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }
}