import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

//public class hello {
   /* static void onlyEven(Collection<Integer> c) {
    	c = c.parallelStream().filter(n -> n % 2 == 0).collect(Collectors.toSet());
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Set<Integer> c = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        onlyEven(c);
        for (Integer n : c)
        	System.out.println(n);
	} */
//}

class Vehicle {}
class Car extends Vehicle {}
class Scooter extends Vehicle {}

public void aMethod() {
	Vehicle[] vehicles = new Car[10];
	vehicles[0] = new Vehicle();
	vehicles[1] = new Car();
	vehicles[2] = new Scooter();
}
