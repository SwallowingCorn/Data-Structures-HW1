public class Demo {

    public static void main(String[] args) {

        PhBArrayList test1 = new PhBArrayList(5);
        test1.insert(0, new Person("Misha", "4"));
        test1.insert(1, new Person("Misha2", "4"));
        test1.insert(2, new Person("Connamacher", "4"));
        test1.insert(3, new Person("Connamacher2", "4"));

        PhBLinkedList test2 = new PhBLinkedList();
        test2.insert(0, new Person("Connamacher3", "4"));
        test2.insert(0, new Person("Connamacher", "4"));
        test2.insert(0, new Person("Misha3", "4"));
        test2.insert(0, new Person("Misha2", "4"));
        PhBIterator iter = test2.iterator();
        /*
        The best positions to insert elements for the array (test 1) is at the end,
        while the best positions to insert elements for the linked list is at the beginning.
        Inserting an element at the end of the array allows for no element-shifting to
        take place, and inserting an element at the beginning of a linked list just
        requires only 1 node to have a pointer adjusted and no traversal needs to take place.
         */

        //before duplicates removed
        System.out.print("Contents of PhB1 ->");
        test1.print();

        System.out.println();

        System.out.print("Contents of PhB2 ->");
        test2.print();

        System.out.println();
        System.out.println();
        System.out.println();

        //post-duplicates removed
        removeDuplicates(test1, test2);

        System.out.print("PhB1 -> ");
        test1.print();

        System.out.println();

        System.out.print("PhB2 -> ");
        test2.print();




    }

    public static void removeDuplicates(PhoneBook PhB1, PhoneBook PhB2) {
        PhBIterator iter1 = PhB1.iterator();
        int PhB1counter = 0;
        //find and record length of first book
        while (iter1.hasNext()) {
            PhB1counter++;
            iter1.next();
        }

        //find and record length of second book
        PhBIterator iter2 = PhB2.iterator();
        int PhB2counter = 0;
        while (iter2.hasNext()) {
            PhB2counter++;
            iter2.next();
        }

        //as if it were 2 different arrays, compare each index against each other
        for (int i = 0; i < PhB1counter; i++) {
            for (int j = 0; j < PhB2counter; j++) {
                //if it's a match, remove them and take one away from recorded lengths
                if (PhB1.lookup(i).getPersonID() == PhB2.lookup(j).getPersonID()) {
                    PhB1.remove(i);
                    PhB2.remove(j);
                    PhB1counter--;
                    PhB2counter--;
                }
            }
        }
    }
    //O(n^2)
}
