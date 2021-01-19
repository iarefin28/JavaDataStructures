/**
 * Name: Ishan Arefin
 * Student ID: 112937865
 * Recitation: R10
 */
import java.util.Scanner;

public class GeneralLedgerManager {
	public static void main(String[] args) throws NumberFormatException, InvalidLedgerPositionException, FullGeneralLedgerException, InvalidTransactionException {
		boolean quit = false; 
		GeneralLedger gl = new GeneralLedger();
		GeneralLedger backup = new GeneralLedger();
		
		while(!quit) {
			Scanner s = new Scanner(System.in);
			
			System.out.println("(A)\tAdd Transaction\n(G)\tGet Transaction\n"
					+ "(R)\tRemove Transaction\n(P)\tPrint Transactions in General Ledger\n(F)\tFilter by Date\n"
					+ "(L)\tLook for Transaction\n(S)\tSize\n(B)\tBackup\n(PB)\tPrint Transactions in Backup\n(RB)\tRevert to Backup\n"
					+ "(CB)\tCompare Backup with Current\n(PF)\tPrint Financial Information\n(Q)\tQuit\n");
			
			System.out.println("Enter a selection: ");
			String input = s.nextLine();
			input = input.toUpperCase();
			
			if(input.equals("A")) {
				try {
					System.out.print("Enter Date: ");
					String d = s.nextLine();
					System.out.print("Enter Amount ($): ");
					String a = s.nextLine();
					System.out.print("Enter Description: ");
					String des = s.nextLine();
				
					gl.addTransaction(new Transaction(d, Double.parseDouble(a), des));
					System.out.println("Transaction successfully added to the general ledger.");
				}
				catch(TransactionAlreadyExistsException a) {
					System.out.println("Transaction not added: Transaction already exists in the ledger.");
				}
				
			}
			
			if(input.equals("G")) {
				System.out.print("Enter position: ");
				String pos = s.nextLine();
				try{
					Transaction a = gl.getTransaction(Integer.parseInt(pos));
					System.out.printf("%-20s%-20s%-20s%-20s%-20s", "No.", "Date", "Debit", "Credit", "Description");
					System.out.println();
					System.out.println("----------------------------------------------------------------------------------------------");
					if(a.getAmount() < 0)
						System.out.printf("%-20s%-40s%-20s%-20s", pos, a.getDate(), Double.toString(-1 * a.getAmount()), a.getDescription()); 
					else if(a.getAmount() > 0) {}
						System.out.printf("%-20s%-20s%-40s%-20s", pos, a.getDate(), Double.toString(a.getAmount()), a.getDescription());
				}
				catch(InvalidLedgerPositionException a) {
					System.out.println("There is no such transaction");
				}
				
			}
			
			if(input.equals("R")) {
				System.out.print("Enter position: ");
				String pos = s.nextLine();
				gl.removeTransaction(Integer.parseInt(pos));
				System.out.println("Transaction has successfully removed from the general ledger.");
			}
		
			if(input.equals("P")) {
				gl.printAllTransactions();
			}
			
			if(input.equals("F")) {
				System.out.print("Enter Date: ");
				String date = s.nextLine();
				GeneralLedger.filter(gl, date);
			}
			
			if(input.equals("L")) {
				System.out.print("Enter Date: ");
				String d = s.nextLine();
				System.out.print("Enter Amount ($): ");
				String a = s.nextLine();
				System.out.print("Enter Description: ");
				String des = s.nextLine();
				Transaction abc = new Transaction(d, Double.parseDouble(a), des);
				Transaction[] xyz = gl.getLedger();
				for(int i = 0; i < gl.size(); i++) {
					if(xyz[i].equals(abc)) { 
						System.out.printf("%-20s%-20s%-20s%-20s%-20s", "No.", "Date", "Debit", "Credit", "Description");
						System.out.println();
						System.out.println("----------------------------------------------------------------------------------------------");
						System.out.printf("%-20s%-20s%-40s%-20s", i+1, xyz[i].getDate(), Double.toString(xyz[i].getAmount()), xyz[i].getDescription());
					}
					else if(i == gl.size()-1) System.out.println("There is no transaction.");
				}
			}
			
			if(input.equals("S")) {
				System.out.println("There are " + gl.size() + " transactions currently in the general ledger.");
			}
			
			if(input.equals("B")) {
				backup = (GeneralLedger) gl.clone();
				System.out.println("Created a backup of the current general ledger.");
			}
			
			if(input.equals("PB")) {
				if(backup.size() == 0) System.out.println("There was no backup created.");
				else
					backup.printAllTransactions();
			}
			
			if(input.equals("CB")) {
				if(backup.size() == 0) System.out.println("There was no backup created.");
				else {
					if(gl.size() != backup.size()) System.out.println("The current ledger is not the same as the backup copy.");
					else {
						boolean same = true;
						for(int i = 0; i < gl.size(); i++) {
							if(!(gl.getLedger()[i].equals(backup.getLedger()[i]))) {
								same = false;
								break;
							}
						}
						if(same = false) System.out.println("The current ledger is not the same as the backup copy.");
						else System.out.println("The current ledger is the same as backup copy.");
					}
				}
			}
			
			if(input.equals("RB")) {
				if(backup.size() == 0) System.out.println("There was no backup created.");
				else {
					gl = (GeneralLedger) backup.clone();
					System.out.println("General ledger successfully reverted to the backup copy.");
				}
			}
			
			if(input.equals("PF")) {
				System.out.println("Financial Data for Jack's Account");
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("Assets:      $" + gl.getTotalDebitAmount());
				System.out.println("Liabilities: $" + gl.getTotalCreditAmount());
				System.out.println("Net Worth:   $" + (gl.getTotalDebitAmount()-gl.getTotalCreditAmount()));
				
			}
			
			if(input.equals("Q")) {
				quit = true;
				System.out.println("Program terminating successfully...");
			}
		}
	}
}
