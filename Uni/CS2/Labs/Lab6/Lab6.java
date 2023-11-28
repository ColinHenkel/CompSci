import java.util.*;

public class Lab6 {

	final public static int NEG_INF = Integer.MIN_VALUE;
	final public static int POS_INF = Integer.MAX_VALUE;
	
	public static Random rndObj = new Random();
	
	private ArrayList<node> levels;
	private int size;
	
	// makes an empty list
	public Lab6() {

		// initially, there is just one level with min and max
		levels = new ArrayList<node>();
		levels.add(buildLevel(0));
		size = 1;
	}

	// this builds level id to be an empty level
	public node buildLevel(int id) {
		node first = new node(NEG_INF, id);
		node last = new node(POS_INF, id);
		first.next = last;
		last.prev = first;		
		return first;
	}
	
	// returns a list of nodes at each level that are right before value
	public ArrayList<node> search(int value) {
		
		// store answers here
		ArrayList<node> res = new ArrayList<node>();
		node cur = levels.get(size-1);
		
		// we search from top, so that we can skip terms
		for (int i=size-1; i>=0; i--) {
			
			// go down this level until we're right before an equal or bigger item
			while (cur.next != null && cur.next.data < value) cur = cur.next;
			
			// this is the floor of value on this list, so add it
			res.add(cur);
				
			// go down to the next level
			if (i>0) cur = cur.down;
		}
		
		// so the list will be in the proper order, since I built it backwards
		Collections.reverse(res);
		
		// stores all of the relevant pointers
		return res;
	}
	
	// inserts value into the set, returns true iff the value was inserted (false means the value was already in the set)
	public boolean insert(int value) {
				
		// find all the "previous" nodes
		ArrayList<node> beforeList = search(value);
		
		// this value is already in the set
		if (beforeList.get(0).next.data == value) return false;
		
		// temp pointer I will use
		node curn = null;
		
		int i = 0;
		
		// farthest we'll go up the lists
		while (i <= size) 
    {
			
      //intentionally generate 1 for first level as we need to at least insert it
			int val = i==0 ? 1 : rndObj.nextInt(2);
     // int val = rndObj.nextInt(2);
			if (val == 0) break;
			
			// we've decided to create this node
			node newn = new node(value, i);
			
			// not necessary for bottom level
			if (i > 0) {
				curn.up = newn;
				newn.down = curn;
			}
			
			// special case where we are adding a new level to our list
			// we add the level and then connect it to the rest of the lists
			if (i == size) {
				node nextL = buildLevel(size);
				levels.add(nextL);
				connectLastLevel();
				beforeList.add(nextL);
			}
			
			// joining newn between prev and next
			node tmpLow = beforeList.get(i);
			node tmpNext = tmpLow.next;
			newn.prev = tmpLow;
			newn.next = tmpNext;
			tmpLow.next = newn;
			tmpNext.prev = newn;
			
			// update the object's size
			if (i == size) {
				size++;
				break;
			}
			
			// go up to next level
			i++;
			curn = newn;
		}
		
		return true;
	}
	
	// deletes value from the list
	public boolean delete(int value) {
    ArrayList<node> beforeList = search(value);
    // check if the value is in the skip list
    if (!beforeList.isEmpty() && beforeList.get(0).next.data == value) {
        // delete value from each level
        for (node before : beforeList) {
            node current = before.next;
            if (current != null) {
                before.next = current.next;
                if (current.next != null) {
                    current.next.prev = before;
                }
                // update up and down pointers
                if (current.up != null) {
                    current.up.down = null;
                }
                if (current.down != null) {
                    current.down.up = null;
                }
            }
        }
        return true;
    }
    return false; // value not found, nothing to delete
	}
	
	// returns the number of items on the top level.
	private int topLevelSize() {
		node cur = levels.get(size-1);
		int sz = 0;
		while (cur != null) {
			cur = cur.next;
			sz++;
		}
		return sz;
	}
	
	// connects the last level to the rest of the lists.
	public void connectLastLevel() {
		
		// we can obtain both of these.
		node top = levels.get(levels.size()-1);
		node below = levels.get(levels.size()-2);
		
		// link left sides up and down.
		top.down = below;
		below.up = top;
		
		// end of top list.
		top = top.next;
		
		// go to end of second to top list.
		while (below.data != POS_INF) below = below.next;
		
		// link right sides up and down.
		top.down = below;
		below.up = top;
	}
	
	// for debugging
	public void printAllLevels() {
		System.out.println(levels.size()+" and "+size);
		for (int i=0; i<size; i++) {
			System.out.print("Level "+i+": ");
			printLevel(i);
		}
		System.out.println("---------------------------");
	}
	
	// prints level id for debugging
	public void printLevel(int id) {
		node cur = levels.get(id);
		while (cur != null) {
			System.out.print(cur.data+" ");
			cur = cur.next;
		}
		System.out.println();
	}

	// returns all the items in the skip list in order
	public ArrayList<Integer> getList() {
		node bottom = levels.get(0);
		ArrayList<Integer> res = new ArrayList<Integer>();
		bottom = bottom.next;
		while (bottom.data != POS_INF) {
			res.add(bottom.data);
			bottom = bottom.next;
		}
		return res;
	}
	
  // a large test of random inserts followed by random deletes
	public static void largeTestRandom(int testSize, DataType dataType) {
    if (dataType == DataType.SKIP_LIST) {
        Lab6 myList = new Lab6();

        // insert random values
        long beforeTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            int item = rndObj.nextInt(2000000);
            myList.insert(item);
        }
        long afterTime = System.currentTimeMillis();
        System.out.println("Skip list insertion took " + (afterTime - beforeTime) + " ms.");

        // delete random values
        beforeTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            int item = rndObj.nextInt(2000000);
            boolean deleted = myList.delete(item);
        }
        afterTime = System.currentTimeMillis();
        System.out.println("Skip list deletion took " + (afterTime - beforeTime) + " ms.");
    } else if (dataType == DataType.TREE_SET) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        // insert random values
        long beforeTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            int item = rndObj.nextInt(2000000);
            treeSet.add(item);
        }
        long afterTime = System.currentTimeMillis();
        System.out.println("TreeSet insertion took " + (afterTime - beforeTime) + " ms.");

        // delete random values
        beforeTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            int item = rndObj.nextInt(2000000);
            boolean deleted = treeSet.remove(item);
        }
        afterTime = System.currentTimeMillis();
        System.out.println("TreeSet deletion took " + (afterTime - beforeTime) + " ms.");
    }
	}

	// returns all items of the treeset in order
	public static ArrayList<Integer> get(TreeSet<Integer> ts) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		while (ts.size() > 0) res.add(ts.pollFirst());
		return res;
	}
		
	public static void main(String[] args) {
    for (int i = 50000; i <= 500000; i += 50000) {
        System.out.println("\nTest size: " + i);
        System.out.println("=======\n");
        int listSize = i;
        // Test with Skip List
        long before = System.currentTimeMillis();
        largeTestRandom(listSize, DataType.SKIP_LIST);
        long after = System.currentTimeMillis();
        System.out.println("Skip list actions took " + (after - before) + " ms.");

        // Test with TreeSet
        before = System.currentTimeMillis();
        largeTestRandom(listSize, DataType.TREE_SET);
        after = System.currentTimeMillis();
        System.out.println("TreeSet actions took " + (after - before) + " ms.");
    }
	}	
}

class node {
	public int data;
	public node next;
	public node prev;
	public node up;
	public node down;
	public int level;

	public node(int myval, int mylev) {
		data = myval;
		level = mylev;
		next = null;
		prev = null;
		up = null;
		down = null;
	}
}

enum DataType {
    SKIP_LIST,
    TREE_SET
}
