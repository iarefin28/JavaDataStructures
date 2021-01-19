/**
 * Name: Ishan Arefin
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a GeneralLedger that stores a collection of Transaction objects.
 */
public class GeneralLedger {
	private static final int MAX_TRANSACTIONS = 50;
	private double totalDebitAmount; 
	private double totalCreditAmount; 
	private Transaction[] ledger;
	private int size; 
	
	/**
	 * This initializes the GeneralLedger class giving the ledger a size of 50, and initializing all other variables to 0
	 */
	public GeneralLedger() {
		ledger = new Transaction[MAX_TRANSACTIONS];
		totalDebitAmount = 0;
		totalCreditAmount = 0;
		size = 0; 
	}
	
	/**
	 * This method adds a new Transaction into the ledger.
	 * @param newTransaction - the transaction to be added
	 * @throws FullGeneralLedgerException if the General Ledger is full and cannot add anymore transactions.
	 * @throws InvalidTransactionException if the Transaction has an amount of 0 or the date of the Transaction is invalid.
	 * @throws TransactionAlreadyExistsException if a Transaction with the same description, amount, and date exists in the General Ledger
	 */
	public void addTransaction(Transaction newTransaction) throws FullGeneralLedgerException, InvalidTransactionException, TransactionAlreadyExistsException {
		if(size == 50) throw new FullGeneralLedgerException("There is no more room to recrod additional transactions in this ledger.");
		int nYear = Integer.parseInt(newTransaction.getDate().substring(0, 4));
		int nMonth = Integer.parseInt(newTransaction.getDate().substring(5, 7));
		int nDay = Integer.parseInt(newTransaction.getDate().substring(8, 10));
		if(newTransaction.getAmount() == 0 || nYear > 2050 || nYear < 1900 || nMonth > 12 || nMonth < 1 || nDay > 30 || nDay < 1) throw new InvalidTransactionException("The transaction is invalid.");
		for(int i = 0; i < size; i++) {
			if(ledger[i].equals(newTransaction)) throw new TransactionAlreadyExistsException("Transaction already exists.");
		}
		int position = size;
		for(int i = 0; i < size; i++) {
			if(nYear == Integer.parseInt(ledger[i].getDate().substring(0, 4))) {
				if(nMonth == Integer.parseInt(ledger[i].getDate().substring(5, 7))) {
					if(nDay == Integer.parseInt(ledger[i].getDate().substring(8, 10))) {
						
					}
					else if(nDay < Integer.parseInt(ledger[i].getDate().substring(8, 10))) {
						position = i;
						break;
					}
				}
				else if(nMonth < Integer.parseInt(ledger[i].getDate().substring(5, 7))) {
					position = i;
					break;
				}
			}
			else if(nYear < Integer.parseInt(ledger[i].getDate().substring(0, 4))) {
				position = i;
				break;
			}
		}
		for(int i = size; i > position; i--) {
			ledger[i] = ledger[i-1];
		}
		ledger[position] = newTransaction;
		size++;
		if(newTransaction.getAmount() > 0) totalDebitAmount += newTransaction.getAmount();
		else totalCreditAmount += (-1 * newTransaction.getAmount());
	}
	
	/**
	 * This method removes a Transaction from its position.
	 * @param position the position of which Transaction to remove
	 * @throws InvalidLedgerPositionException if the position is larger than the size of ledger or if it is below 1, which is the first in the ledger.
	 */
	public void removeTransaction(int position) throws InvalidLedgerPositionException {
		if(position < 1 || position > size) throw new InvalidLedgerPositionException("The position is not within the valid range.");
		ledger[position-1] = null;
		for(int i = position-1; i < size; i++) {
			ledger[i] = ledger[i+1];
		}
		size--;   
	}
	
	/**
	 * This method obtains a Transactions information at a given position.
	 * @param position the position of which Transaction's information to obtain
	 * @return is of type Transaction
	 * @throws InvalidLedgerPositionException if the position is larger than the size of ledger or if it is below 1, which is the first in the ledger.
	 */
	public Transaction getTransaction(int position) throws InvalidLedgerPositionException {
		if(position < 1 || position > size) throw new InvalidLedgerPositionException("The position is not within the valid range.");
		return ledger[position-1];
	}
	
	/**
	 * This method prints all Transactions in a given date.
	 * @param generalLedger the GeneralLedger object that will be searched
	 * @param date the date of which Transactions to obtain.
	 */
	public static void filter(GeneralLedger generalLedger, String date) {
		String filter = "";
		for(int i = 0; i < generalLedger.size(); i++) {
			if(generalLedger.getLedger()[i].getDate().compareTo(date) == 0) {
				if(generalLedger.getLedger()[i].getAmount() < 0){
					filter = filter + String.format("%-20s%-40s%-20s%-20s", Integer.toString(i+1), generalLedger.getLedger()[i].getDate(), Double.toString(-1 * generalLedger.getLedger()[i].getAmount()), generalLedger.getLedger()[i].getDescription()) + "\n"; 
				}
				else if(generalLedger.getLedger()[i].getAmount() > 0) {
					filter = filter + String.format("%-20s%-20s%-40s%-20s", Integer.toString(i+1), generalLedger.getLedger()[i].getDate(), Double.toString(generalLedger.getLedger()[i].getAmount()), generalLedger.getLedger()[i].getDescription()) + "\n"; 
				}
			}
		}
		System.out.printf("%-20s%-20s%-20s%-20s%-20s", "No.", "Date", "Debit", "Credit", "Description");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println(filter);
	}
	
	/**
	 * This method effectively creates a deep copy of the GeneralLedger object.
	 */
	public Object clone() {
		GeneralLedger a = new GeneralLedger();
		for(int i = 0; i < size; i++) {
			a.ledger[i] = (Transaction) ledger[i].clone();
		}
		a.size = size;
		a.totalCreditAmount = this.totalCreditAmount;
		a.totalDebitAmount = this.totalDebitAmount;
		
		return a;
	}
	
	/**
	 * This method checks the GeneralLedger to see if a certain Transaction exists.
	 * @param transaction the transaction that is being searched for
	 * @return Returns true if the transaction does exists, false otherwise
	 */
	public boolean exists(Transaction transaction) {
		for(int i = 0; i < size; i++) {
			if(ledger[i].equals(transaction)) return true;
		}
		return false;
	}
	
	/**
	 * This method returns the size of the GeneralLedger.
	 * @return Returns size in O(1)
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method prints a neatly formatted table of all the Transactions in the current GeneralLedger object.
	 */
	public void printAllTransactions() {
		if(size == 0) System.out.println("No transactions currently in the general ledger.");
		else {
			System.out.printf("%-20s%-20s%-20s%-20s%-20s", "No.", "Date", "Debit", "Credit", "Description");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println(this.toString());
		}
	}
	
	/**
	 * This method returns a String that contains all Transactions in a nice table.
	 */
	public String toString() {
		String result = "";
		for(int i = 0; i < size; i++) {
			if(ledger[i].getAmount() < 0)
				result = result + String.format("%-20s%-40s%-20s%-20s", Integer.toString(i+1), ledger[i].getDate(), Double.toString(-1 * ledger[i].getAmount()), ledger[i].getDescription()) + "\n"; 
			else if(ledger[i].getAmount() > 0) 
				result = result + String.format("%-20s%-20s%-40s%-20s", Integer.toString(i+1), ledger[i].getDate(), Double.toString(ledger[i].getAmount()), ledger[i].getDescription()) + "\n"; 
		}
		return result;
	}
	
	/**
	 * This method returns entire ledger array.
	 * @return of type Transaction[]
	 */
	public Transaction[] getLedger() {
		return ledger;
	}
	
	/**
	 * This method allows for totalDebitAmount to be accessed and changed.
	 * @param a the number that totalDebitAmount should be changed too.
	 */
	public void setTotalDebitAmount(double a) {
		totalDebitAmount = a;
	}
	
	/**
	 * This method obtains the totalDebitAmount
	 * @return the totalDebitAmount in the form of a double.
	 */
	public double getTotalDebitAmount() {
		return totalDebitAmount;
	}
	
	/**
	 * This method allows for totalCreditAmount to be accessed and changed.
	 * @param a the number that totalCreditAmount should be changed too.
	 */
	public void setTotalCreditAmount(double a) {
		totalCreditAmount = a;
	}
	
	/**
	 * This method obtains the totalCreditAmount
	 * @return the totalCreditAmount in the form of a double.
	 */
	public double getTotalCreditAmount() {
		return totalCreditAmount;
	}
}
