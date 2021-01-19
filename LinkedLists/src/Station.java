/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a Station that holds a list of Track objects.
 */
import java.util.Scanner;

public class Station {
	private Track head;
	private Track tail;
	private Track cursor;
	private int size;
	
	/**
	 * Constructor for the Station object. Initializes all the variables
	 * 
	 */
	public Station() {
		head = null;
		tail = null;
		cursor = null;
	}
	
	/**
	 * Adds a new Track into the list.
	 * The Track is added into the list so that no two Tracks have the same Track number and that the Tracks are ordered from smallest to greatest in integers.
	 * @param newTrack the new Track object to be added into the linked list
	 * @throws TrackAlreadyExistsException thrown if Track with the same Track number exists
	 */
	public void addTrack(Track newTrack) throws TrackAlreadyExistsException { 
		if(head == null) {
			head = newTrack;
			cursor = head;
			size++;
		}
		else if(newTrack.getTrackNumber() < head.getTrackNumber()) {
			newTrack.setPrev(null);
			newTrack.setNext(head);
			head.setPrev(newTrack);
			head = newTrack;
			cursor = newTrack;
			size++;
		}
		else {
			cursor = head;
			if(newTrack.getTrackNumber() == cursor.getTrackNumber()) throw new TrackAlreadyExistsException("Track already exists.");
			while(cursor.getNext() != null && cursor.getNext().getTrackNumber() < newTrack.getTrackNumber()) {
				if(newTrack.getTrackNumber() == cursor.getTrackNumber()) throw new TrackAlreadyExistsException("Track already exists.");
				cursor = cursor.getNext();
			}
			newTrack.setNext(cursor.getNext());
			if(cursor.getNext() != null) newTrack.getNext().setPrev(newTrack);
			cursor.setNext(newTrack);
			newTrack.setPrev(cursor);
			cursor = newTrack;
			size++;
		}	
	}
	
	/**
	 * Removes the currently selected Track from the list
	 * @return of type Track if there is a selected Track, otherwise returns null
	 */
	public Track removeSelectedTrack() {
		if(cursor == null) return null;
		Track a = cursor;
		if(cursor.getNext() == null && cursor.getPrev() == null) {
			head = null;
			size--;
		}
		else if(cursor.getNext() == null) {
			cursor.getPrev().setNext(cursor.getNext());
			cursor = cursor.getPrev();
			size--;
		}
		else if(cursor.getPrev() == null) {
			head = cursor.getNext();
			cursor = cursor.getNext();
			size--;
		}
		else {
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getNext();
			size--;
		}
		return a;	
	}
	
	/**
	 * Prints out the currently selected Track with all of its Train objects in a neatly formatted table.
	 */
	public void printSelectedTrack() {
		System.out.println("Track " + cursor.getTrackNumber() + "(" + cursor.getUtilizationRate()/1440*100 + "% Utilization Rate):");
		System.out.printf("%-20s%-20s%-20s%-20s%-20s", "Selected", "Train Number", "Train Destination", "Arrival Time", "Departure Time");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		cursor.printTrack();
	}
	
	/**
	 * Prints out all the Tracks and there Train objects in a neatly formatted table
	 */
	public void printAllTracks() {
		Track b = cursor;
		cursor = head;
		while(cursor != null) {
			if(cursor == b) {
				System.out.print("*");
				printSelectedTrack();
				cursor = cursor.getNext();
			}
			else {
				printSelectedTrack();
				cursor = cursor.getNext();
			}
		}
		cursor = b;
	}
	
	/**
	 * If a specific Track exists, cursor is changed to select that Track
	 * @param tracktoSelect the Track number to select
	 * @return true if Track was able to be changed, false otherwise
	 */
	public boolean selectTrack(int tracktoSelect) {
		Track a = cursor;
		cursor = head;
		while(cursor.getNext() != null) {
			if(cursor.getTrackNumber() == tracktoSelect) {
				return true;
			}
			cursor = cursor.getNext();
		}
		cursor = a;
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, TrainExistsException {
		boolean quit = false; 
		Station station = new Station();
		
		while(!quit) {
			Scanner s = new Scanner(System.in);
			
			System.out.println("Train Options\n\tA. Add new Train\n"
					+ "\tN. Select nextTrain\n\tV. Select previous Train\n\tR. Remove selected train\n"
					+ "\tP. Print selected Train\nTrack Options\n\tTA. Add Track\n\tTR. Remove selected Track\n\tTS. Switch Track\n"
					+ "\tTPS. Print selected Track\n\tTPA. Print all Tracks\nStation Options\n\tSI. Print Station Information\n\tQ. Quit\n");
			
			System.out.println("Choose an operation: ");
			String input = s.nextLine();
			input = input.toUpperCase();
			
			if(input.equals("TA")) {
				System.out.println("Enter track number: ");
				String tn = s.nextLine();
				Track a = new Track(); 
				a.setTrackNumber(Integer.parseInt(tn));
				try {
					station.addTrack(a);
					System.out.println("Track " + tn + " added to the Station.");
				} catch (TrackAlreadyExistsException e) {
					System.out.println("Track not added: Track " + tn + " already exists.");
				}
			}
			
			if(input.equals("A")) {
				System.out.println("Enter train number: ");
				String tn = s.nextLine();
				System.out.println("Enter train destination: ");
				String td = s.nextLine();
				System.out.println("Enter train arrival time: ");
				String tat = s.nextLine();
				System.out.println("Enter train transfer time: ");
				String ttt = s.nextLine();
				if(Integer.parseInt(tat.substring(0, 2)) < 0 || Integer.parseInt(tat.substring(0, 2)) > 24 || Integer.parseInt(tat.substring(2, 4)) > 60 || Integer.parseInt(tat.substring(0, 4)) < 0) {
					System.out.println("Train not added: Invalid arrival time.");
				}
				else if(station.cursor == null) System.out.println("Train not added: There is no Track to add the Train to!");
				else {
					try {
						station.cursor.addTrain(new Train(null, null, Integer.parseInt(tn), td, Integer.parseInt(tat), Integer.parseInt(ttt)));
						System.out.println("Train No. " + tn + " to " + td + " added to Track " + station.cursor.getTrackNumber());
						station.cursor.setUtilizationRate(Double.parseDouble(ttt) + station.cursor.getUtilizationRate());
					}
					catch(TrainNumberExistsException e) {
						System.out.println("A train with the same train number already exists in this track.");
					}
					catch(TrainExistsException e) {
						System.out.println("Train not added: There is a Train already scheduled on Track" + station.cursor.getTrackNumber() + " at that time!");
						
					}
				}
			}
			
			if(input.equals("N")) {
				try {
					//station.cursor.setCursor(station.cursor.getCursor().getNext());
					if(station.cursor.selectNextTrain()) 
						System.out.println("Cursor has been moved to next train.");
					else {
						System.out.println("Selected train not updated: Already at end of Track list.");
					}
				}
				catch(NoSelectedTrainException e) {
					System.out.println("There is no train selected.");
				}
			}
			
			if(input.equals("V")) {
				try {
					//station.cursor.setCursor(station.cursor.getCursor().getPrev());
					if(station.cursor.selectPrevTrain()) {
						System.out.println("Cursor has been moved to previous train.");
					}
					else {
						System.out.println("Selected train not updated: Already at beginning of Track list.");
					}
				}
				catch(NoSelectedTrainException e) {
					System.out.println("There is no train selected.");
				}
			}
			
			if(input.equals("R")) {
				Train a = station.cursor.removeSelectedTrain();
				station.cursor.setUtilizationRate(station.cursor.getUtilizationRate() - a.getTransferTime());
				System.out.println("Train No. " + a.getTrainNumber() + " to " + a.getDestination() + " has been removed from Track " + station.cursor.getTrackNumber());
			}
			
			
			if(input.equals("TPS")) {
				if(station.cursor == null) System.out.println("There are no tracks selected.");
				else {
					station.printSelectedTrack();
				}
			}
			
			if(input.equals("TPA")) {
				station.printAllTracks();
			}
			
			if(input.equals("TS")) {
				int g = station.cursor.getTrackNumber();
				System.out.println("Enter the Track number: ");
				String tn = s.nextLine();
				station.cursor = station.head;
				while(station.cursor != null) {
					if(station.cursor.getTrackNumber() == Integer.parseInt(tn)) {
						System.out.println("Switched to Track " + tn);
						break;
					}
					else station.cursor = station.cursor.getNext();
				}
				if(station.cursor == null) {
					System.out.println("Could not switch to Track " + tn + ": Track " + tn + " does not exist.");
					station.cursor = station.head;
					while(station.cursor != null) {
						if(station.cursor.getTrackNumber() == g) {
							break;
						}
						else {
							station.cursor = station.cursor.getNext();
						}
					}
				}
			}
			
			if(input.equals("TR")) {
				Track b = station.removeSelectedTrack();
				if(b != null) System.out.println("Closed Track " + b.getTrackNumber());
				else System.out.println("There is no track to remove.");
			}
			
			if(input.equals("P")) {
				System.out.println(station.cursor.getCursor().toString());
			}
			
			if(input.equals("SI")) {
				System.out.println("Station (" + station.size + " tracks)");
				Track b = station.cursor;
				station.cursor = station.head;
				while(station.cursor != null) {
					System.out.println("Track " + station.cursor.getTrackNumber() + ": " + station.cursor.getSize() + " trains arriving (" + station.cursor.getUtilizationRate()/1440*100 + "% Utilization Rate)");
					station.cursor = station.cursor.getNext();
				}
				station.cursor = b;
			}
			
			if(input.equals("Q")) {
				quit = true;
				System.out.println("Program terminating normally...");
			}
		}
	}
	
}
