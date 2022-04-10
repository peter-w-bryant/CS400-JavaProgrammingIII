import java.util.NoSuchElementException;

// --== CS400 Project One File Header ==--
// Name: Peter Bryant
// Email: pbryant2@wisc.edu
// Team: Red
// Group: AC
// TA: Ilay Raz
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

/**
 * This class tests the HashtableMap object and methods implemented in Hashtable.java
 *
 */
public class HashtableMapTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test1: " + test1());
		System.out.println("test2: " + test2());
		System.out.println("test3: " + test3());
		System.out.println("test4: " + test4());
		System.out.println("test5: " + test5());
	}
	
	/**
	 * Tests the put(), size(), and resize() method with a small Hashtable with capcity 5.
	 * 
	 * @return true, iff all tests pass
	 */
	public static boolean test1() { 
		
		HashtableMap Map = new HashtableMap(5);
		
		// Case 1: Put node into empty table
		if(!Map.put(1, "111")) {
			return false;
		}
		
		// Case 2: Put node into non-empty table
		if(!Map.put(2, "222")) {
			return false;
		}
		Map.put(3, "333");
		//Test Size
		if(Map.size() != 3) 
			return false;
		//Ensure the capacity is 5
		if(Map.getCapacityTest() != 5) {
			return false;
		}
		Map.put(4, "444");
		//Checks the size increased to 4
		if(Map.size() != 4) {
			return false;
		}
		// 4/5= 80%, the capacity should double to 10
		if(Map.getCapacityTest() != 10) {
			return false;
		}
		
		//Case 3: Put node into hashtable which already has the key
		if(Map.put(4, "445")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Tests the remove() method with a small Hashtable with capacity 10.
	 * 
	 * @return true, iff all tests pass
	 */
	public static boolean test2() { 
		HashtableMap Map2 = new HashtableMap(10);		
		Map2.put(1, "111");
		Map2.put(2, "222");
		Map2.put(3, "333");
		Map2.put(4, "444");
		
		if(Map2.size() != 4) {
			return false;
		}
		
		//Case 1: Remove node from empty list
		HashtableMap emptyMap = new HashtableMap(10);	
		if(emptyMap.remove(2) != null) {
			return false;
		}
		
		//Case 2: Remove first node from list with more nodes
		//Checks the hashtable size is 4
		Map2.remove(1);
		//Checks that the size was decremented
		if(Map2.size() != 3) {
			return false;
		}
		//Checks if the map still contains the key that was just removed
		if(Map2.containsKey(1)) {
			return false;
		}
		
		//Case 3: Remove node from list with nodes before it
		Map2.remove(4);
		
		//Checks that the size was decremented
		if(Map2.size() != 2) {
			return false;
		}
		//Checks if the map still contains the key that was just removed
		if(Map2.containsKey(4)) {
			return false;
		}
				
		return true;
		
	}
		
	/**
	 * Tests the containsKey() method with a small Hashtable with capacity 5.
	 * 
	 * @return true, iff all tests pass
	 */
	public static boolean test3() {  
		HashtableMap Map3 = new HashtableMap(6);		
		Map3.put(1, "111");
		Map3.put(2, "222");
		Map3.put(3, "333");
		Map3.put(4, "444");
				

		//Case 1: Non-empty hashtable contains the key.
		if(!Map3.containsKey(3)) {
			return false;
		}
		//Case 2: Non-empty hashtable doesn't contain the key.
		if(Map3.containsKey(6)) {
			return false;
		}
		//Case 3: Checks an empty map
		HashtableMap Map30 = new HashtableMap(5);
		if(Map30.containsKey(6)) {
			return false;
		}
		return true;
	}
	/**
	 * Tests the get() method with a small Hashtable with capacity 5.
	 * 
	 * @return true, iff all tests pass
	 */
	public static boolean test4() { 
		
		
		//Case 1: Empty hashtable (should throw NoSuchElementException)
		HashtableMap Map4 = new HashtableMap(10);
		try {
			if(Map4.get(1) == "111") {
				return false;
			}
		}catch(NoSuchElementException e) {}
	
		//Case 2: Non-empty hashtable, key not contained (should throw NoSuchElementException)
		HashtableMap Map40 = new HashtableMap(10);		
		Map40.put(1, "111");
		Map40.put(2, "222");
		
		try {
		if(Map40.get(3) != null) {
			return false;
			}
		}catch(NoSuchElementException e) {}
		
		//Case 3: Non-empty hashtable, contains Key
		HashtableMap Map400 = new HashtableMap(10);		
		Map400.put(1, "111");
		Map400.put(2, "222");
		Map400.put(3, "333");
		if(Map400.get(1) != "111" || Map400.get(2) != "222" || Map400.get(3) != "333" ) {
			return false;
		}
		
		return true;	
	}

	/**
	 * Tests the clear() method by creating a Hashtable, adding nodes to it, clearing it, and evaluating the number of currentValues it contains. 
	 * 
	 * @return true, iff all tests pass
	 */
	public static boolean test5() { 
		HashtableMap Map5 = new HashtableMap(10);		
		Map5.put(1, "111");
		Map5.put(2, "222");
		Map5.put(3, "333");
		Map5.put(4, "444");
		Map5.put(5, "555");
		Map5.put(6, "666");
		
		Map5.clear();
		
		//Checks that the number of current values is zero
		if(Map5.getCurrentValuesTest() != 0) {
			return false;
		}
		
		//Checks that none of the previous keys are contained in the hashtable anymore
		for(int i = 1; i <7 ; i++ ) {
			if(Map5.containsKey(i)) {
				return false;
			}
		}
		
		return true;
	}	
	
}
