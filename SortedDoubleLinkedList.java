package application;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> {
	
	// Variables
	private Comparator<T> comparator;

	/**
	 *  Constructor
	 * @param comparator comparator used
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Add method to insert to the BasicDoubleLinkedList in sorted order dependent on the comparator
	 * @param data the data to be inserted
	 * @return the current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		Node newNode = new Node(data);
		Node headingNode = head; // Set the head to the current heading node
		
		/*
		 *  If the list is empty, add the data node to the head of the linked list; head and tail 
		 *  points to the same element if there is only one element in the list.
		 *  Else if the list already contains data, and if compared to the new data node is greater
		 *  than 0, add the new data node that will become the head of the linked list.
		 *  Else if the list already contains data, and if compared to the new data node is less than
		 *  0, add the new data node that will be pushed to the tail of the targeted data.
		 */
		if (listSize == 0) {
			head = newNode;
			tail = head;
			listSize++; // Increment listSize
			return this; // Return current object
		}
		else if (comparator.compare(head.data, data) > 0) {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
			listSize++; // Increment listSize
			return this; // Return current object
		}
		else {
			while (comparator.compare(headingNode.data, data) < 0) {
				if (headingNode.next == null) {
					headingNode.next = newNode;
					newNode.previous = headingNode;
					tail = newNode;
					listSize++; // Increment listSize
					return this; // Return current object
				}
				else {
					headingNode = headingNode.next;
				}
			}
			headingNode.previous.next = newNode;
			newNode.previous = headingNode.previous;
			headingNode.previous = newNode;
			newNode.next = headingNode;
			listSize++; // Increment listSize
			return this; // Return current object
		}
	}

	/**
	 * Call super remove method to remove the target data
	 */
	public SortedDoubleLinkedList<T> remove(T targetedData, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(targetedData, comparator);
	}

	@Override
	public ListIterator<T> iterator() throws NoSuchElementException, UnsupportedOperationException {
		return super.iterator();
	}
	
	/**
	 * Not supported operation in SortedDoubleLinkedList
	 * @param theElement element to be added at the front of the linked list
	 * @throws UnsupportedOperationException Not supported operation
	 */
	public SortedDoubleLinkedList<T> addToFront(T theElement) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**
	 * Not supported operation in SortedDoubleLinkedList
	 * @param theElement element to be added at the end of the linked list
	 * @throws UnsupportedOperationException Not supported operation
	 */
	public SortedDoubleLinkedList<T> addToEnd(T theElement) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

}
