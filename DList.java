/**
 * DList.java
 *
 * A doubly-linked list.
 *
 * @author TVD/CGG/HK and Steven Barker
 * Wheaton College, CSCI 235, Spring 2019
 * Project 6
 * 4/16/19
 */

public class DList implements List235 {

    /**
     * The first node in this list.
     */
    private DLNode head;

    /**
     * Constructor.
     * POSTCONDITION: This list is empty.
     */
    public DList() {
	head = null;
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
	// set the link going forward (next)
	DLNode n = new DLNode(item);
	n.spliceAsHead(head);
	head = n;
    }

    /**
     * Delete the front item from this list.
     * PRECONDITION: This list is not empty.
     */
    public void deleteFront() {
	head = head.spliceOut();
    }

    /**
     * Delete and return the front item from this list.
     * @return The item that was deleted.
     * PRECONDITION: This list is not empty.
     */
    public int removeFront() { 
	DLNode oldHead = head;
	head = head.spliceOut();
	return oldHead.datum();
    }

    // Note that most of these can be the same as for singly-linked
    /**
     * How many items are in this list?
     * @return The number of items in this list.
     */
    public int length() {
	DLNode place = head;
	int i = 0;
	while(place != null){
		place = place.next();
		i += 1;
	}
	return i;
    }

    /**
     * Does the list contain an item?
     * @param item The value to look for.
     * @return Whether this list contains the item.
     */
    public boolean contains(int item) {
	DLNode place = head;
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
		DLNode place = head;
		int i = 0;
		while (place != null) {
	    	if (i == position) { 
			return place.datum();
	    	}
			place = place.next();
	    	i++;
		}
	
		return 0;
    }

    /**
     * Insert a new item at after a specified position.
     * @param item The value to insert.
     * @param position The index after which the item will be inserted.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void insertAfter(int item, int position) {
    	DLNode newItem = new DLNode (item);
		int i = 0;
		DLNode place = head;
		if(position >= 0 && position < this.length()){
			while(place != null){
				if(i == position){
					newItem.spliceAfter(place);
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
	DLNode place = head;
	int i = 0;
		if(position == 0){
			head = head.next();
			return;
		}else{
			while (place != null) {
	    		if (i == position) {
	    			place.spliceOut();
					return;
	    		}else{
	    			place = place.next();
	    			i += 1;
				}
			}	
		}
    }

    /**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
    	DLNode place = head;

    	while(place != null){
    		if(place.datum() == item){
    			place.spliceOut();
    			return;
    		}else place = place.next();
    	}
	}
    
    /**
     * Delete all occurrences of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteAll(int item) {
	DLNode place = head;
		while(place != null){
    		if(place.datum() == item){
    			place = place.spliceOut();
    		}else place = place.next();
    	}
    }

    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
    	DLNode newNode = new DLNode(item);
    	DLNode place = head;

    	while(place != null){
    		if(place == head && item < place.datum()){
    			newNode.spliceAsHead(place);
    			head = newNode;
    			return;
    		}else if(item < place.datum()){
    			newNode.spliceAfter(place.previous());
    			return;
    		}else place = place.next();
    }

    /**
     * Find the last node in this (non-empty) list.
     * @return The last node in this list.
     * PRECONDITION: this is not empty
     */
    private DLNode findLast() {
		if(head != null){
        	return head.findLast();
    	}else return new DLNode ();
    }
	
   /**
     * Insert an item at the back of this list.
     * @param item The item to add.
     */
    public void insertAtBack(int item) {
		if (head == null) {
	    	insertAtFront(item);
		} else {
	   	 	DLNode last = this.findLast();
	    	DLNode n = new DLNode(item);
	    	n.spliceAfter(last);
		}
    }

    /**
     * Remove and return the smallest element of the list.
     * First find the smallest, then delete it, then return it.
     * @return The (now removed) smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int removeSmallest() {
		DLNode smallest = findSmallest();
		smallest.spliceOut();
		return smallest.datum();
    }

    /**
     * Return the value of the smallest element of the list.
     * @return The node containing the smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    private DLNode findSmallest() {
	DLNode place = head;
	DLNode currentSmallest = head;
		while(place != null){
			if(place.datum() < currentSmallest.datum()){
				currentSmallest = place;
			}else place = place.next();
		}
		return currentSmallest;
    }

    /**
     * Print the elements of this list to System.out.
     */
    public void printList() {
	System.out.print("[ ");
	DLNode place = head;
		while (place != null) {
	    	System.out.print(place.datum()+" ");
	    	place = place.next();
		}
	System.out.println("]");
    }
    
	/**
     * Print the elements of this list in reverse order.
     */
	public void printListReverse() {
		DLNode place = head;
		if (place != null) {
			while (place.next() != null) {
				place = place.next();
			}
		}
		System.out.print ("reverse [ ");
		while (place != null) {
			System.out.print (place.datum() + " ");
			place = place.previous();
		}
		System.out.println ("]");
	}
	
    
	public static void main(String[] args) {
	// for testing
		DList x = new DList();
		x.insertAtFront(15);		
		x.insertAtFront(12);
		x.insertAtFront(4);
		x.printList();
		x.printListReverse();
	
	}
}
