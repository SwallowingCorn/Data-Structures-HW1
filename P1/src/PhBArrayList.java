import java.util.NoSuchElementException;

public class PhBArrayList implements PhoneBook {

    private Person[] PArray;
    private int tempCapacity;

    public PhBArrayList(int tempCapacity) {
        //don't allow a phonebook with an invalid capacity be created
        if (tempCapacity <= 0)
            throw new IndexOutOfBoundsException();
        PArray = new Person[tempCapacity];
        this.tempCapacity = tempCapacity;
    }

    //iterator that can be accessed outside of class
    public PhBArrayIterator iterator() {
        return new PhBArrayIterator();
    }
    //O(1)


    //checks if the size needs to be increased - called each time insert() is used
    public void incSize() {
        //check if the number of elements vs number of possible elements difference has gotten small. If so, double it with a new array
        if (tempCapacity - size() < 2) {
            tempCapacity = tempCapacity * 2;
            Person[] newAr = PArray.clone();
            PArray = new Person[tempCapacity];
            //copy elements
            for (int i = 0; i < tempCapacity/2; i++) {
                PArray[i] = newAr[i];
            }
        }
    }
    //O(n)

    //checks if the size can be safely decreased - called each time remove() is used
    public void decSize() {
        //check if less than half of the possible spaces have been filled
        if (size() / tempCapacity < .5) {
            //shrink capacity
            tempCapacity = (int)(tempCapacity * .5) + 2;
        }
    }
    //O(n)


    public void print() {
        System.out.print("People in Phonebook:        ");
        //iterate through list, printing every person's name
        PhBArrayIterator iter = this.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next().getPersonID() + "      ");
        }
    }
    //O(n)

    @Override
    public int size() {
        int counter = 0;
        //traverse array forwards from the first index
        for (int i = 0; i < tempCapacity; i++) {
            //count each space that isn't null
            if (PArray[i] != null)
                counter++;
        }
        return counter;
    }
    //O(n)

    @Override
    public void insert(int i, Person person) {
        //possibly increase size if size needs to be increased. If it doesn't, this function will do nothing.
        incSize();

        //if an invalid index is inputted, don't allow it
        if (i < 0)
            throw new IndexOutOfBoundsException();

        //if there are no non-null elements, set this one at the beginning
        if (this.size() == 0) {
            PArray[0] = person;
        }

        //if a person is trying to be inserted after the last non-null element, put it at the end
        else if (i > this.size()) {
            PArray[this.size()] = person;
        }

        else {
            //traverse array backwards starting at last non-null index
            //the current index becomes the previous index
            for (int j = this.size() + 1; j > i; j--) {
                PArray[j] = PArray[j - 1];
            }
            //now put person where user asked them to be inputted
            PArray[i] = person;
        }
    }
    //O(n)

    @Override
    public Person remove(int i) {
        //possibly increase size if size needs to be increased. If it doesn't, this function will do nothing.
        decSize();

        //if invalid index, don't allow it
        if (i < 0)
            throw new IndexOutOfBoundsException();

        //if an index is requested after all of the non-null indexes, save the computational power and return null rather than check the elements
        else if (i >= this.size()) {
            return null;
        }

        else {
            //Person to remove
            Person temp = PArray[i];
            //traverse array forwards, starting at the index of the person to remove
            //the current index becomes the next index
            for (int j = i; j <= this.size(); j++) {
                PArray[j] = PArray[j+1];
            }
            return temp;
        }
    }
    //O(n)

    @Override
    public Person lookup(int i) {
        return PArray[i];
    }
    //O(1)


    //iterator creation
    private class PhBArrayIterator implements PhBIterator {

        int curIndex;

        public PhBArrayIterator() {
            curIndex = -1; //starting before the array
        }

        @Override
        public Person next() {

            if (!hasNext())
                //if there's no next, don't go next
                throw new NoSuchElementException();
            else {
                curIndex++;
            }
            return PArray[curIndex];
        }
        //O(n) (because of hasNext())

        @Override
        public boolean hasNext() {
            return curIndex < size() - 1; //if the current index is at the limit of or beyond the non-null indexes, there is no next
        }
        //O(n) (because of size method)
    }
}
