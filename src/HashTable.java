import java.util.Arrays;

public class HashTable {

    String[] array;
    int size;
    int itemsInArray = 0;

    public static void main(String[] args){

    }

    // Very bad but simple hashfunction
    public void hashFunc1(String[] fromArr, String[] toArr){
        for(int i = 0; i < fromArr.length; i++){
            String newElement = fromArray[i];
            toArray[Integer.parseInt(newElement)] = newElement;
        }
    }

    public HashTable(int size){
        this.size = size;
        array = new String[size];
        Arrays.fill(array, "null");
    }

    
}