import java.lang.invoke.MethodHandles;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.ConsoleLauncher;

public class VetAppTests {
   /*
    public static void main(String[] args) {
        VetAppTests test = new VetAppTests();
        test.BackEndDeveloper_testInsert();
        test.BackEndDeveloper_testSearch();
        test.BackEndDeveloper_testChangeVaccinationDate();
	test.FrontEndDeveloper_testInsertNewDog();
	test.FrontEndDeveloper_testSearchDogName();
        test.FrontEndDeveloper_testSearchDogType();
        test.FrontEndDeveloper_testDisplayTree();
    }
    */
    
  
    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {
                "-cp",
                classPath,
                "--include-classname=.*",
                "--select-class=" + className };
        ConsoleLauncher.main(arguments);
        VetAppTests test = new VetAppTests();
        test.BackEndDeveloper_testInsert();
        test.BackEndDeveloper_testSearch();
        test.BackEndDeveloper_testChangeVaccinationDate();
        test.FrontEndDeveloper_testInsertNewDog();
        System.out.println("Start of Main");
        test.FrontEndDeveloper_testSearchDogName();
        test.FrontEndDeveloper_testSearchDogType();
	test.FrontEndDeveloper_testDisplayTree();
    }

    /**
     * Test if the initiation of dog node successfully 
     * @author Gaven Wang (DataWranglerDeveloper)
     */
    @Test
    public void DataWranglerDeveloper_testNewNode(){
        DogNode d1=new DogNode("Dog1",2002,"white");
        DogNode d2=new DogNode("Dog2",2005,"black");
        assertEquals("Dog1", d1.getName());
        assertEquals(2005,d2.getBirthYear());
        assertEquals("white", d1.getType());
    }
    
    /**
     * Test if the boolean method worked
     * @author Gaven Wang (DataWranglerDeveloper)
     */
    @Test
    public void DataWranglerDeveloper_testBoolean(){
        DogNode d1=new DogNode("Dog1",2002,"white");
        DogNode d2=new DogNode("Dog2",2005,"black");
        assertEquals(false, d1.vaccinationStatus());
        assertEquals(false, d2.isRed());
        d1.setVaccinationStatus(true);
        assertEquals(true, d1.vaccinationStatus());
    }
    /**
     * Test if dog node is comparable
     * @author Gaven Wang (DataWranglerDeveloper)
     */
    @Test
    public void DataWranglerDeveloper_testComparable(){
        DogNode d1=new DogNode("Dog1",2002,"white");
        DogNode d2=new DogNode("Dog1",2005,"black");
        assertEquals(false, d1.equals(d2));
        assertEquals(-1,d1.compareTo(d2));
    }
    /**
     * tests if the BackEnd insert method correctly add dogs into the database. If insertions are successful as expected,
     * the size method should return the correct number of dogs in the database, and the contains method should indicate
     * any dog that has been added is in the database. Any unexpected exception thrown will also mean failing the test.
     * @author Roujia Wang (BackEndDeveloper)
     */
    @Test
    public void BackEndDeveloper_testInsert(){
        try {
            DogDatabase dogs = new DogDatabase();

            // check size and contains after adding 2 dogs with insert method
            dogs.insert("Apple", 2012, "Bulldog");
            dogs.insert(new DogNode("Juice", 2019, "Poodle"));
            assertEquals(2, dogs.size(), "BackEndDeveloper_testInsert()\nwrong size after 2 successful insertion");
            assertTrue(dogs.contains(new DogNode("Apple", 2012, "Bulldog"))
                    && dogs.contains(new DogNode("Juice", 2019, "Poodle")),
                    "BackEndDeveloper_testInsert()\nshould contain the first 2 nodes inserted");

            // check size and contains after adding 6 more dogs with insert method
            dogs.insert(new DogNode("Beverage", 2018, "Beagle"));
            dogs.insert("Sundaes", 2016, "Beagle");
            dogs.insert("Apple", 2015, "Rottweiler");
            dogs.insert("Foxhound", 2010, "Hairless Terrier");
            dogs.insert(new DogNode("Water spaniel", 2015, "Shepherd Dog"));
            dogs.insert(new DogNode("Kelpie", 2019, "Azawakh"));
            assertEquals(8, dogs.size(), "BackEndDeveloper_testInsert()\nwrong size after 5 successful insertion");
            assertTrue(dogs.contains(new DogNode("Beverage", 2018, "Beagle"))
                    && dogs.contains(new DogNode("Kelpie", 2019, "Azawakh")),
                    "BackEndDeveloper_testInsert()\nshould contain the 3rd and 8th nodes inserted");

        }catch (Exception e){
            e.printStackTrace();
            fail("BackEndDeveloper_testInsert()\nunexpected exception thrown");
        }
    }

    /**
     * tests the BackEnd search-by-name and search-by-type methods. If the argument is valid (a non-null string), either search
     * method should return an array of dogs with non-negative integer length that corresponds to the number of dos found
     * (which is 0 if no dog is found). This is what the test is based on. Any unexpected exception thrown will mean
     * failing the test.
     * @author Roujia Wang (BackEndDeveloper)
     */
    @Test
    public void BackEndDeveloper_testSearch(){
        try{
            // creates a collection of 8 dogs
            DogDatabase dogs = new DogDatabase();
            dogs.insert("Apple", 2012, "Bulldog");
            dogs.insert(new DogNode("Juice", 2019, "Poodle"));
            dogs.insert(new DogNode("Beverage", 2018, "Beagle"));
            dogs.insert("Sundaes", 2016, "Beagle");
            dogs.insert("Apple", 2015, "Rottweiler");
            dogs.insert("Foxhound", 2010, "Hairless Terrier");
            dogs.insert(new DogNode("Water spaniel", 2015, "Shepherd Dog"));
            dogs.insert(new DogNode("Kelpie", 2019, "Azawakh"));

            assertEquals(2, dogs.searchByName("Apple").length,
                    "BackEndDeveloper_testSearch()\nshould find two dogs with name Apple");
            assertEquals(2, dogs.searchByType("Beagle").length,
                    "BackEndDeveloper_testSearch()\nshould find two dogs with type Beagle");
            assertEquals(0, dogs.searchByName("Alfred").length,
                    "BackEndDeveloper_testSearch()\nshould find no dog with name Alfred");


        }catch (Exception e){
            e.printStackTrace();
            fail("BackEndDeveloper_testSearch()\nunexpected exception thrown");
        }
    }

    /**
     * tests if the BackEnd changeVaccinationDate method can successfully change the vaccination date information of a
     * specific dog (with the exact same date, birthYear, and type as arguments) in the database. Success is defined
     * by the mutator returning true. Unexpected exceptions thrown in the process also means failing the test.
     * @author Roujia Wang (BackEndDeveloper)
     */
    @Test
    public void BackEndDeveloper_testChangeVaccinationDate(){
        try {
            DogDatabase dogs = new DogDatabase();
            dogs.insert("Apple", 2012, "Bulldog");
            dogs.insert(new DogNode("Juice", 2019, "Poodle"));
            dogs.insert("Beverage", 2018, "Beagle");
            assertTrue(dogs.changeVaccinationDate("2021", "Apple", 2012, "Bulldog"),
                    "BackEndDeveloper_testChangeVaccinationDate()\nchange for Apple should work");
            assertTrue(dogs.changeVaccinationDate("2020", "Juice", 2019, "Poodle"),
                    "BackEndDeveloper_testChangeVaccinationDate()\nchange for Juice should work");
            assertFalse(dogs.changeVaccinationDate("2019", "Beverage", 2016, "Beagle"), // wrong birthYear
                    "BackEndDeveloper_testChangeVaccinationDate()\nchange for Beverage should fail because there is no corresponding dog");

        }catch (Exception e){
            e.printStackTrace();
            fail("BackEndDeveloper_testChangeVaccinationDate()\nunexpected exception thrown");
        }
    }
    
    
    /**
     * This is a JUNIT Test method tests the functionality of the Front-End insertNewDog() method.
     * ->This test method creates a new TextUITester object to simulate user input corresponding to
     * 1. Accessing the database (1\n)
     * 2. Selecting add a new dog to the database (1\n)
     * 3. Storing "GoodDog" as the Dog's Name, "Retriever" as the Dog's Type, and (int) 2000 as the dog's DOB. (GoodDog\nRetriever\n2000\n)
     * 4. Finally, the user will exit the database. (0\n)
     *
     * The output of the previous commands will be checked for correct output and confirmation that the insertion was successful.
     * @author Peter Bryant (Front End Developer)
     */
    @Test
    public void FrontEndDeveloper_testInsertNewDog(){
        try {
        	//Load new TestUITester
            TextUITester tester = new TextUITester("1\n1\nGoodDog\nRetriever\n2000\n0\n");
            //Create BackEnd object
    		ExtendedSortedCollectionInterface back = new DogDatabase();	  
    		//Create FrontEnd object
    	    VetManagerFrontEnd front = new VetManagerFrontEnd();
    	    front.run(back);
    	    String out = tester.checkOutput();
    	    //System.out.println(out);
    	    if(!out.contains("Successfully added |GoodDog, Retriever, 2000")){System.out.println("false.");}
    	    
    	    //Junit Asserts
    	    assertEquals(out.contains("Successfully added |GoodDog, Retriever, 2000|"), true);
    	    //assertEquals(back.contains(out), true);
        }catch(Exception e) {
        	System.out.println("Error: Exception thrown in FrontEndDeveloper_testInsertNewDog().");
        	e.printStackTrace();
        }  
    }
    /**
     * This is a JUNIT Test method tests the functionality of the Front-End testSearchDogName() method.
     * ->This test method creates a new TextUITester object to simulate user input corresponding to
     * 1. Accessing the database (1\n)
     * 2. Add a new dog to the database (1\nGoodDog\nRetriever\n2000\n)
     * 3. Add another new dog to the database (1\nGoodDog\nPitbull\n2005\n)
     * 4. Search for dog's with the name "GoodDog" (2\nGoodDog\n)
     * 5. Finally, the user will exit the database. (0\n)
     *
     * The output of the previous commands will be checked for correct output and confirmation that the insertion was successful.
     * @author Peter Bryant (Front End Developer)
     */
    @Test
    public void FrontEndDeveloper_testSearchDogName(){
        try {
          	//Load new TestUITester.
        	TextUITester tester2 = new TextUITester("1\n1\nGoodDog\nRetriever\n2000\n1\nGoodDog\nPitbull\n2005\n2\nGoodDog\n0\n ");
    		ExtendedSortedCollectionInterface back = new DogDatabase();	  
  
    	    VetManagerFrontEnd front = new VetManagerFrontEnd();
    	    front.run(back);
    	    String out = tester2.checkOutput();
    	    //System.out.println(out);  
    	    if(!out.contains("Successfully added |GoodDog, Pitbull, 2005|")){System.out.println("false.");}
    	    if(!out.contains("|GoodDog at index 1: GoodDog 2005 Pitbull|")){System.out.println("here false.");}
    	    if(!out.contains("|GoodDog at index 0: GoodDog 2000 Retriever|")){System.out.println("false.");}

    	    //Junit Asserts
    	    assertEquals(out.contains("Successfully added |GoodDog, Pitbull, 2005|"), true);
        	assertEquals(out.contains("|GoodDog at index 1: GoodDog 2005 Pitbull|"), true);
        	assertEquals(out.contains("|GoodDog at index 0: GoodDog 2000 Retriever|"), true);

        }catch(Exception e) {
        	System.out.println("Error: Exception thrown in FrontEndDeveloper_testSearchDogName().");
        	e.printStackTrace();
        }  
    }
    /**
     * This is a JUNIT Test method tests the functionality of the Front-End testSearchDogType() method.
     * ->This test method creates a new TextUITester object to simulate user input corresponding to
     * 1. Accessing the database (1\n)
     * 2. Add a new dog to the database (1\nGoodDog\nLab\n2000\n)
     * 3. Add another new dog to the database (1\nBadDog\nLab\n2005\n)
     * 4. Search for dog's with the type "Lab" (3\Lab\n)
     * 5. Finally, the user will exit the database. (0\n)
     *
     * The output of the previous commands will be checked for correct output and confirmation that the insertion was successful.
     * @author Peter Bryant (Front End Developer)
     */
    @Test
    public void FrontEndDeveloper_testSearchDogType(){
        try {
          	//Load new TestUITester.
        	TextUITester tester3 = new TextUITester("1\n1\nGoodDog\nLab\n2000\n1\nBadDog\nLab\n2005\n3\nLab\n0\n ");
    		ExtendedSortedCollectionInterface back = new DogDatabase();	  
    	    VetManagerFrontEnd front = new VetManagerFrontEnd();
    	    front.run(back);
    	    String out = tester3.checkOutput();
    	    //System.out.println(out);  
    	    if(!out.contains("Successfully added |BadDog, Lab, 2005|")){System.out.println("false.");}
    	    if(!out.contains("|Lab-1: BadDog 2005 Lab|")){System.out.println("false.");}
    	    if(!out.contains("|Lab-0: GoodDog 2000 Lab|")){System.out.println("false.");}
    	    
    	    //Junit Asserts
    	    assertEquals(out.contains("Successfully added |BadDog, Lab, 2005|"), true);
        	assertEquals(out.contains("|Lab-1: BadDog 2005 Lab|"), true);
        	assertEquals(out.contains("|Lab-0: GoodDog 2000 Lab|"), true);
        	
        }catch(Exception e) {
        	System.out.println("Error: Exception thrown in FrontEndDeveloper_testSearchDogType.");
        	e.printStackTrace();
        }  
    }
    
    /**
     * This is a JUNIT Test method tests the functionality of the Front-End testDisplayTree() method.
     * ->This test method creates a new TextUITester object to simulate user input corresponding to
     * 1. Accessing the database (1\n)
     * 2. Add a new dog to the database (1\nGoodDog\nRetriever\n2005\n)
     * 3. Add another new dog to the database (1\nGoodDog\nLab\n2000\n)
     * 4. Display the current tree (4\n)
     * 5. Finally, the user will exit the database. (0\n)
     *
     * The output of the previous commands will be checked for correct output and confirmation that the insertion was successful.
     * @author Peter Bryant (Front End Developer)
     */
    @Test
    public void FrontEndDeveloper_testDisplayTree(){
        try {
        	TextUITester tester4 = new TextUITester("1\n1\nGoodDog\nRetriever\n2005\n1\nGoodDog\nLab\n2000\n4\n0\n");
            //Create BackEnd object
    		ExtendedSortedCollectionInterface back = new DogDatabase();	  
    		//Create FrontEnd object
    	    VetManagerFrontEnd front = new VetManagerFrontEnd();
    	    front.run(back);
    	    String out = tester4.checkOutput();
        	//System.out.println(out);
        	assertEquals(out.contains("[ Dog Name:GoodDog Year of birth:2000 Type:Lab, Dog Name:GoodDog Year of birth:2005 Type:Retriever ]"), true);
        	
        }catch(Exception e) {
        	fail("Error: Exception thrown in FrontEndDeveloper_testDisplayTree.");
        	e.printStackTrace();
        }  
    }
    
}
