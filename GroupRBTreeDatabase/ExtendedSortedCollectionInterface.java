// --== CS400 Project TWO File Header ==--
// Name: Roujia Wang
// Email: rwang492@wisc.edu
// Team: red
// Group: AC
// TA: Ilay Raz
// Lecturer: Gary Dahl
interface ExtendedSortedCollectionInterface<T extends Comparable<T>, A, B, C> extends SortedCollectionInterface<T>{
    // A, B, C are data type of fields in the nodes of the collection
    // additional insert method that only requires user to provide pieces of information instead of an object
    public boolean insert(A name, B year, C type) throws NullPointerException, IllegalArgumentException;
    // additional search method using name of the item
    public T[] searchByName(A name);
    // additional search method using type of the item
    public T[] searchByType(C type);

    // for the purpose of updating vaccination date for dogs in the database
    public boolean changeVaccinationDate(String date, A name, B year, C type);
}
