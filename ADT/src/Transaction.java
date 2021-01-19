/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a transaction that will go into a general ledger. 
 */
public class Transaction {
	private String date; 
	private double amount;
	private String description;
	
	/**
	 * Creates a Transaction object with no date, no amount, and no description.
	 */
	public Transaction() {
		date = "";
		amount = 0.0;
		description = "";
	}
	
	/**
	 * Creates a Transaction object with given values;
	 * @param date String object that contains the Transaction date
	 * @param amount Double object that contains the Transaction amount
	 * @param description String object that contains a description of the Transaction
	 */
	public Transaction(String date, double amount, String description) {
		this.date = date; 
		this.amount = amount;
		this.description = description;
	}
	
	/**
	 * Creates a deep clone of a Transaction object
	 */
	public Object clone() {
		return new Transaction(date, amount, description);
	}
	
	/**
	 * Tests if an object is equal to the Transaction
	 * @param obj represents the object that will be compared 
	 * @return Returns true if the two objects are equal and false otherwise.
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Transaction)) {
			return false;
		}
		Transaction t = (Transaction) obj;
		return date.equals(t.getDate()) && amount == t.getAmount() && description.equals(t.getDescription());
	}
	
	/**
	 * @return Returns the date of the Transaction
	 */
	String getDate() {
		return date; 
	}
	
	/**
	 * @return Returns the amount of the Transaction
	 */
	double getAmount() {
		return amount;
	}
	
	/**
	 * @return Returns the description of the Transaction
	 */
	String getDescription() {
		return description;
	}
}