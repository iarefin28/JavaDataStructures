/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a DiningSimulator, where the simulation takes place.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class DiningSimulator {
	Collection<Restaurant> restaurants; 
	private int chefs; 
	private int duration; 
	private double arrivalProb; 
	private int maxCustomerSize;
	private int numRestaurants; 
	private int customersLost;
	private int totalServiceTime;
	private int customersServed;
	private int profit;
	
	/**
	 * This method serves as a constructor for the DiningSimulator object that is used in the simulation.
	 * @param r represents the number of restaurants for the simulation
	 * @param c represents the maximum number of customers in each restaurant for the simulation
	 * @param p represents the arrival probability of a customer for the simulation
	 * @param ch represents the number of chefs in each restaurant for the simulation
	 * @param s represents how many simulation units the simulation will run for
	 * @throws ImproperArgumentException if any of the parameters are inappropiate values. These values would cause errors in the simulation.
	 */
	public DiningSimulator(int r, int c, double p, int ch, int s) throws ImproperArgumentException {
		if(r < 1 || c < 1 || p < 0 || p > 1 || ch < 1 || s < 1) throw new ImproperArgumentException("Invalid Arguments.");
		restaurants = new ArrayList<Restaurant>();
		numRestaurants = r;
		maxCustomerSize = c;
		arrivalProb = p;
		chefs = ch;
		duration = s;
		customersLost = 0;
	}
	
	/**
	 * This method is where each simulation actually happens.
	 * @param simUnit The simUnit that is taking place.
	 * @param a The DiningSimulator object that is being simulated.
	 */
	public double simulate(int simUnit, DiningSimulator a) {
		for(int x = 0; x < a.restaurants.size(); x++) {
			for(int y = 0; y < ((ArrayList<Restaurant>) a.restaurants).get(x).size(); y++) {
				//System.out.println(((ArrayList<Restaurant>) a.restaurants).get(x).size());
				if(((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().get(y).getTimeToServe() == 0) {
					Customer z = ((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().get(y);
					a.totalServiceTime += (simUnit - z.getTimeArrived())*5;
					//System.out.println(a.totalServiceTime);
					customersServed += 1;
					profit += z.getPriceOfFood();
					System.out.println("Customer #" + z.getOrderNumber() + " has enjoyed their food! $" + z.getPriceOfFood() + " profit!");
					((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().removeFirstOccurrence(z);
					y--;
					//for(int i = ((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().indexOf(z); i < ((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().size(); i++) {
						//((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().set(i, ((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().get(i+1));
					//}
					//System.out.println(((ArrayList<Restaurant>) a.restaurants).get(x).size());
				}
				
		
			}
		}
		
		
		
		ArrayList<Customer> ca = new ArrayList<Customer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		for(int i = 0; i < a.numRestaurants; i++) {
			for(int j = 0; j < 3; j++) {
				double d = Math.random();
				if(d < a.arrivalProb) {
					Customer cust = new Customer(simUnit);
					ca.add(cust);
					res.add(i);
				}
			}
		}
		
		for(int i = 0; i < ca.size(); i++) {
			Customer.setTotalCustomers(Customer.getTotalCustomers() + 1);
			System.out.println("Customer #" + Customer.getTotalCustomers() + " has entered Restaurant " + (int) (res.get(i)+1));
		}
		Customer.setTotalCustomers(Customer.getTotalCustomers() - ca.size());
		for(int i = 0; i < ca.size(); i++) {
			Customer.setTotalCustomers(Customer.getTotalCustomers() + 1);
			if(((ArrayList<Restaurant>) a.restaurants).get(res.get(i)).size() < a.maxCustomerSize) {
				int b = randInt(1, 100); String food = ""; int price = 0; int time = 0;
				if(1 >= 0 && b <= 20) {
					food = "Cheeseburger";
					price = 15;
					time = 25;
				}
				else if(b > 20 && b <= 40) {
					food = "Steak";
					price = 25;
					time = 30;
				}
				else if(b > 40 && b <= 60) {
					food = "Grilled Cheese";
					price = 10;
					time = 15; 
				}
				else if(b > 60 && b <= 80) {
					food = "Chicken Tenders";
					price = 10;
					time = 25;
				}
				else if(b > 80 && b <= 100) {
					food = "Chicken Wings";
					price = 20;
					time = 30;
				}
				if(a.chefs == 4) time -= 5;
				else if(a.chefs >= 5) time -= 10;
				else if(a.chefs == 2) time += 5;
				else if(a.chefs == 1) time += 10;
				ca.get(i).setFood(food);
				ca.get(i).setPriceOfFood(price);
				ca.get(i).setOrderNumber(Customer.getTotalCustomers());
				ca.get(i).setTimeToServe(time + 15);
				((ArrayList<Restaurant>) a.restaurants).get(res.get(i)).enqueue(ca.get(i));
				System.out.println("Customer #" + Customer.getTotalCustomers() + " has been seated with the order " + food); 
			}                    
			else {
				System.out.println("Customer #" + Customer.getTotalCustomers() + " cannot be seated! They have left the restaurant.");
				a.customersLost++;
			}
		}
		
		for(int i = 0; i < restaurants.size(); i++) {
			System.out.println("R" + (int) (i+1) + ": {" + ((ArrayList<Restaurant>) a.restaurants).get(i).toString() + "}");
		}
		
		return 0;
	}
	
	/**
	 * This is a helper method for simulate and generates a random value from minVal to maxVal, inclusive.
	 * @param minVal The minimum value to generate a number from
	 * @param maxVal The maximum value to generate a number from
	 * @return of type int, a random number from min to max value.
	 */
	private int randInt(int minVal, int maxVal) {  
		  return (int) (Math.random() * (maxVal - minVal + 1) + minVal);
	}	
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]) {
		boolean quit = false;
		
		while(!quit) {
			Scanner s = new Scanner(System.in);
			System.out.println("Starting simulator... \n");
			System.out.print("Enter the number of restaurants: ");
			int nR = s.nextInt();
			System.out.print("Enter the maximum number of customers a restaurant can serve: ");
			int mC = s.nextInt();
			System.out.print("Enter the arrival probablity of a customer: ");
			double aP = s.nextDouble();
			System.out.print("Enter the number of chefs: ");
			int nChefs = s.nextInt();
			System.out.print("Enter the number of simulation units: ");
			int sUnits = s.nextInt();
			try {
				DiningSimulator a = new DiningSimulator(nR, mC, aP, nChefs, sUnits);
				for(int i = 0; i < a.numRestaurants; i++) {
					a.restaurants.add(new Restaurant());
				}
				
				Customer.setTotalCustomers(0);
				for(int i = 1; i <= a.duration; i++) {
					System.out.println("Time: " + i);
					a.simulate(i, a);
					
					for(int x = 0; x < a.restaurants.size(); x++) {
						for(int y = 0; y < ((ArrayList<Restaurant>) a.restaurants).get(x).size(); y++) {
							((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().get(y).setTimeToServe(((ArrayList<Restaurant>) a.restaurants).get(x).getQueue().get(y).getTimeToServe()-5);
						}
					}
				}
				System.out.println();
				System.out.println("Simulation ending...\n\n\n\n");
				System.out.println("Total customer time: " + a.totalServiceTime);
				System.out.println("Total customers served: " + a.customersServed);
				double d = a.totalServiceTime/a.customersServed;
				System.out.println("Average customer time lapse: " + String.format("%.2f", d) + " minutes per order");
				System.out.println("Total Profit: " + a.profit);
				System.out.println("Customers that left: " + a.customersLost);
				
				System.out.println("Would you like to try another simulation?(y/n)");
				String ans = s.next();
				if(ans.equals("n")) {
					quit = true;
					System.out.println("Program terminating normally...");
				}
			}
			catch(ImproperArgumentException i){
				System.out.println("Simulation could not be started.");
				System.out.println("There must be one or more restaurants, maximum customers, number of chefs and simulation units. Also the arrival probability should be in between 0 and 1 inclusive. Please enter proper values");
			}
		}
	}
}

