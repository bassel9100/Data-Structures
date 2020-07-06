import java.util.*;
/**
 * Binary Search Tree implementation
 * @author basselkanaan, bassel.9100@gmail.com
 **/

public class BST <T extends Comparable<T>>{

  private int count = 0; // Tracks total number of nodes in the BST
  private Node root = null; // The main root of the BST

  private class Node{
    T x;
    Node left, right;
    public Node(Node left, Node right, T x){
      this.left = left;
      this.right = right;
      this.x = x;
    }
  }

  public boolean isEmpty(){ // Checks if tree is empty
    return size() == 0;
  }

  public int size(){ // Get the total amount nodes in the tree
    return count;
  }

  public boolean add(T element){
    if(contains(element)){  // If inserted element is already in the tree ignore it
      return false;
    } else {
      add(root, element); // Add the element to the tree and increase the count
      count++;
      return true;
    }
  }

  public Node add(Node node, T element){ // Recursively insert an element to the tree
    if (node == null) { // If the node is a leaf node
      node = new Node(null, null, element);
    } else {
      if (element.compareTo(node.data) < 0) {  // Pick a subtree to insert element
        node.left = add(node.left, element);
      } else {
        node.right = add(node.right, element);
      }
    }
    return node;
  }

  public boolean remove(T element) {   // Remove an element from the tree if it exists O(n) time complexity
    if (contains(element)){
      root = remove(root, elem);
      count--;
      return true;
    }
    return false;
  }

  private Node remove(Node node, T elem) {
    if(node == null) return null;
    int temp = elem.compareTo(node.data);
    if (temp < 0) { // Check left subtree becuase the element is smaller than the current element
      node.left = remove(node.left, elem);
    } else if (temp > 0) { // Check right subtree becuase the element is larger than the current element
      node.right = remove(node.right, elem);
    } else { // Found the element

      if (node.left == null) {          // Case with only a right subtree or no subtree
        Node rightChild = node.right;   // In that case, swap the node that is going to be removed with its right child
        node.data = null;
        node = null;
        return rightChild;
      } else if (node.right == null) {
                                        // Case with only a left subtree or no subtree
        Node leftChild = node.left;    // In that case, swap the node that is going to be removed with its left child
        node.data = null;
        node = null;
        return leftChild;

      } else {  // Find the smallest element in the right subtree
        Node tmp = findMin(node.right); // Find the leftmost node in the right subtree
        node.data = tmp.data; // Swap data
        node.right = remove(node.right, tmp.data);
    }
  }
  return node;
}



}
