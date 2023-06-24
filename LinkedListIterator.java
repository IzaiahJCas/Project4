import java.util.Iterator;

/**
 * An iterator that iterates through all the values in a linked list.
 */
public class LinkedListIterator<T> implements Iterator<T> {
  /** Keeps track of the next node in the iteration */
  private LLNode<T> nodeptr;
  
  /**
   * Creates an iterator for a linked list.
   * @param firstNode the node where the iteration should start
   */
  public LinkedListIterator(LLNode<T> firstNode) {
    nodeptr = firstNode;
  }
  
  /**
   * Returns whether there are still values in the linked list to iterate over.
   * @returns true if there are more values to iterate over and false if the iteration is done.
   */
  public boolean hasNext() {
    return nodeptr != null;
  }
  
  /**
   * Returns the next element of the linked list. 
   * Should throw a NoSuchElementException if there are no more elements, but currently throws a NullPointerException
   * @returns the next element in the iteration of the list
   */
  public T next() {
    T save = nodeptr.getElement();
    nodeptr = nodeptr.getNext();
    return save;
  }
}