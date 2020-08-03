/**
 * RNode.java
 *
 * Node for a recursive linked list.
 *
 * Note that in the recursive version, most of the work for the list methods
 * is done here.
 *
 * @author TVD/CGG/HK, and Steven Barker
 * Wheaton College, CSCI 235, Spring 2019
 * Project 6
 * Date 4/13/19
 */

public class RNode{

	 /**
     * The payload for this RNode.  This should be treated as immutable.
     */

    private final int datum;
    /**
     * The INode following this one.
     */
    private RNode next;

    /**
     * Constructor.
     * @param datum The payload for this INode.
     * @param next  The node that follows this one.
     */
    public RNode(int datum, RNode next) {
        this.datum = datum;
        this.next = next;
    }

    /**
     * Accessor method.
     */
    public int datum() { return datum; }
	

	 /**
     * Accessor method.
     */
    public RNode next() { return next; }
	

    /**
     * print this node and its successors
     */
    public void print() {
	System.out.print(datum+" ");
	if (next != null) {
	    next.print();
	}
    }

    /**
    *Finds the length of a list
    *@return an int that is the lenght of the list
    */
    public int length() {
	if (next == null) {
	    return 1;
	}
	return 1 + next.length();
    }

	/**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public RNode insertSorted(int item) {
		if(item < datum){
			return new RNode(item, this);

		}else if(next == null){
			next = new RNode (item, null);
			return this;
		}else{
			next = next.insertSorted(item);
			return this;
		}
    }

	/**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public RNode deleteFirstOccurrence(int item) {
		if(item == datum){
			return next;
		}else if(next == null){
			return this;
		}else{
			next = next.deleteFirstOccurrence(item);
			return this;
		}
	}


	
	/**
	 *elementAt
     * @param position The position in the list.
     * @return The value at position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
	public int elementAt(int position) {
		if (position == 0){
			return datum;
		}else return next.elementAt(position - 1);
	}

	/**
	*Contains
	*Tests if a list contains a specific datum
	*If false, tests if it is at the end of the list
	*If false, calls itself again on the next item
	*PRECONDITION: Must not be an empty list and item must be an int
	*@param item, the item to be searched for
	*@return, either true or false
	*/
	public boolean contains(int item){
		if(datum == item){
			return true;
		}else if (next == null){
			return false;
		}else return next.contains(item);
	}
	
	/**
     * @param item The value to insert.
     * @param position The index after which the item will be inserted.
     * PRECONDITION: position is within the list
     */
	public RNode insertAfter(int item, int position){
		if(position == 0){
			next = new RNode(item, next);
			return this;
		}else if(next == null){
			return this;
		}else{
			next = next.insertAfter(item, position-1);
			return this;
		}
	}

	 /**
     * Delete all occurrences of item in this list, if any.
     * @param item is the item to be deleted from the list
     */
    public void deleteAll(int item){
    	if(next.datum() == item && next.next() == null){
    		next = null;
    		return;
    	}else{
    		if (next.datum() == item){
    			next = next.next();
    			next.deleteAll(item);
    		}else if(next != null){
    			next.deleteAll(item);
    		}
    	}
    }

     /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void deleteAt(int position){
    	if(position == 1 && next != null){
    		next = next.next();
    	}else next.deleteAt(position-1);
    	
    }


    /**
     * Add an item to the back of this list.
     * POSTCONDITION: a new node that contains the item will be inserted at back
     * @param item, to be the datum of the newly inserted node
     */
    public void insertAtBack(int item){
    	if(next == null){
    		next = new RNode(item, null);
    	}else next.insertAtBack(item);
    }
    
    /**
    *Finds the smallest datum in a list recursively.
    *@return the smallest datum
    */
    public int findSmallest(){
    	if(next == null){
    		return datum;
    	}else{
    		int small = next.findSmallest();
    		if(small > datum){
   				small = datum;
   			}
   			return small;
    	}
    }

	
}
   
    














