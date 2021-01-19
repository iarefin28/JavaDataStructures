/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents an Item object that exists in a particular store.
 */
import java.io.Serializable;

public class Item implements Serializable {
	String itemCode; 
	String name; 
	int qtyInStore;
	int averageSalesPerDay;
	int onOrder; 
	int arrivalDay; 
	double price;
	int minQ; 
	
	/**
	 * Default constructor for the Item class. Initializes all member variables.
	 */
	public Item() {
		itemCode = ""; name = ""; qtyInStore = 0; averageSalesPerDay = 0; onOrder = 0; arrivalDay = 0; price = 0.0; minQ = averageSalesPerDay*3;
	}
	
	/**
	 * Constructor with parameters. Initializes all member variables to specific values.
	 * @param ic what itemCode should be initialized to.
	 * @param n what name should be initialized to.
	 * @param qis what qtyInStore should be initialized to.
	 * @param aspd what averageSalesPerDay should be initialized to.
	 * @param o what onOrder should be initialized to.
	 * @param p what price should be initialized to.
	 */
	public Item(String ic, String n, int qis, int aspd, int o, double p) {
		itemCode = ic; name = n; qtyInStore = qis; averageSalesPerDay = aspd; onOrder = o; price = p; minQ = averageSalesPerDay*3; arrivalDay = 0;
	}
	
	/**
	 * Returns a string representation of the Item object.
	 */
	public String toString() {
		String sItem = "";
		sItem =  String.format("%-20s%-40s%-20s%-20s%-20s%-20s%-20s", itemCode, name, Integer.toString(qtyInStore), Integer.toString(averageSalesPerDay), Double.toString(price), Integer.toString(onOrder), Integer.toString(arrivalDay));
		return sItem;
	}
	
	/**
	 * Obtains the itemCode of the Item object.
	 * @return itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * Sets the itemCode to a new value
	 * @param itemCode the new itemCode this.itemCode will be set to
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * Obtains the name of the Item object
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets name to a new value
	 * @param name the new value for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtains the quantity of the item in the store 
	 * @return qtyInStore
	 */
	public int getQtyInStore() {
		return qtyInStore;
	}
	/**
	 * Sets a new value for qtyInStore
	 * @param qty the new value for qtyInStore
	 */
	public void setQtyInStore(int qty) {
		qtyInStore = qty;
	}
	/**
	 * Obtains the avgSales of this Item
	 * @return averageSalesPerDay
	 */
	public int getAvgSales() {
		return averageSalesPerDay;
	}
	/**
	 * Sets a new value for avgSales
	 * @param avgSales the new value for avgSales
	 */
	public void setAvgSales(int avgSales) {
		averageSalesPerDay = avgSales;
	}
	/**
	 * Obtains the number of this item on order, or 0 if there is none on order
	 * @return onOrder
	 */
	public int getOnOrder() {
		return onOrder;
	}
	/**
	 * Sets a new value for onOrder
	 * @param onOrder the new value for onOrder
	 */
	public void setOnOrder(int onOrder) {
		this.onOrder = onOrder;
	}
	/**
	 * Obtains the day that this items order will arrive.
	 * @return arrivalDay
	 */
	public int getArrivalDay() {
		return arrivalDay;
	}
	/**
	 * Sets a new value for the arrivalDay of an item.
	 * @param ad the new value for arrivalDay
	 */
	public void setArrivalDay(int ad) {
		arrivalDay = ad;
	}
	/**
	 * Obtains the price of the Item
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Sets a new value for the price of the Item
	 * @param p the new value for price to be set to.
	 */
	public void setPrice(double p) {
		price = p;
	}
	/**
	 * Obtains the mimimum quantity of this Item that should be in the store.
	 * @return minQ
	 */
	public int getMinQ() {
		return minQ;
	}
}

