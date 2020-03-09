import java.util.NoSuchElementException;

public class PhBLinkedList implements PhoneBook {

    private PhBNode head;

    public PhBLinkedList() {
        head = null;
    }

    //iterator that can be accessed outside class
    public PhBLLIterator iterator() {
        return new PhBLLIterator();
    }
    //O(1)

    public void print() {
        System.out.print("People in Phonebook:        ");
        //iterate through list, printing every person's name
        PhBLLIterator iter = this.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next().getPersonID() + "      ");
        }
    }
    //O(n)

    @Override
    public int size() {
        PhBLLIterator iter = this.iterator();
        int counter = 0;
        //while there are nodes left...
        while (iter.hasNext()) {
            //add one to counter
            counter++;
            iter.next();
        }
        return counter;
    }
    //O(n)

    @Override
    public void insert(int i, Person person) {
    //node to insert
    PhBNode insertion = new PhBNode(person, null);
    //dummy head to point at nodes, beginning at the first
    PhBNode dummy = this.head;

    //if a invalid index is given, don't allow it
    if (i < 0)
        throw new IndexOutOfBoundsException();

    //if an index is given that's larger than the size of this list, put it at the end
    else if (i > this.size()) {
        //variable to allow node.next to get to last position
        int j = this.size();
        while (j > 1) {
            dummy = dummy.next;
            j--;
        }
        dummy.next = insertion;
    }

    //if the index is the first position, point the insertion to the head of PhB, then set the insertion as the head
    else if (i == 0) {
        insertion.next = this.head;
        this.head = insertion;
    }

    else {
        //get dummy to node at position right before insertion
        while (i > 1) {
            dummy = dummy.next;
            i--;
        }
        //point insertion at next node, then point current node at insertion
        insertion.next = dummy.next;
        dummy.next = insertion;
    }
    }
    //O(n)

    @Override
    public Person remove(int i) {
        //dummy head to point at nodes
        PhBNode dummy = this.head;

        //if a invalid index is given, don't allow it
        if (i < 0 || i > this.size())
            throw new IndexOutOfBoundsException();


        //if they're searching for the start of the list
        else if (i == 0) {
            //record old head
            Person temp = head.person;
            //if it's the only node in the list, remove it by setting head to null
            if (this.size() == 1)
                head = null;
             else {
                 //new head is the next node, return old head
                head = dummy.next;
                return temp;
            }
        }
        //if they're searching for the last node
        else if (i == this.size() - 1) {
            //get to last node
            while (i > 1) {
                dummy = dummy.next;
                i--;
            }
            PhBNode temp = dummy.next;
            dummy.next = null;
            return temp.person;
        }

        else {
            //get dummy to node at position right before insertion
            while (i > 1) {
                dummy = dummy.next;
                i--;
            }
            //set the node's pointer to the node after the one to remove
            dummy.next = dummy.next.next;
            return dummy.next.person;
        }
        return null;
    }
    //O(n)

    @Override
    public Person lookup(int i) {
        //if the index requested doesn't exist in the PhB, return null
        if (i > size() - 1 || i < 0)
            return null;

        //traverse list with a dummy head and count down the index to get to the right position
        PhBNode dummy = this.head;
        while (i > 0) {
            dummy = dummy.next;
            i--;
        }
        //return person contained in node at that position
        return dummy.person;
    }
    //O(n)


    //simple class for the nodes, which hold the person and the pointer
    public static class PhBNode {
        private Person person;
        private PhBNode next;

        public PhBNode(Person person, PhBNode next) {
            this.person = person;
            this.next = next;
        }
    }


    //iterator creation
    public class PhBLLIterator implements PhBIterator {
        private PhBNode nextNode;

        public PhBLLIterator() {
            nextNode = head;
        }

        @Override
        public Person next() {
            if (!hasNext()) throw new NoSuchElementException();

            else {
                //set person to next
                PhBNode p = nextNode;
                //move slider
                nextNode = nextNode.next;
                return p.person;
            }
        }
        //O(1)

        @Override
        public boolean hasNext() {
            //if there's a next node, hasNext() is true
            return nextNode != null;
        }
        //O(1)
    }
}
