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

    // Much more effective hashfunction using modulus
    public void hashFunc2(String[] fromArr, String[] toArr){
        for(int i = 0; i < fromArr.length; i++){
            String newElement = fromArray[i];
            int index = Integer.parseInt(newElement) % 99; // Create an index 2 store value by taking the modulus
            while(toArr[index] != "null") {
                ++index;
                index %= size;
            }
            toArr[index] = newElement;
        }
    }

    public HashTable(int size){
        this.size = size;
        array = new String[size];
        Arrays.fill(array, "null");
    }
}