/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a Restaurant that seats Customers. It functions with a Queue implementation
 */
import java.util.LinkedList;

public class Restaurant extends LinkedList<Customer> {
	private LinkedList<Customer> queue; 
	
	/**
	 * Default constructor. Initializes the variable queue.
	 */
	public Restaurant() {
		queue = new LinkedList<Customer>();
	}
	
	/**
	 * Adds a customer to the end of the queue.
	 * @param c the Customer object that is added to the end of the queue.
	 */
	public void enqueue(Customer c) {
		queue.addLast(c);
	}
	
	/**
	 * Removes the first customer from the queue.
	 * @return the Customer object that was removed.
	 */
	public Customer dequeue() {
		Customer first = queue.remove();
		return first;
	}
	
	/**
	 * Returns the first customer in the queue without removing the object
	 */
	public Customer peek() {
		return queue.peek();
	}
	
	/**
	 * Returns the size of queue. This size is how many customers are in this restaurant object.
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * This method checks to see if this restaurant is empty.
	 */
	public boolean isEmpty() {
		if(queue.isEmpty()) return true;
		return false;
	}
	
	/**
	 * Returns a string representation of the restaurant object, which is basically all the customers in the restaurant. 
	 */
	public String toString() {
		String a = "";
		for(int i = 0; i < queue.size(); i++) {
			a += queue.get(i).toString() + ",";
		}
		return a;
	}
	
	/**
	 * Returns the LinkedList object that contains all the customers.
	 * @return queue
	 */
	public LinkedList<Customer> getQueue(){
		return queue;
	}
}
