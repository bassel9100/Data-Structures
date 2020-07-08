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

  public boolean add(T elementent){
    if(contains(elementent)){  // If inserted elementent is already in the tree ignore it
      return false;
    } else {
      add(root, elementent); // Add the elementent to the tree and increase the count
      count++;
      return true;
    }
  }

  public Node add(Node node, T elementent){ // Recursively insert an elementent to the tree
    if (node == null) { // If the node is a leaf node
      node = new Node(null, null, elementent);
    } else {
      if (elementent.compareTo(node.data) < 0) {  // Pick a subtree to insert elementent
        node.left = add(node.left, elementent);
      } else {
        node.right = add(node.right, elementent);
      }
    }
    return node;
  }

  public boolean remove(T elementent) {   // Remove an elementent from the tree if it exists O(n) time complexity
    if (contains(elementent)){
      root = remove(root, element);
      count--;
      return true;
    }
    return false;
  }

  private Node remove(Node node, T element) {
    if(node == null) return null;
    int temp = element.compareTo(node.data);
    if (temp < 0) { // Check left subtree becuase the elementent is smaller than the current elementent
      node.left = remove(node.left, element);
    } else if (temp > 0) { // Check right subtree becuase the elementent is larger than the current elementent
      node.right = remove(node.right, element);
    } else { // Found the elementent

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

      } else {  // Find the smallest elementent in the right subtree
        Node tmp = findMin(node.right); // Find the leftmost node in the right subtree
        node.data = tmp.data; // Swap data
        node.right = remove(node.right, tmp.data);
      }
    }
    return node;
  }

  private Node findMin(Node node) { // Find the leftmost node with smallest value "Helper method"
    while (node.left != null)
      node = node.left;
    return node;
  }

  private Node findMax(Node node) { // Find the rightmost node with largest value "Helper method"
    while (node.right != null)
      node = node.right;
    return node;
  }

  public boolean contains(T element) {
    return contains(root, element);
  }

  private boolean contains(Node node, T element) { // Find elementent in the tree
    if (node == null) return false; // Element not found
    int temp = element.compareTo(node.data);
    if (temp < 0) return contains(node.left, element);
    else if (temp > 0) return contains(node.right, element);
    else return true; // Found the element
  }

  public int height() { // Calculates and returns the height of the tree
    return height(root);
  }

  private int height(Node node) { // Recursive method that calculates the height of the tree
    if (node == null) return 0;
    return Math.max(height(node.left), height(node.right)) + 1;
  }
  
}
