/**
 * IList.java
 *
 * A first stab at a linked list (iterative version)
 *
 * @author Steven Barker
 * Wheaton College, CSCI 235, Spring 2019
 * Project 6
 * 4/17/19
 */

public class IList implements List235 {

    /**
     * The first INode in this list.
     */
    private INode head;

    /**
     * Constructor.
     * POSTCONDITION: This list is empty.
     */
    public IList() {
	head = null;
    }

    /**
     * Alternative to constructor: create a list for use by sorting methods.
     * To simplify the insertion methods used for sorting, this starts
     * out the list with an extra zero item on the front.
     * After fixing the insertions to work on an empty list,
     * change this method  to start with an empty list.
     */
    public static IList newSortList() {
	IList theList = new IList();
	// get rid of the next line in Task #4 
	return theList;
    }

    /**
     * Is this list empty?
     * @return Whether this list is empty.
     */
    public boolean isEmpty() {
	return head == null;
    }

    /**
     * Insert an item at the front of this list.
     * @param item The item to add.
     */
    public void insertAtFront(int item) {
	head = new INode(item, head);
    }

    /**
     * Delete the front item from this list.
     * PRECONDITION: This list is not empty.
     */
    public void deleteFront() {
	head = head.next();
    }

    /**
     * Delete and return the front item from this list.
     * @return The item that was deleted.
     * PRECONDITION: This list is not empty.
     */
    public int removeFront() {
	int temp = head.datum();
	head = head.next();
	return temp;
    }

    /**
     * Print the elements of this list to System.out.
     */
    public void printList() {
	System.out.print("[ ");
	INode place = head; // position within this list
	while (place != null) {
	    System.out.print(place.datum()+" ");
	    place = place.next();
	}
	System.out.println("]");
    }

    /**
     * How many items are in this list?
     * @return The number of items in this list.
     */
    public int length() {
	INode place = head;
	int i = 0;
	while(place != null){
		place = place.next();
		i += 1;
	}
	return i;

    }
 
    /**
     * What is the sum of items in the list?
     * @return The sum of all items in this list.
     */
    public int sum() {
	INode place = head;
	int sum = 0;
	while(place != null){
		sum = sum + place.datum();
		place = place.next();
	}
		return sum;
    }

    /**
     * Does the list contain an item?
     * @param item The value to look for.
     * @return Whether this list contains the item.
     */
    public boolean contains(int item) {
	INode place = head;
	while(place != null){
		if(place.datum() == item){
			return true;
		}else place = place.next(); 
	}
	return false;

    }

    /**
     * Get the value of the item at a specified position.
     * @param position The position in the list.
     * @return The value at position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public int elementAt(int position) {
	/* This is not the most compact way to write this,
	 * but this form can be adapted to other uses.
	 */
	INode place = head;  // the node we are looking at
	int i = 0;           // the position of place
	while (place != null) {
	    if (i == position) { // is place the node we want?
		return place.datum();
	    }
	    // do something before going to the next node
	    place = place.next();
	    i++;
	}
	
	return 0;//this will never get reached
    }

    /**
     * Insert a new item at after a specified position.
     * @param item The value to insert.
     * @param position The index after which the item will be inserted.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void insertAfter(int item, int position) {
		INode newItem = new INode (item, null);
		int i = 0;
		INode place = head;
		if(position >= 0 && position < this.length()){
			while(place != null){
				if(i == position){
					newItem.setNext(place.next());
					place.setNext(newItem);
					return;
				}else{
					place = place.next();
					i++;
				}
			}
		}
    }

    /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void deleteAt(int position) {
	INode place = head;    // where we are in the list
	int i = 0;             // count corresponding to place
	INode previous = null; // the node before place
                         // There is nothing before head, so null?
	if(position == 0){
			head = head.next();
			return;
		}
		while (place != null) {
	    if (i == position) {
	    	previous.setNext(place.next());
		// place is the node to remove from the list;
		// so splice out place -- for which we need to know
	        // the node before it
		return;
	    }
	    // move place forward
	    previous = place;
	    place = place.next();
	    i += 1;
	}
	// reached only if position >= length of list
	//throw new RuntimeException("precondition violated");
    }

    /**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
	   INode place = head.next();
       INode previous = head;


        if(previous.datum() == item){
             head = place;
        }
        while (place != null) {
            if (item == place.datum()){
                previous.setNext(place.next());
                return;

            }
            previous = place;
            place = place.next();
        }
        if (place == null){
            previous.setNext(null);
        }
        
       }
    

    /**
     * Delete all occurrences of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteAll(int item) {
	INode place = head;
	int position = 0;

		while(place != null){
			if(place.datum() == item){
				deleteAt(position);
				position -= 1;
			}		
			place = place.next();
			position++;
		}	
	}
    

    /**
     * Make a new list that is the reverse of this one.
     * @return the reversed new list
     */
    public IList makeReverse() {
	
	IList newList = new IList();
	int i = 0;
	while(i < length()){
		newList.insertAtFront(elementAt(i));
		i++;
	}
	return newList;

    }

    /**
     * Reverse this list (without making a new list).
     * (Ideally, do it without making any new nodes.)
     */
    public void reverse() {
    		
    	INode previous = head;
    	INode place = head.next();
    	INode next = place.next();

    	while(next != null){
    		place.setNext(previous);
    		previous = place;
    		place = next;
    		next = next.next();
    	}
    	place.setNext(previous);
    	head.setNext(null);
    	head = place;
    	
    }


    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
	INode temp = new INode (item, null);
    INode previous = head;

    if(previous == null || temp.datum() < head.datum()){
        temp.setNext(head);
        head = temp;
        return;
    }
    INode next = previous.next();
    while(previous != null){
        if(next == null){
            temp.setNext(null);
            previous.setNext(temp);
            return;
        }
        else if(temp.datum() >= previous.datum() && temp.datum() <= next.datum()){
        previous.setNext(temp);
        temp.setNext(next);
        return;
    }

    previous = next;
        next = next.next();
        
    }
        }


    /**
     * Insert an item at the back of the list.
     * @param item The item to to add
     */
    public void insertAtBack(int item) {
	   INode newLast = new INode(item, null);
       INode place = head;
       INode previous = null;

       if(place == null){
       	head = new INode(item, head);
       	return;
       }
       while(place != null){
       previous = place;
       place = place.next();
       if(place.next() == null){
        place.setNext(newLast);
        newLast = null;
        return;
       }
}

    
    }

    /**
     * Remove and return the smallest element of the list.
     * First find the smallest, then delete it, then return it.
     * @return The (now removed) smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int removeSmallest() {
        int smallest = findSmallest();
        deleteFirstOccurrence(smallest);
        return smallest;
    }

    /**
     * Return the value of the smallest element of the list.
     * @return The smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    private int findSmallest() {
        INode place = head.next();
        int smallest = head.datum();
        while (place != null){
            if (place.datum() <= smallest){
                smallest = place.datum();
            }
            place = place.next();
        }
        return smallest;    
    }

    public static void main(String[] args) {
	// whatever we need to test the class
	
    IList x = new IList();
    System.out.print("Inital list: ");
    x.printList();
    x.insertAtBack(1);
    x.printList();
    x.insertAtFront(3);
    x.insertAtFront(4);
    System.out.print("After adding 1,3,4: ");
    x.printList();
    x.insertAtFront(6);
    x.insertAtFront(7);
    System.out.print("After adding 6,7: ");
    x.printList();
    x.reverse();
    System.out.print("After reversing: ");
    x.printList();
    

	/**System.out.println("isEmpty()? "+x.isEmpty());
	System.out.print("Initial: ");
	x.printList();
	System.out.println("insertAtFront(4)");
	x.insertAtFront(4);  // [ 4 ]
	x.printList();
	System.out.println("insertAtFront(12)");
	x.insertAtFront(12);  // [ 12 4  ]
	System.out.println("insertAtFront(15)");
	x.insertAtFront(15);  // [ 15 12 4  ]
	x.printList();
	System.out.println("deleteAt(0)");
	x.deleteAt(0);  // [ 15 12 ]
	x.printList();
	System.out.println("isEmpty()? "+x.isEmpty());
	System.out.println("length() "+x.length());
	System.out.println("deleteFront()");
	x.deleteFront();
	System.out.println("deleteFront()");
	x.deleteFront();
	x.printList();
	System.out.println("insertAtFront(15)");
	x.insertAtFront(15);
	System.out.println("insertAtFront(7)");
	x.insertAtFront(7);
	System.out.println("removeFront() "+x.removeFront());
	x.printList();
    //System.out.println("newSortList()");
    //x.newSortList();
    //x.printList();
    System.out.println("insertSorted(5)");
    x.insertSorted(5);
    x.printList();
    System.out.println("insertSorted(8)");
    x.insertSorted(8);
    x.printList();
    System.out.println("insertSorted(0)");
    x.insertSorted(0);
    x.printList();
    System.out.println("findSmallest()");
    x.findSmallest();
    System.out.println("removeSmallest()");
    x.removeSmallest();
    x.printList();
    System.out.println("insertAtBack(2)");
    x.insertAtBack(2);
    x.printList();
    */
       }

   }


