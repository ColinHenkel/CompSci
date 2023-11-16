Background
-----------------
	- BST search time is average O(logn), however it can still e O(n) in the worst case
	- balancing the BST can help achieve O(logn) much more consistently because search, insertion, and deletion times are proportional to height
	- we can take an unbalanced tree and rebalance it (take elements in random order and insert them into a new tree)
	- we can also add new restrictions so that they stay balanced (incremental re-balancing[AVL, red-black, splay, and the focus of this lecture B-trees])
	- BTrees ensure all leaves are guranteed to have the same depth
	- b-trees are often used for indexing data in a database
Disks:
	- data in a database is stored in a disk
	- a disk is made up of tracks (circular lines), sectors (lines dividing circles into sections), and blocks (intersection boxes)
	- each block has a block address (Track number, Sector number)
	- each block stores a specific amount of bytes
		> For example, 512 bytes can be stored in each of a disk's blocks
	- operating systems read data from one block at a time as it is efficient
	- Representation of 512 byte block:
		1 byte | 1 byte | 1 byte | ... | 1 byte
		  0			1		2 			  511
	- in the above block each byte has its address (offset address)
	- the address starts from 0 and ends at 511 (in our example of 512 bytes)
	- in the above picture 2 is the offset of the 3rd byte in the block
		> therefore, to access any particular byte, we need to know the track number, sector number, and offset address
		> in order to read a particular block, the disk needs to rotate and the head needs to go to the particular sector containing the block
Main Memory and Data Structure:
	- any data that needs to be processed is brought to the main memory (RAM)
	- we use different data structures to store data in RAM for efficient processing
	- data stored in the disk is managed by the database management system (DBMS), in order to efficiently access disk information
	- E.g.:
		> if a record is 128 bytes, how many records can you store in a 512 byte disk block? 512/128 = 4 records per block
		> every 4 records takes a block
		> if we have 100 records in our database, how many blocks will be needed? 100/4 = 25 blocks
	- main concept of B and B+ trees comes from multi-level indexing (index of an index of data)
		> index stores a key for a record and the address of that record within the disk in order to make storage and searching more efficient
M-way (multiway) search tree:
	- in a BST, there can only be one key at each node, and each node can only have 2 children, thus the binary part of the name
	- in one type of M-way tree, each node contains 2 keys and a maximum of 3 children, called a 3-way search tree
		> M-way means M-1 keys for each node and maximum of M children
	- if a node contains k items(let these be l1, l2, ... lk in numeric order) then that node will contain k+1 subtrees
		> let these subtrees be S1, S2, .... Sk+1
	- in order to create a search tree with a reasonable order, there must be constraints on the values stored in each subtree
		> in particular:
			= all values in S1 are less than l1
			= all values in S2 are less than l2, but greater than l1
			= all values in S3 are less than l3, but greater than l2
			= all values in Sk are less than lk but greater than lk-1
			= all values in Sk+1 are greater than lk
		> for future reference, a subtree to the left of value lm is Sm and Sm+1 is the subtree to the right of lm
	- similar to binary searching, if search key s<k1, search the leftmost child
	- if s>kd-1, search the rightmost child
	- that is in a binary tree, what about if d>2
	- find two keys ki-1 and ki between which s falls, and search the child vi
	- for multi level indexing, you need to maintain the record pointer for each key in the node structure
	- in the following example of a 4-way search tree, it maintains a child pointer and a record pointer
		> {cp1}[k1]{rp1}{cp2}[k2]{rp2}{cp3}[k3]{rp3}{cp4} <
	- the only problem with m-way search trees is that there is not any well defined rules for insertion and utilizing upper level spaces before going to the next level
		> this can result in insanely tall trees, similar to a BST
-----------------
B and B+ Trees
-----------------
B-Trees (2-4 Trees):
	- B-tree is a type of m-way search tree with specific rules
	- for simplicity, notes will use 2-4 trees, it is a B-tree with m = 4
	- a 2-4 tree is a specific type of multitree with these restrictions:
		1.) Each node has between 1 to 3 values
		2.) Each node has in between 2 and 4 children
		3.) All leaves have the same depth
	- E.g.:
					10,20,30
			/		/	 	\	  	\
		  3,7	  15  	22,28  	40,50,60
		/  | | 	  /\   / |  \   | |  |  \
	   1   5 8  12 17 21 26 29 35 45 55 65,70
	- Insertion in 2-4 Tree:
		> rules must be maintained, creation process is bottom up
		> add elements only to existing nodes (if possible)
		> possible insertion problem:
			= a node can become full (contains 3 values already)
				- cannot just create a node and insert it into the next level as not all external nodes will have the same depth
				- instead, you must push a value up to the parent of the inserted node
			= if a node could become too big, this is known as overflow
			= a split operation must be performed in case of overflow as follows:
				- replace node V with two node V_x and V_y
				- V_x gets the first two keys
				- V_y gets the last key
				- send other key up the tree (parent)
				- if V is the root, create new root with third key
				- otherwise just add third key to parent
	- Implementation Ideas
		1.) Each node is represented by 2 arrays:
			a) array of values
			b) array of references to Nodes
		2.) Each node stores
			a) linked list of values
			b) linked list of references to Nodes
		Advantage of first design:
			= easier to access each item in a node and modify values in a node
		Advantage of second design:
			= never have extra values or references stored in a node
	- Delete Operation:
		> find element to be deleted (simply multi-way search)
		> Case 0: Element is 3 node or 4 node leaf (simple case)
			= 3 node means- a node with 2 elements and 3 children
			= 4 node means- a node with 3 elements and 4 children
			= Solution to case 0 is just simply remove the value (no structural change)
		> Case 1: Element is not a leaf node
			= it is like BST deletion where you bring the largest number from the left subtree or smallest number from the right subtree
			= taking from the right side:
				- remember position of the element and continue until we reach a leaf containing element's successor
				- swap the element with successor
				- if the leaf is not a 2-node (it means you have more than 2 values), delete the element
					> 2-node means a node with only 1 element
		> Case 2: Element is in a 2-node leaf (only element in the node)
			= in this case if you remove the item, there will not be anything remaining
			= this case has 3 sub cases
				- Subcase 1 (2.1): the parent node and sibling nodes are not 2-node (they have more than one items in them)
					> pull an item from the parent and replace it with the element being deleted
					> now balance it by moving an item from the sibling
				- Subcase 2 (2.2): the sibling nodes are 2-node (only one item in them)
					> pull an item from the parent and replace it with the element being deleted
					> fuse the siblings into one node (merge)
				- Subcase 3 (2.3): the parent node is a 2-node (it only has one item)
					> pull an item from the parent and replace it with the element that we are deleting
					> fuse siblings into one node (merge)
					> repeat
	- Time complexity
		> the height of a 2-4 tree is O(log n)
		> split, transfer, and fusion each take O(1)
		> search, insertion, and deletion each take O(log n)

B+ Tree:
	- B+ tree is an extension of B tree
	- in indexing, a B tree node contains record pointer, in addition to child pointer
		> this is applicable for any node, regardless whether the node is a leaf node or not
	- in a B+ tree, all keys are also available in the leaf level and the record pointer is only available in the leaf level
		> in this case, there may be some duplicate entries in the sense that an item in the upper level is also present in the leaf level
		> additionally, the leaf level nodes are connected like a linked list

Differences between B and B+ Tree:
		   			B Tree  													B+ Tree
	1.) all internal leaf nodes have data pointers				only leaf nodes have data pointers
	2.) no duplicate of keys maintained in the tree 			duplicates of keys are maintained and all nodes are present at the leaf
	3.) insertion takes more time and is not predictable        insertion is easier and the results are always the same
	4.) deletion of internal node is very complex 			    deletion of any node is easy because all nodes are found at the leaf
	5.) leaf nodes are not stored as linked list 			    leaf nodes are stored as a structural linked list
	6.) no redundant search keys are present 					redundant search keys may be present