// --== CS400 Project TWO File Header ==--
// Name: Roujia Wang
// Email: rwang492@wisc.edu
// Team: red
// Group: AC
// TA: Ilay Raz
// Lecturer: Gary Dahl
// Notes to Grader: the BackEnd program inherits insert, contains, size, isEmpty methods from RedBlackTree.java

public class DogDatabase extends RedBlackTree<DogNode> implements ExtendedSortedCollectionInterface<DogNode,String, Integer, String>{
    // private DogNode root; // the root of the black-red tree as a collection of dogs
    // private int size; // the number of items in the collection


    //@Override
    public boolean insert(String name, Integer birthYear, String type) throws NullPointerException, IllegalArgumentException{
        // creates an object using pieces of information to insert into database
        return insert(new DogNode(name, birthYear, type));
    }

    //@Override
    public DogNode[] searchByName(String name){
        final int indexOfName = 0; // used for the private helper method to understand what type of search method calls it
        if(name == null){ // return null and print out message to indicate invalid (null) argument
            System.out.println("search by name: invalid name");
            return null;
        } else {
            return searchHelper(indexOfName, name); // return a list of dog objects found
        }
    }

    //@Override
    public DogNode[] searchByType(String type){
        final int indexOfType = 2; // used for the private helper method to understand what type of search method calls it
        if(type == null){ // return null and print out message to indicate invalid (null) argument
            System.out.println("search by type: invalid type");
            return null;
        } else {
            return searchHelper(indexOfType, type); // return a list of dog objects found
        }
    }

    // helper method to search and return a list of dog objects found
    private DogNode[] searchHelper(int indexOfKeyword, String keyword){
        final int indexOfName = 0; // helps determine which type of search method calls it
        final int indexOfType = 2; // helps determine which type of search method calls it

        int count = 0; // counts the number of dogs found
        DogNode[] temp = new DogNode[size]; // for temporary storage of dogs found; the maximum number of dogs found is the size of database
        for(DogNode dog: this){
            if(indexOfKeyword == indexOfName && dog.getName().equals(keyword)){
                temp[count] = dog; // add the dog found to the list
                count ++;
            } else if(indexOfKeyword == indexOfType && dog.getType().equals(keyword)){
                temp[count] = dog;
                count ++;
            }
        }

        DogNode[] dogs = new DogNode[count]; // create the list to be returned with length the same as the number of dogs found
        System.arraycopy(temp, 0, dogs, 0, count); // copy the non-null items from temp to return value
        return dogs;
        // return dogs.toArray(new DogNode[0]); // convert LinkedList to array // since LinkedList is no longer needed, this line is commented out
    }

    //@Override
    public boolean changeVaccinationDate(String date, String name, Integer year, String type){
        if(date == null || name == null || year == null || type == null){ // return false and print out message to indicate invalid (containing-null) arguments
            System.out.println("change vaccination date: information incomplete");
            return false;
        }
        for(DogNode dog: this){ // find the first dog that has the exact same information and change its vaccination date
            if(name.equals(dog.getName()) && year.equals(dog.getBirthYear()) && type.equals(dog.getType())){
                dog.setVaccinationDate(date);
                return true;
            }
        }
        return false; // no dog found, return false
    }

    /*
    // this is an incomplete alternate size method. For simplicity, this program turns to use the size method from its parent class.
    @Override
    public int size() {
        return sizeHelper(root);
    }

    // this is an incomplete alternate size private helper method. It is no longer used, so it is commented out.
    private int sizeHelper(Node<DogNode> node){
        if(node == null){
            return 0;
        } else {
            return 1 + sizeHelper(node.leftChild) + sizeHelper(node.rightChild);
        }
    }
     */


    /*
    // this is an incomplete alternate iterator method. For simplicity, this program turns to use the iterator method from its parent class.
    @Override
    public Iterator<DogNode> iterator() {
        return iterator<DogNode>();
     */
        /*
        return new Iterator<DogNode>() {
            LinkedList<DogNode> dogList = listHelper(root);

            @Override
            public boolean hasNext() {
                return dogList.isEmpty();
            }

            @Override
            public DogNode next() {
                return dogList.remove(0);
            }

            private LinkedList<DogNode> listHelper(Node<DogNode> node){
                LinkedList<DogNode> list = new LinkedList<>();
                if(node == null){
                    return list;
                }

                list.addAll(listHelper(node.leftChild));
                list.add(node.data);
                list.addAll(listHelper(node.rightChild));

                return list;
            }
        };
    }
    */
}

/*
// since all methods required in proposal have been implemented, placeholders are no longer needed and are commented out.
class DogDatabasePlaceholders implements ExtendedSortedCollectionInterface<DogNode, String, Integer, String>{ // a temporary replacement for actual methods
    private DogNode root; // the root of the black-red tree as a collection of dogs
    private int size; // the size of list

    @Override
    public boolean insert(DogNode dog) throws NullPointerException, IllegalArgumentException{ // add a dog to the collection
        System.out.println("This method has not been fully written yet");
        return true; // if insertion is successful, return true
    }

    @Override
    public boolean insert(String name, Integer birthYear, String type) throws NullPointerException, IllegalArgumentException{ // add a dog to the collection
        return insert(new DogNode(name, birthYear, type));
    }

    @Override
    public boolean contains(DogNode dog){
        if(dog != null &&dog.getName().equals("Ben") && dog.getBirthYear() == 2012 && dog.getType().equals("Corgi")){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<DogNode> iterator() {
        return null;
    }

    @Override
    public DogNode[] searchByName(String name){ // search for dog(s) with a certain name
        System.out.println("This method has not been fully written yet");

        if("Ben".equals(name)){ // in case dog(s) is found
            DogNode[] list = new DogNode[1];
            list[0] = new DogNode("Ben", 2012, "Corgi");
            return list;
        }

        return null; // in case no dog is found
    }

    @Override
    public DogNode[] searchByType(String type){ // search for dog(s) of a certain type
        System.out.println("This method has not been fully written yet");

        if("Corgi".equals(type)){ // in case dog(s) is found
            DogNode[] list = new DogNode[1];
            list[0] = new DogNode("Ben", 2012, "Corgi");
            return list;
        }

        return null; // in case no dog is found
    }

    public boolean changeVaccinated(String name, Integer birthYear, String type){ // locate a unique dog to change its vaccination status
        DogNode[] list = searchByName(name);
        if(list != null){
            for(int i = 0; i < list.length; i ++){
                if(list[i] != null && list[i].getBirthYear() == birthYear && list[i].getType().equals(type)){
                    list[i].setVaccinated(!list[i].isVaccinated()); // change the status of isVaccinated
                }
            }
        }
        return true;
    }

    @Override
    public boolean changeVaccinationDate(String vaccinationDate, String name, Integer birthYear, String type){ // locate a unique dog to change its vaccination info
        DogNode[] list = searchByName(name);
        if(list != null){
            for(int i = 0; i < list.length; i ++){
                if(list[i] != null && list[i].getBirthYear() == birthYear && list[i].getType().equals(type)){
                    list[i].setVaccinationDate(vaccinationDate); // change the status of vaccination date
                }
            }
        }
        return true;
    }

    public boolean clear(){ // returns true if tree becomes empty
        root = null;
        size = 0;
        return true;
    }


}
 */
