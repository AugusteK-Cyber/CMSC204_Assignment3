package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

	// Variables
	protected Node head; // First element of the linked list
	protected Node tail; // Last element of the linked list
	protected int listSize; // Size of the linked list

	/**
	 * Default Constructor
	 */
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.listSize = 0; // Initialize linked list size to 0
	}

	/**
	 * Adds element to the front of the list.
	 * The new Node is always added before the head of the given linked list, and it becomes the 
	 * new head of the linked list. The pointer points to this head, before it is changed to point
	 * to the new node. Do not use iterators to implement this method.
	 * @param data the element to be added to the linked list
	 * @return the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data); // Allocate the Node and put in the data

		/*
		 *  If the list is empty, add the new data node to the head of the linked list; 
		 *  head and tail points to the same element if there is only one element in the list.
		 *  Else if the list already contains data, add the new data node to the head of
		 *  the linked list.
		 */
		if (listSize == 0) {
			head = newNode;
			tail = head;
		}
		else {
			head.previous = newNode; // Change previous of head node to new node (head != null)
			newNode.next = head; // Make next of new node as head of the linked list
			head = newNode; // Move the head to point to the new node of the linked list
		}

		listSize++; // Increment listSize each time data is added to the linked list
		
		return this; // Return current object
	}

	/**
	 * Adds an element to the end of the list.
	 * @param data the data add to the end of the linked list
	 * @return the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		// Allocate the Node and put in the data
		Node newNode = new Node(data);

		/*
		 *  If the list is empty, add the new data node to the head of the linked list; 
		 *  head and tail points to the same element if there is only one element in the list.
		 *  Else if the list already contains data, add the new data node to the tail of
		 *  the linked list. We need to traverse the list till end and then change the next of
		 *  last node to new node.
		 */
		if (listSize == 0) {
			head = newNode;
			tail = head;
		}
		else {
			tail.next = newNode; // Change next of tail node to new node (tail != null)
			newNode.previous = tail; // Make previous of the new node at the end of the linked list as tail 
			tail = newNode; // Move the tail to point to the new node at the end of the linked list
		}

		listSize++; // Increment listSize each time data is added to the linked list
		return this; // Return current object
	}

	/**
	 * Returns but does not remove the first element from the list.
	 * @return first data corresponding to the head of the linked list
	 */
	public T getFirst() {
		if (listSize == 0) {
			return null; // If the linked list is empty
		}
		return head.data; 
	}

	/**
	 * Returns but does not remove the last element from the list.
	 * @return last data corresponding to the tail of the linked list
	 */
	public T getLast() {
		if (listSize == 0) {
			return null; // If the linked list is empty
		}
		return tail.data;
	}

	/**
	 * Return the size of the linked list. Must not traverse the list to compute the size
	 * @return listSize the size of the linked list
	 */
	public int getSize() {
		return listSize;
	}

	/**
	 * Removes the first instance of the targetData from the list.
	 * @param targetedData the data to remove from the linked list
	 * @param comparator comparator variable
	 * @return the current object
	 */
	public BasicDoubleLinkedList <T> remove(T targetedData, Comparator<T> comparator) {
		Node headingNode = head; // Set the head to the current heading node
		while (headingNode != null) {
			if (comparator.compare(targetedData, headingNode.data) == 0) {
				if (headingNode == head) {
					head = head.next;
				}
				else if (headingNode == tail) {
					tail = tail.previous;
				}
				else {
					headingNode.previous.next = headingNode.next;
				}
				listSize--; // Decrement size of linked list when data is removed
				return this;
			}
			headingNode = headingNode.next; // Move pointer to the next node
		}
		return this; // Return current object
	}

	/**
	 * Removes and returns the first element from the list.
	 * @return firstData current head of the linked list 
	 */
	public T retrieveFirstElement() {

		if (listSize == 0) {
			return null; // If the linked list is empty
		}

		/*
		 * Retrieve the first data of the linked list. If only one data exists in the linked 
		 * list, set head and tail to null after retrieving that data. 
		 */
		T firstData = head.data; // first data of the linked list
		if (listSize == 1) {
			head = null;
			tail = null;
		}
		else {
			head = head.next; // The head of the linked list moves to the next node
			head.previous = null; // The node where the data were retrieved is set to null
		}
		listSize--; // Decrement size of the linked list each time a data is retrieved
		return firstData;
	}

	/**
	 * Removes and returns the last element from the list.
	 * @return lastData current tail of the linked list
	 */
	public T retrieveLastElement() {

		if (listSize == 0) {
			return null;
		}

		/*
		 * Retrieve the last data of the linked list. If only one data exists in the linked 
		 * list, set head and tail to null after retrieving that data. 
		 */
		T lastData = tail.data;
		if (listSize == 1) {
			head = null;
			tail = null;
		}
		else {
			tail = tail.previous; // The tail of the linked list moves to the previous node
			tail.next = null; // The node where the data were retrieved is set to null
		}
		listSize--; // Decrement size of the linked list each time a data is retrieved
		return lastData;
	}

	/**
	 * Returns an arraylist of the items in the list from head of list to tail of list
	 * @return dataList an arraylist of nodes
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> dataList = new ArrayList<>();
		Node headingNode = head; // Set the current heading node to the head of the linked list

		while(headingNode != null) {
			dataList.add(headingNode.data); // Add data in the head Node to the arraylist
			headingNode = headingNode.next; // Move pointer to the next node to fetch next data
		}
		return dataList;
	}

	/**
	 * This method must be implemented using an inner class that implementsListIterator
	 * and defines the methods of hasNext(), next(), hasPrevious() and previous().
	 * @return a Node object
	 */
	public ListIterator<T> iterator() throws NoSuchElementException, UnsupportedOperationException {
		return new Node();
	}

	/**
	 * Inner class Node that implements ListIterator for the iterator method, head and tail references,
	 * and an integer that represents the linked list size. Though, only the following methods are 
	 * implemented: next(), hasNext(), previous(), hasPrevious().
	 * @author Auguste Kiendrebeogo
	 *
	 */
	public class Node implements ListIterator<T> {

		// Variables
		protected Node pointer;
		protected Node currentNode; // Keep track of the current node
		protected Node previous;
		protected Node next;
		protected T data;

		// No-arg Constructor
		public Node() {
			this.pointer = head; // The pointer points to the head (first node) of the linked list
			this.currentNode = null;
			this.previous = null;
			this.next = null;
		}

		// Constructor
		public Node(T data) {
			this.pointer = null;
			this.currentNode = null;
			this.previous = null;
			this.next = null;
			this.data = data;
		}

		/**
		 * If an element exists in the next node, the pointer will point to to the value of this node.
		 * Return true if the pointer points to an element in the next node, false otherwise.
		 */
		@Override
		public boolean hasNext() {
			return pointer != null;
		}

		/**
		 * If an element was added to the last node, the current node value is no longer null.
		 * Return true if an element exists in the previous node, false otherwise.
		 */
		@Override
		public boolean hasPrevious() {
			return currentNode != null;
		}

		/**
		 * Return the data contained in the next node if it exists.
		 * Throw an exception if the element does not exist in the linked list.
		 */
		@Override
		public T next() throws NoSuchElementException {
			while (hasNext()) {
				currentNode = pointer;
				pointer = currentNode.next;
				return currentNode.data;
			}
			throw new NoSuchElementException("There is no next element in the linked list");
		}

		/**
		 * Return the data contained in the previous node if it exists.
		 * Throw an exception if the element does not exist in the linked list.
		 */
		@Override
		public T previous() throws UnsupportedOperationException {
			while (hasPrevious()) {
				pointer = currentNode;
				currentNode = pointer.previous;
				return pointer.data;
			}
			throw new NoSuchElementException("There is no previous element in the linked list");
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();			
		}

		@Override
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
}
