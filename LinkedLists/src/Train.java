/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a train that will go into the Track class.
 */
public class Train {
	private Train next;
	private Train prev;
	private int trainNumber;
	private String destination;
	private int arrivalTime;
	private int transferTime;
	private int arrivalMin;
	private int depMin;
	
	/**
	 * Constructor for train object. Initializes all member variables.
	 */
	public Train() {
		next = null;
		prev = null;
		trainNumber = 0;
		destination = "";
		arrivalTime = 0;
		transferTime = 0;
		arrivalMin = 0;
		depMin = 0;
	}
	
	/**
	 * Constructor with parameters for the train object.
	 * @param next is what member variable next will be set to
	 * @param prev is what member variable prev will be set to
	 * @param trainNumber is what member variable trainNumber will be set to
	 * @param dest is what the member variable destiniation will be set to
	 * @param arrivalTime is what member variable arrivalTime will be set to
	 * @param transferTime is what member variable transferTime will be set to
	 */
	public Train(Train next, Train prev, int trainNumber, String dest, int arrivalTime, int transferTime) {
		this.next = next;
		this.prev = prev;
		this.trainNumber = trainNumber;
		destination = dest;
		this.arrivalTime = arrivalTime;
		this.transferTime = transferTime;
		arrivalMin = arrivalTime / 100 * 60 + arrivalTime%100;
		depMin = arrivalMin + transferTime;
	}
	
	/**
	 * Tests if an object is equal to a train object.
	 * @param o is the object that will be compared to train object for equality
	 * @return is a boolean. If two trains are equal return will be true, false otherwise
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Train)) {
			return false;
		}
		Train a = (Train) o;
		if(a.trainNumber == trainNumber) return true;
		return false;
	}
	
	/**
	 * Returns a string representation of the Train object
	 */
	public String toString() {
		String s = "";
		s += "Train Number: " + Integer.toString(trainNumber) + "\n";
		s += "Train Destination: " + destination + "\n";
		String at = String.format("%04d", arrivalTime);
		s += "Arrival Time: " + at + "\n";
		int a = depMin/60;
		int b = depMin%60;
		String c = "";
		if(b < 10) {
			c = "0"  + Integer.toString(b);
		}
		else {
			c = Integer.toString(b);
		}
		String depTime = Integer.toString(a) + c;
		depTime = String.format("%04d", Integer.parseInt(depTime));
		s += "Departure Time: " + depTime + "\n";
		return s;
	}
	
	/**
	 * @return Returns the next Train in the linked list.
	 */
	public Train getNext() {
		return next;
	}
	
	/**
	 * Sets the next variable to a new node
	 * @param next the new node to be set to
	 */
	public void setNext(Train next) {
		this.next = next;
	}
	
	/**
	 * @return Retruns the previous Train in the linked list
	 */
	public Train getPrev() {
		return prev;
	}
	
	/**
	 * Sets the prev variable to a new node
	 * @param prev the new node to be set to
	 */
	public void setPrev(Train prev) {
		this.prev = prev;
	}
	
	/**
	 * @return Return type int, returns the train number of the current Train
	 */
	public int getTrainNumber() {
		return trainNumber;
	}
	
	/**
	 * Sets a new number for Train Number
	 * @param trainNumber the new train number 
	 */
	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}
	
	/**
	 * @return Returns the destination of the train
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * Sets new destination for the train
	 * @param destination the new destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	/**
	 * @return the arrival time of the train.
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Sets new arrival time for the train.
	 * @param arrivalTime the new arrival time to be set to
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * @return the transfer time of the train
	 */
	public int getTransferTime() {
		return transferTime;
	}
	
	/**
	 * Sets new transfer time for the train
	 * @param transferTime the new transfer time to be set to
	 */
	public void setTransferTime(int transferTime) {
		this.transferTime = transferTime;
	}
	
	/**
	 * @return the exact arrival minute of the train from 0 - 1440
	 */
	public int getArrivalMin() {
		return arrivalMin;
	}
	
	/**
	 * @return the exact departure minute of the train from 0 - 1440
	 * This should return the arrival time + the transfer time.
	 */
	public int getDepMin() {
		return depMin;
	}
}
