/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a HashedGrocery object that contains a Hash Table of keys and Item objects.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashMap;

public class HashedGrocery implements Serializable {
	int businessDay; 
	HashMap<String, Item> table;
	
	/**
	 * Default constructor of HashedGrocery object. Initializes business day and the table.
	 */
	public HashedGrocery() {
		businessDay = 1; 
		table = new HashMap<String, Item>();
	}
	
	/**
	 * This method adds an item into the table.
	 * @param item the Item object to be added to the table.
	 * @throws SameItemCodeException if an Item with the same code already exists in table.
	 */
	public void addItem(Item item) throws SameItemCodeException {
		if(table.containsKey(item.getItemCode())) throw new SameItemCodeException("This item code already exists in the hash table.");
		table.put(item.getItemCode(), item);
	}
	
	/**
	 * This updates an item quantity in the store.
	 * @param item the item that should be changed
	 * @param adjustByQty subtract this much from the current quantity.
	 */
	public void updateItem(Item item, int adjustByQty) { 
		item.setQtyInStore(item.getQtyInStore() - adjustByQty);
	}
	
	/**
	 * This method reads a file and fills the table up with all the items found in this file.
	 * @param filename the file to parse
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void addItemCatalog(String filename) throws IOException, ParseException {
		FileInputStream fis = new FileInputStream(filename);
		InputStreamReader inStream = new InputStreamReader(fis);
		BufferedReader stdin = new BufferedReader(inStream);
	    JSONParser parser = new JSONParser();
	    
	    JSONArray objs = (JSONArray) parser.parse(inStream); 
	    for(int i = 0; i < objs.size(); i++) {
	    	JSONObject obj = (JSONObject) objs.get(i);      
		    String itemCode = (String) obj.get("itemCode");
		    String itemName = (String) obj.get("itemName");
		    String qInStore = (String) obj.get("qtyInStore");
		    String avgSales = (String) obj.get("avgSales");
		    String price = (String) obj.get("price");
		    String amtOnOrder = (String) obj.get("amtOnOrder");
		    try {
		    	addItem(new Item(itemCode, itemName, Integer.parseInt(qInStore), Integer.parseInt(avgSales), Integer.parseInt(amtOnOrder), Double.parseDouble(price)));
		    	System.out.println(itemCode + ": " + itemName + " is added to inventory.");
		    } catch(SameItemCodeException e) {
		    	System.out.println(itemCode + ": Cannot add item as item code already exists.");
		    }
	    }
	}
	
	/**
	 * This method reads a file and appropriately adjusts according to the sales. It orders more of an item if running short as well.
	 * @param filename the file that contains sales information to parse
	 * @throws ParseException
	 * @throws IOException
	 */
	public void processSales(String filename) throws ParseException, IOException {
		FileInputStream fis = new FileInputStream(filename);
		InputStreamReader inStream = new InputStreamReader(fis);
		BufferedReader stdin = new BufferedReader(inStream);
	    JSONParser parser = new JSONParser();
	    
	    JSONArray objs = (JSONArray) parser.parse(inStream); 
	    for(int i = 0; i < objs.size(); i++) {
	    	JSONObject obj = (JSONObject) objs.get(i);      
		    String itemCode = (String) obj.get("itemCode");
		    String qSold = (String) obj.get("qtySold");
		    if(table.containsKey(itemCode) && Integer.parseInt(qSold) > table.get(itemCode).getQtyInStore()) {
		    	System.out.println(itemCode + ": Not enough stock for sale. Not updated.");
		    }
		    else {
			    if(table.containsKey(itemCode)) updateItem(table.get(itemCode), Integer.parseInt(qSold)); //updates after sales
			    else System.out.println(itemCode + ": Cannot buy as it is not in the grocery store.");
			    
			    if(table.containsKey(itemCode) && table.get(itemCode).getQtyInStore() < table.get(itemCode).getMinQ() && table.get(itemCode).getOnOrder() == 0) {
			    	table.get(itemCode).setOnOrder(table.get(itemCode).getAvgSales()*2);
			    	table.get(itemCode).setArrivalDay(businessDay + 3);
			    	System.out.println(itemCode + ": " + qSold + " units of " + table.get(itemCode).getName() + " are sold. Order has been placed for " + table.get(itemCode).getOnOrder() + " more units.");
			    }
			    else if(!table.containsKey(itemCode)) {}
			    else {
			    	System.out.println(itemCode + ": " + qSold + " units of " + table.get(itemCode).getName() + " are sold. ");
			    }
		    }
	    }
	}
	
	/**
	 * This method moves to the next business day and checks to see if any item on order has arrived. If so, the inventory is appropriately updated.
	 */
	public void nextBusinessDay() {
		boolean noOrders = true;
		businessDay++; 
		System.out.println("Business day: " + businessDay);
		for(Item i : table.values()) {
			if(i.getArrivalDay() != 0) {
				if(i.getArrivalDay() == businessDay) {
					i.setQtyInStore(i.getQtyInStore() + i.getOnOrder());
					System.out.println(i.getItemCode() + ": " + i.onOrder + " units of " + i.getName() + " has arrived.");
					i.setOnOrder(0);
					i.setArrivalDay(0);
					noOrders = false;
				}
			}
		}
		if(noOrders) System.out.println("No orders have arrived.");
	}
	
	/**
	 * String representation of the table. All items informations are displayed
	 */
	public String toString() {
		String result = "";
		for(Item i : table.values()) {
			result = result + i.toString() + "\n";
		}
		return result;
	}
	
	/**
	 * Obtains the current business day.
	 * @return businessDay.
	 */
	public int getBusinessDay() {
		return businessDay;
	}
}