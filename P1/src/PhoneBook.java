public interface PhoneBook {

    PhBIterator iterator();

    int size();

    void insert(int i, Person person);

    Person remove(int i);

    Person lookup(int i);

    void print(); //because it's nice to see what's actually in the phonebook
}
