/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a Track that will be used in the Station object. 
 */
public class Track {
	private Train head;
	private Train tail;
	private Train cursor;
	private Track next;
	private Track prev;
	private double utilizationRate;
	private int trackNumber;
	private int size;
	
	/**
	 * Constructor that initializes all member variables.
	 */
	public Track() {
		head = null;
		tail = null;
		cursor = null;
		next = null;
		prev = null;
		utilizationRate = 0;
		trackNumber = 0;
	}
	
	/**
	 * This method adds a new Train into the Track. The train is added so that the linked list maintains a sorted order through times.
	 * @param newTrain the new Train object to be added into the Track
	 * @throws TrainExistsException when there is a train scheduled in the time that new Train is trying to be scheduled in
	 * @throws TrainNumberExistsException when there is a train that is attempted to be added with the same number as a Train already in the Track
	 */
	public void addTrain(Train newTrain) throws TrainExistsException, TrainNumberExistsException {
		Train a = cursor;
		cursor = head;
		while(cursor != null) {
			if(cursor.getTrainNumber() == newTrain.getTrainNumber()) throw new TrainNumberExistsException("A train with this train number already exists in this track.");
			cursor = cursor.getNext();
		}
		if(head == null) {
			head = newTrain;
			cursor = newTrain;
			size++;
		}
		else if(newTrain.getArrivalMin() < head.getArrivalMin() && newTrain.getDepMin() < head.getArrivalMin()) {
			newTrain.setPrev(null);
			newTrain.setNext(head);
			head.setPrev(newTrain);
			head = newTrain;
			cursor = newTrain;
			size++;
		}
		else {
			cursor = head;
			while(cursor.getNext() != null && (cursor.getNext().getDepMin() < newTrain.getArrivalMin() || cursor.getNext().getArrivalMin() < newTrain.getArrivalMin())) {
				if((newTrain.getArrivalMin() >= cursor.getArrivalMin() && newTrain.getArrivalMin() <= cursor.getDepMin()) || (newTrain.getDepMin() >= cursor.getArrivalMin() && newTrain.getDepMin() <= cursor.getDepMin())) {
					cursor = a;
					throw new TrainExistsException("There is a train scheduled for this time.");
				}
				cursor = cursor.getNext();
			}
			if((newTrain.getArrivalMin() >= cursor.getArrivalMin() && newTrain.getArrivalMin() <= cursor.getDepMin()) || (newTrain.getDepMin() >= cursor.getArrivalMin() && newTrain.getDepMin() <= cursor.getDepMin())) {
				cursor = a;
				throw new TrainExistsException("There is a train scheduled for this time.");
			}
			newTrain.setNext(cursor.getNext());
			if(cursor.getNext() != null) newTrain.getNext().setPrev(newTrain);
			cursor.setNext(newTrain);
			newTrain.setPrev(cursor);
			cursor = newTrain;
			size++;
		}
	}
	
	/**
	 * Removes the selected train (if selected) from the linked list.
	 * @return of type Train and this is the Train object that is removed from the list
	 */
	public Train removeSelectedTrain() {
		if(cursor == null) return null;
		if(cursor.getNext() == null && cursor.getPrev() == null) {
			Train a = cursor;
			cursor = null;
			size--;
			return a;
		}
		Train a = cursor;
		if(cursor.getPrev() != null) cursor.getPrev().setNext(cursor.getNext());
		if(cursor.getNext() != null) cursor.getNext().setPrev(cursor.getPrev());
		if(cursor.getNext() == null)
			cursor = cursor.getPrev();
		else cursor = cursor.getNext();  
		size--;
		return a;
	} 
	
	/**
	 * This method selects the next Train in the current Track
	 * @return if the cursor is moved, true is returned, otherwise false
	 * @throws NoSelectedTrainException if there is no train selected
	 */
	public boolean selectNextTrain() throws NoSelectedTrainException {
		if(cursor == null) throw new NoSelectedTrainException("There is no train selected");
		if(cursor.getNext() == null) return false;
		cursor = cursor.getNext();
		return true;
	}
	
	/**
	 * This method selects the previous Train in the current Track
	 * @return if the cursor is moved, true is returned, otherwise false
	 * @throws NoSelectedTrainException if there is no train selected
	 */
	public boolean selectPrevTrain() throws NoSelectedTrainException {
		if(cursor == null) throw new NoSelectedTrainException("There is no train selected");
		if(cursor.getPrev() == null) return false;
		cursor = cursor.getPrev();
		return true;
	}
	
	/**
	 * This method works with the method printSelectedTrack in Station object in order to print all the Trains in the selected track
	 */
	public void printTrack() {
		Train a = cursor;
		cursor = head;
		while(cursor != null) {
			String b = String.format("%04d", cursor.getArrivalTime());
			int x = cursor.getDepMin()/60;
			int y = cursor.getDepMin()%60;
			String g = "";
			if(y < 10) g = "0" + Integer.toString(y);
			else g = Integer.toString(cursor.getDepMin());
			String c = String.format("%04d", Integer.parseInt(g));
			if(cursor.equals(a)) {
				System.out.printf("%-20s%-20s%-20s%-20s%-20s", "*", cursor.getTrainNumber(), cursor.getDestination(), b, c);
				System.out.println();
			}
			else {
				System.out.printf("%-20s%-20s%-20s%-20s%-20s", "", cursor.getTrainNumber(), cursor.getDestination(), b, c);
				System.out.println();
			}
			cursor = cursor.getNext();
		}
		cursor = a;
	}
			
	public Train getHead() {
		return head;
	}
	
	public void setHead(Train a) {
		head = a;
	}
	
	public Train getTail() {
		return tail;
	}
	
	public void setTail(Train a) {
		tail = a;
	}
	
	public Train getCursor() {
		return cursor;
	}
	
	public void setCursor(Train a) {
		cursor = a;
	}
	
	public Track getNext() {
		return next;
	}
	
	public void setNext(Track nt) {
		next = nt;
	}
	
	public Track getPrev() {
		return prev;
	}
	
	public void setPrev(Track nt) {
		prev = nt;
	}
	
	public double getUtilizationRate() {
		return utilizationRate;
	}
	
	public void setUtilizationRate(double d) {
		utilizationRate = d;
	}
	
	public int getTrackNumber() {
		return trackNumber;
	}
	
	public void setTrackNumber(int a) {
		trackNumber = a;
	}
	
	public int getSize() {
		return size;
	}
}


