// --== CS400 Project TWO File Header ==--
// Name: Peter Bryant
// Email: pbryant2.wisc.edu
// Team: red
// Group: AC
// TA: Ilay Raz
// Lecturer: Gary Dahl

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * VetManagerFrontEndInterface provides a template and comments for all methods implemented in the VetManagerFrontEnd class.
 * 
 * @author Peter W Bryant
 */
interface VetManagerFrontEndInterface{

	/**
	 * Begins running the program by asking the user for an operation to perform.
	 */
	public void run(ExtendedSortedCollectionInterface <DogNode, String, Integer, String> list);
	

	/**
	 * Prompts a user input regarding the dog they are inserting.
	 * 
	 * @return true, iff insertion successful
	 */
	public boolean insertNewDog(ExtendedSortedCollectionInterface <DogNode, String, Integer, String> list, Scanner newScanner);
	

	/**
	 * Prompts a user for a dog name to search for and prints a list of the found dogs.
	 * 
	 * @return true, iff search-by-name successful
	 */
	public boolean searchDogName(ExtendedSortedCollectionInterface <DogNode, String, Integer, String> list, Scanner newScanner);
	

	/**
	 * Prompts a user for a dog's type and prints a list of the found dogs of that type.
	 * 
	 * @return true, iff search-by-type successful
	 */
	public boolean searchDogType(ExtendedSortedCollectionInterface <DogNode, String, Integer, String> list, Scanner newScanner);
	
	/**
	 * This method performs an INORDER traversal of the RB tree and prints out its contents.
	 * 
	 * @return true, iff tree printed successfully
	 */
	public boolean displayTree(ExtendedSortedCollectionInterface <DogNode, String, Integer, String> list);

}

/**
 * VetManagerFrontEnd is a class that provides methods for user input and other functionality for Front End Development.
 * 
 * @author Peter W Bryant
 */
public class VetManagerFrontEnd implements VetManagerFrontEndInterface  {
	
	public static void main(String[] args){
		ExtendedSortedCollectionInterface back = new DogDatabase();	       
	    VetManagerFrontEnd front = new VetManagerFrontEnd();
	    front.run(back);
	}

	/**
	 * Begins running the program by prompting the user for an operation to perform. It requires the following steps,
	 * (1) It requires the user to input '1' to access the database
	 * (2) It prompts the user for input '0-4' to perform from 5 different operations
	 * (3) After the user has finished an operation, they will be able to perform an additional operation or exit the program.
	 * @param list, a back end object
	 */
	@Override
	public void run(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list) {
		 int user_input = -1;
		 Scanner newScanner = new Scanner(System.in);
		 System.out.println("Enter '1' to access the database.");		 
		 user_input = newScanner.nextInt();
		 if(user_input != 1) {System.out.println("--Access Denied--");}
		 System.out.println("--Access Granted--");
		 System.out.println("Welcome to the Vet Dog Management Program!");
		 System.out.println("What operation would you like to preform?");
		 System.out.println("1: Add a New Dog to the Database\n" +
	                		"2: Get a List of Dogs with a Certain Name\n" +
	                		"3: Get a List of Dogs that are a Certain Breed\n" +
	                		"4: Get a List of all Dogs in the Database\n" +
				 			"0: Exit Management Application");
		
		 while(user_input != 0) {
			try {
			 
			 System.out.println("Please input a command -> 0-4");
	        user_input = newScanner.nextInt();
	        //Case 0: User inputs value that is equal to 0
	        //Executes: Prints exit message and breaks from program execution
	        if(user_input == 0) {
	        	System.out.println("Exiting Management Application...");
	        	System.out.println("Please come again!");
	        	}
	        //Case 1: User inputs value that is equal to 1
	        //Executes: Calls InsertNewDog() helper method
	        else if(user_input == 1){	        	  
	        	insertNewDog(list, newScanner);
	        	}
	        //Case 2: User inputs value that is equal to 2
	        //Executes: Calls searchDogName() helper method
	        else if(user_input == 2){	  	        	
	        	searchDogName(list, newScanner);
	        	}  
	        //Case 3: User inputs value that is equal to 3
	        //Executes: Calls searchDogType() helper method
	        else if(user_input == 3){	  
	        	searchDogType(list, newScanner);
	        	}
	        //Case 4: User inputs value that is equal to 3
	        //Executes: Calls searchDogType() helper method
	        else if(user_input == 4){	  
	        	displayTree(list);
	        	}
	        //Case X (Invalid Input): User inputs value that is not from 0-4
	        //Executes: Prints out "Invalid command: Command must be an integer from 0-4"
	        else {System.out.println("Invalid command: Command must be an integer from 0-4");}       
		
		}catch(Exception e) {
			e.printStackTrace();
			newScanner.nextLine();
			continue;
		}
	}
	}
	
	
	/**
	 * Prompts a user for input regarding the dog they are inserting. The method will ask the user for the following,
	 * (1) What the name of the dog being inserted is.
	 * (2) What the type of dog being inserted is.
	 * (3) What the dog being inserted's date of birth is.
	 * Once all of these fields are provided by the user, the method will insert the dog object into the backend object.
	 * 
	 * @param list, the back end object
	 * @param newScanner, a scanner object passed from the run() method to collect user input
	 * @return true, iff insertion successful
	 */
	@Override
	public boolean insertNewDog(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list, Scanner newScanner) {
		try {
		String dogName = "";
		String dogType = "";
		int birthYear = 0;
		//Get the Name of the Dog Being Inserted
		System.out.println("What is the name of the dog you are inserting?");	
		newScanner.nextLine();
		dogName = newScanner.nextLine().trim();
		//System.out.println(dogName);	
		
		//Get the Type of Dog Being Inserted
		System.out.println("What is the type of the dog you are inserting?");	
		dogType = newScanner.nextLine().trim();
		//System.out.println(dogType);
		
		//Get the DOB of the dog being inserted
		System.out.println("What is the dog's date of birth?");	
		birthYear = newScanner.nextInt();
		//System.out.println(birthYear);

		//Insert the New Song into the BackEnd's List
		list.insert(dogName, birthYear, dogType);
		System.out.println("Successfully added |" + dogName + ", " + dogType +  ", " + birthYear + "|");
		return true;
		
		}catch(Exception e) {
			System.out.println("Error: Exception thrown in inserNewDog().");
			e.printStackTrace();
			return false;
			
		}
	
	}

	/**
	 * Prompts a user for a dog name to search the database for and prints a list of the found dogs with that name. 
	 * The method will ask the user for the following,
	 * (1) What the name of the dog being inserted is.
	 * 
	 * Once all of these fields are provided by the user, the method will output a list of Dogs with the given name.
	 * 
	 * @param list, the back end object
	 * @param newScanner, a scanner object passed from the run() method to collect user input
	 * @return true, iff search-by-name successful
	 */
	@Override
	public boolean searchDogName(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list, Scanner newScanner) {
		try {
		String dogName = "";
		DogNode[] dogList;
		System.out.println("What is the name of the dog you are searching for?");
		newScanner.nextLine();
		dogName = newScanner.nextLine();
		dogList = (DogNode[]) list.searchByName(dogName);
		int listLen = dogList.length;
		System.out.println("There are "+ listLen + " dogs with the name " + dogName + "." );
		for(int i = 0; i< listLen ; i++) {
			System.out.println("|"+dogName+ " at index "+ i + ": " + dogList[i].getName() + " " +  dogList[i].getBirthYear() + " " + dogList[i].getType() + "|");
		}
		}catch(Exception e) {
			System.out.println("Error: Exception thrown in searchDogName().");
			return false;
			
		}
		return true;
	}

	/**
	 * Prompts a user for a dog's type and prints a list of the found dogs of that type. The method will ask the user for the following,
	 * (1) What the type of the dog being inserted is.
	 * 
	 * Once all of these fields are provided by the user, the method will output a list of Dogs with the given name.
	 * 
	 * @param list, the back end object
	 * @param newScanner, a scanner object passed from the run() method to collect user input
	 * @return true, iff search-by-name successful
	 */
	@Override
	public boolean searchDogType(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list, Scanner newScanner) {
		try {
		String dogType = "";
		DogNode[] dogList;
		System.out.println("What is the type of the dog you are searching for?");
		newScanner.nextLine();
		dogType = newScanner.nextLine();
		dogList = (DogNode[]) list.searchByType(dogType);		
		int listLen = dogList.length;
		System.out.println("There are "+ listLen + " dogs of the type " + dogType + "." );
		for(int i = 0; i< listLen ; i++) {
			System.out.println("|"+ dogType+ "-"+ i + ": "+ dogList[i].getName() + " " +  dogList[i].getBirthYear() + " " + dogList[i].getType() +"|");
		}
		}catch(Exception e) {
			System.out.println("Error: Exception thrown in searchDogType().");
			return false;
			
		}
		return true;
	}

	/**
	 * This method performs an INORDER traversal of the RB tree and prints out its contents.
	 * 
	 * @param list, the back end object
	 * @return true, iff search-by-name successful
	 */
	@Override
	public boolean displayTree(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list) {
		try {
		System.out.println(list.toString());
		return true;
		}catch(Exception e) {
			System.out.println("Error: Exception found in VetManagerFrontEnd.displayTree()");
			e.printStackTrace();
			return false;
		}
	}
}

class VetManagerFrontEndPlaceholder implements VetManagerFrontEndInterface {
	@Override
	public void run(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list) {
        System.out.println("Placeholder");
    }
	@Override
	public boolean insertNewDog(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list, Scanner newScanner) {
		 System.out.println("Placeholder");
		return false;
	}

	@Override
	public boolean searchDogName(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list, Scanner newScanner) {
		 System.out.println("Placeholder");
		return false;
	}

	@Override
	public boolean searchDogType(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list, Scanner newScanner) {
		 System.out.println("Placeholder");
		return false;
	}

	@Override
	public boolean displayTree(ExtendedSortedCollectionInterface<DogNode, String, Integer, String> list) {
		System.out.println("Placeholder");
		return false;
	}
}

