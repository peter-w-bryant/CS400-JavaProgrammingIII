// --== CS400 Project One File Header ==--
// Name: Peter Bryant
// Email: pbryant2@wisc.edu
// Team: Red
// Group: AC
// TA: Ilay Raz
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * @author Peter W Bryant
 * 
 * This class creates a HashtableMap object, which uses a separate HashNode class to handle chaining.
 *
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	private int capacity;						//Current Capacity of the Hashtable
	private int currentValues;					//Number of elements in the Hashtable
	private HashNode<KeyType, ValueType> Map[]; 
	
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		Map = (HashNode<KeyType, ValueType>[]) new HashNode[capacity];
		this.capacity = capacity;
		this.currentValues= 0;
	}
	
	@SuppressWarnings("unchecked")
	public HashtableMap() { // with default capacity = 20
		Map = (HashNode<KeyType, ValueType>[]) new HashNode[20];
		this.capacity = 20;
		this.currentValues = 0;
	}
	
	
	
	/**
	 * 
	 * This class creates a HashNode object, which will unify both Key and Value data types 
	 * into one object for handling collisions.
	 *
	 */
	private static class HashNode<KeyType, ValueType> {
		private KeyType key;
		private ValueType val;
		private HashNode next;
		
		public HashNode(KeyType key, ValueType val) {
			this.key = key;
			this.val = val;
		}
		
		//Helper methods
		
		//Gets the key value from a HashNode
		public KeyType getKey() {
			return key;
		}
		
		//Sets the key value of a HashNode
		public void setKey(KeyType key) {
			this.key = key;
		}
		
		//Returns the value of a HashNode
		public ValueType getVal() {
			return val;
		}
		
		//Sets the values of a HashNode
		public void setVal(ValueType val) {
			this.val = val;
		}
		
		//Returns the next HashNode in the chain
		public HashNode<KeyType, ValueType> getNext() {
			return this.next;
		}
		
		//Sets the next HashNode in the chain
		public void setNext(HashNode<KeyType, ValueType> next) {
			this.next = next;
		}
		
		
		//Checks if the key of a HashNode is the same of the param. key
		public boolean equalKey(KeyType k) {
			if(this.key == k) {
				return true;
			}
			return false;
		}
		
	}
	
	/**
	 * Method inserts HashNode object into our Hashtable. It also checks the LoadFactor 
	 * 
	 * @param the key of the object being inserted
	 * @param the value of the object being inserted
	 * @return true, iff HashNode added successfully.
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		//Case 1: Provided Key = null
		if(key == null) {
			return false;
		}
		
		HashNode<KeyType,ValueType> node = new HashNode<KeyType,ValueType>(key,  value); 	//Creates new HashNode to Add to hashTable
		int bucket = getHashIndex(key);											//Finds the index for the element in the array
		
		//Case 2: Key already in hashmap	
		if(this.containsKey(key))
		{
			return false;
		}

		//Case 3: No errors, add the node to the list
		//3.1 If the bucket is empty, add a node
		if (Map[bucket] == null) 
		{
			Map[bucket] = node;
		}
		else 
		//3.2 The bucket isn't empty, traverse the bucket to the last node. Then change the last node's
		// next node to the new node.
		{
			HashNode<KeyType,ValueType> endNode = Map[bucket];
			while(endNode != null) 
			{
				if(endNode.getNext() == null) 
				{
					endNode.setNext(node);
					break;
				}
			endNode = endNode.getNext();
			}
		}
		//Increment the amount of Key, Value pairs we have in the hashtable
		currentValues++;
		
		//Check the Load Factor, if the load factor is greater than 80% resize and rehash the entries.
		if(this.getLoadFactor() >= 0.8) {
			this.resize();
		}
		return true;	
	}
	
	
	/**
	 * Method returns the value of a node in the hashtable.
	 * 
	 * @param the key of the object whose value you are returning
	 * @return the ValueType of the object if found, otherwise null.
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		if(this.containsKey(key) == false) {
			throw new NoSuchElementException("NoSuchElementException: get()");
		}
		ValueType retVal = null;		
		int index = getHashIndex(key);
		//Searches the chain for the key, returns the value if found
		HashNode<KeyType,ValueType> travNode = Map[index];
		while(travNode != null) 
		{
			if(travNode.getKey() == key) 
			{
				return travNode.getVal();
					
			}
		travNode = travNode.getNext();
		}
		
		return retVal;
	}
		
	
	/**
	 * Method returns the number of key,value pairs in the hashtable.
	 * 
	 * @return the size of the hashtable (the number of key,value pairs).
	 */
	@Override
	public int size() {
		return currentValues;
	}
	
	/**
	 * Methods checks if a provided key is already stored in the hashtable.
	 * 
	 * @param the key being checked for
	 * @return true, iff the hashtable contains the key
	 */
	@Override
	public boolean containsKey(KeyType key) {
			//Gets the HashIndex of the key
			int index = getHashIndex(key);
			//Traverses the chain until it finds it, and returns true;
			HashNode<KeyType,ValueType> travNode = Map[index];
			while(travNode != null) 
			{	
				if(travNode.getKey().equals(key)) 
				{
					return true;
				}
			travNode = travNode.getNext();
			}	
			
		return false;
	}

	
	/**
	 * Method removes a hashnode object from the hashtable, provided its key.
	 * 
	 * @param the key of the object being removed
	 * @return the ValueType of the object if successfully removed, otherwise null
	 */
	@Override
	public ValueType remove(KeyType key) {
		//Checks if hashtable already contains the key
		if(this.containsKey(key) == false) {
			return null;
		}
		//Finds the hash index of the key
		int index = getHashIndex(key);
		
		//Case 1: No keys exist in this location
		if(Map[index] == null) {
			return null;
		}
		
		HashNode<KeyType,ValueType> travNode = Map[index];
		ValueType retVal = null;
		
		//Case 2: It is the first node.
		if(travNode.getKey().equals(key)) {
			//Store the Value of the node corresponding to the key
			retVal= travNode.getVal();
			//If there other nodes after this node, replace this node with the next node
			if(travNode.getNext() != null) {
			travNode = travNode.getNext();
			}else {
				//If there are no other nodes in the chain, set this node to null
				Map[index] = null;
			}
			currentValues--;
			return retVal;
		}
		
		HashNode<KeyType,ValueType> nextNode = travNode.getNext();
		
		//Case 3: It is farther in the chain.
		while( (nextNode != null)&&(!nextNode.getKey().equals(key))) 
		{
			nextNode = nextNode.getNext();
			travNode = nextNode;
		}
			//If nextNode is not null, then it is the key.
			if(nextNode != null) 
			{
				//Skip the HashNode corresponding to the key
				travNode.setNext(nextNode.getNext());
				currentValues--;
				return nextNode.getVal();
			}
			
		return retVal;
	}
	
	/**
	 * Method clears all HashNode objects from the hashtable.
	 */
	@Override
	public void clear() {
		//Loop through all indexes of the Map array
		for(int i = 0; i< capacity ; i++) {
			HashNode<KeyType,ValueType> travNode = Map[i];
			//Loop through all nodes at each index
			while(travNode != null) 
			{
				//Remove the element at this index
				remove(travNode.getKey());
				//Break to next index if this chain is empty
				if(travNode.getNext() == null) 
				{
					break;
				}
			travNode = travNode.getNext();
			}		
		}
		currentValues = 0;
	}


//Helper Methods
	
	/**
	 * Computes the absolute value of a number.
	 * 
	 * @param the number being evaluated
	 * @return the absolute value of the number, type int
	 */
	private int abs(int num) {
		if(num<0) {
			return num *= -1;
		}
		return num;
	}
	
	/**
	 * Returns the Hash Index provided a key value.
	 * 
	 * @param the key
	 * @return the hashcode, type int
	 */
	private int getHashIndex(KeyType key) {
		return abs(key.hashCode()) % capacity;
	}
	
	/**
	 * Computes the Load Factor of the current hashtable.
	 * 
	 * @return the Load Factor, the ratio of stored values to the capacity of the hashtable, type double
	 */
	private double getLoadFactor() {
		return (double) currentValues/capacity;
	}
	
	/**
	 * Resizes the current hashtable by doubling the current hashtables size and rehashes all of the currentValues according to the
	 * increased capacity. 
	 *  
	 * @return the new capacity, twice the previous capacity
	 */
	private int resize() {
		int oldCapacity = capacity;
		capacity *= 2;
		//Creating a deep copy of the Map[] array to store the Key-Value pairs
		HashNode<KeyType, ValueType> copyMap[] = Map.clone(); 
		//Set Map[] to a new array that is double the size
		Map= (HashNode<KeyType, ValueType>[]) new HashNode[capacity];
		//Since no new values have been added to the new array, clear the currentValues
		currentValues= 0;
		//Loop through all of Map[]'s indexes
		for(int i =0; i< oldCapacity; i++) {
			HashNode<KeyType,ValueType> travNode = copyMap[i];
			//Loop through each chain of nodes
			while(travNode != null) 
			{
				//Add the node to the larger Map[]
				put(travNode.getKey(), travNode.getVal());
				//Break to the next index if there are no more nodes in the chain
				if(travNode.getNext()== null) 
				{
					break;
				}
				
			travNode = travNode.getNext();
			}	
		
		}
	return capacity;
	}
	
	
	//FOR TESTING USE ONLY
	
	/**
	 * Tester method returns the current capacity value.
	 *  
	 * @return the capacity of the array, type int
	 */
	public int getCapacityTest() {
		return capacity;
	}
	
	/**
	 * Tester method returns the number of current values in the hashtable.
	 *  
	 * @return the number of current values, type int
	 */
	public int getCurrentValuesTest() {
		return currentValues;
	}
}

