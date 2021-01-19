/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a GroceryDriver class in which the user can interact with.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import org.json.simple.parser.ParseException;

/**
 * The main engine of the GroceryDriver class. All the things that the user can do are stored in this method.
 */
public class GroceryDriver {
	public static void main(String[] args) throws IOException {
		HashedGrocery grocery = null;
		try {
			FileInputStream file = new FileInputStream("Grocery");
			ObjectInputStream inStream = new ObjectInputStream(file);
			grocery = (HashedGrocery) inStream.readObject();
			System.out.println("HashedGrocery is loaded from grocery.obj\n");
			System.out.println("Business Day " + grocery.getBusinessDay() + ".");
		}
		catch(IOException | ClassNotFoundException e) {
			System.out.println("Grocery.obj does not exist. Creating new HashedGrocery object...");
			grocery = new HashedGrocery();
		}
		
		boolean quit = false; 
		while(!quit) {
			Scanner s = new Scanner(System.in);
			
			System.out.println("Menu: \n\n(L)\tLoad item catalog\n(A)\tAdd items\n"
					+ "(B)\tProcess Sales\n(C)\tDisplay all items\n(N)\tMove to next business day\n"
					+ "(Q)\tQuit\n");
			
			System.out.println("Enter option: ");
			String input = s.nextLine();
			input = input.toUpperCase();
			
			if(input.equals("L")) {
				System.out.println("Enter file to load: ");
				String file = s.nextLine();
				try {
					grocery.addItemCatalog(file);
				} catch (ParseException e) {
					System.out.println("The specified file does not exist.");
				} catch (FileNotFoundException e) {
					System.out.println("The specified file does not exist.");
				}
			}
			
			if(input.equals("A")) {
				System.out.print("Enter item code: ");
				String itemCode = s.nextLine();
				System.out.print("Enter item name: ");
				String itemName = s.nextLine();
				System.out.print("Enter Quantity in store: ");
				int qis = s.nextInt();
				System.out.print("Enter Average sales per day: ");
				int aspd = s.nextInt();
				System.out.print("Enter price: ");
				double p = s.nextDouble();
				try {
					grocery.addItem(new Item(itemCode, itemName, qis, aspd, 0, p));
					System.out.println();
					System.out.println(itemCode + ": " + itemName + " added to inventory.");
				} catch (SameItemCodeException e) {
					System.out.println();
					System.out.println("This item could not be added because an item with the same item code exists.");
				}
			}
			
			if(input.equals("B")) {
				System.out.println("Enter filename: ");
				String file = s.nextLine();
				try {
					grocery.processSales(file);
				} catch (ParseException e) {
					System.out.println("The specified file does not exist.");
				} catch (FileNotFoundException e) {
					System.out.println("The specified file does not exist.");
				}
			}
			
			if(input.equals("C")) {
				System.out.printf("%-20s%-40s%-20s%-20s%-20s%-20s%-20s", "Item code", "Name", "Qty", "AvgSales", "Price", "OnOrder", "ArrOnBusDay");
				System.out.println();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(grocery.toString());
			}
			
			if(input.equals("N")) {
				System.out.println("Advancing business day..."); 
				grocery.nextBusinessDay();
			}
			
			if(input.equals("Q")) {
				quit = true;
				try {
					FileOutputStream file = new FileOutputStream("Grocery");
					ObjectOutputStream outStream = new ObjectOutputStream(file);
					outStream.writeObject(grocery);
					outStream.close();
					file.close();
					System.out.println("HashedGrocery is saved in grocery.obj.");
					
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

