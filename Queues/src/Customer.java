/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a Customer object that will eat at a Restaurant.
 */
public class Customer {
	private static int totalCustomers;
	private int orderNumber; //will be determined with the totalCustomers variable
	private String food;
	int priceOfFood;
	int timeArrived;
	int timeToServe; 
	
	/**
	 * Default constructor for the Customer object. Initializes all variables. [Not used in program]
	 */
	public Customer() {
		orderNumber = 0;
		food = "";
		priceOfFood = 0;
		timeArrived = 0;
		timeToServe = 0; 
	}
	
	/**
	 * Constructor with parameters for Customer object. Initializes the timeArrived variable.
	 * @param tA the variable that time arrived will be set to.
	 */
	public Customer(int tA) {
		timeArrived = tA;
	}
	
	/**
	 * This method returns a String representation of the customer object.
	 */
	public String toString() {
		String b = "";
		if(food == "Steak") b = "S";
		else if(food == "Chicken Wings") b = "CW";
		else if(food == "Chicken Tenders") b = "CT";
		else if(food == "Cheeseburger") b = "C";
		else if(food == "Grilled Cheese") b = "GC";
		return "[#" + orderNumber + ", " + b + ", " + timeToServe + " min.]";
	}
	
	/**
	 * @return Returns the totalCustomers that have entered and left the restaurants.
	 */
	public static int getTotalCustomers() {
		return totalCustomers;
	}
	
	/**
	 * This methods sets a new value for totalCustomers.
	 * @param tC the new value totalCustomers will be set to. 
	 */
	public static void setTotalCustomers(int tC) {
		totalCustomers = tC;
	}
	
	/**
	 * @return Returns the orderNumber variable.
	 */
	public int getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * This method sets a new value for orderNumber
	 * @param orderNum the new value orderNumber will be set to.
	 */
	public void setOrderNumber(int orderNum) {
		orderNumber = orderNum;
	}

	/**
	 * This method sets a new value for food.
	 * @param food the new value food will be set to.
	 */
	public void setFood(String food) {
		this.food = food;
	}
	
	/**
	 * This method sets a new value for the priceOfFood variable
	 * @param price the new price priceOfFood will be set to
	 */
	public void setPriceOfFood(int price) {
		priceOfFood = price; 
	}
	
	/**
	 * This method obtains the priceOfFood for a certain customer.
	 * @return priceOfFood
	 */
	public int getPriceOfFood() {
		return priceOfFood;
	}
	
	/**
	 * This method obtains the simUnit that the customer arrived in.
	 * @return timeArrived
	 */
	public int getTimeArrived() {
		return timeArrived;
	}
	
	/**
	 * This method sets a new value for the timeToServe variable
	 * @param time the new time timeToServe will be set too.
	 */
	public void setTimeToServe(int time) {
		timeToServe = time;
	}
	
	/**
	 * @return This method returns time toToServe
	 */
	public int getTimeToServe() {
		return timeToServe;
	}
}
