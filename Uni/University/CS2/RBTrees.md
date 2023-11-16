Rules of RB:
	1.) every node is red or black
	2.) must be a valid bst
	3.) root is black
	4.) children of red nodes are black
	5.) every external node is NIL and black
	6.) all external black nodes must have same black depth (# of black colored ancestors - 1)

Convert 2-4 into RB:
	- each 2-node (1 value) will be black
	- each 3-node (2 values) will be child red and parent black
	- each 4-node (3 values) will be black parent and two red children

Insertion in RB Tree:
	- initially when an element is inserted normal bst insert is performed and the node is placed as red (root will obviously be black)
		> in some cases this will not cause problems as it does not change black depth and as long as parent is black there is no violation or rule 4
		> thus there are only problems when the parent of the inserted node is red
			= denote inserted node as Z, parent of the inserted node as V, and grandparent of inserted as U
			= denote uncle of the inserted node as W
				- this can cause two cases:
					1.) W is black (could be initially null)
					2.) W is red
	- Insertion (Case 1)
		> relabel the nodes U, V, Z with A, B, C using their inorder order: a < b < c
		> node storing B is colored black while the nodes storing A and C are red
			= this is almost the balancing of AVL trees (RB balancing is implicit rotation)
		> W will end up in one of the four subtrees that have either A or C as a parent
	- Insertion (Case 2)
		> using the same variable conventions as before, recolor U, V, and W
		> specifically make U red, but doing this must follow the rule that the children of red nodes are black
		> therefore both V and W will get colored black
		> however, it may introduce a problem where U may become a double red node
		> continue the recoloring steps up the tree until we get to Case 1
			= if we get to the root, in which case we do not recolor the root as it must stay black
		> once we reach Case 1 perform Case 1 restructuring if necessary for depth

Deletion in RB Tree:
	1) Red leaf node:
		> normal BST deletion is sufficient
		> removing a red node does not change the black depth
	2) Black node, with one red child:
		> after the BST deletion, recolor the child node of the deleted node to black
		> changing this color adds one to the black depths of each node in the subtree of the deleted node, preserving the black depth
		> changing a node to black does not violate any other RB tree rules
	3) Black leaf node:
		> instead, when the NIL child is patched into the deleted node, the child is changed to a fake "double black" color
		> to deal with the following sub cases label as follows:
			1) child of the deleted node is 'r' (double black node)
			2) 'y' is the sibling of r
			3) 'z' is the designated child of y
			4) 'x' is the parent of y
		> the remaining cases with "double black" node are:
			3.1) sibling of the "double black" node (y) is black and has a red child (z)
				- take the nodes, 'x' 'y' 'z' and relabel them 'a' 'b' 'c' in their inorder ordering: a < b < c
				- place 'b' where 'x' used to be, and then have 'a' and 'c' be the left and right children of 'x' respectively
				- color 'a' and 'c' black, and color 'b' whatever color 'x' USED to be
			3.2) sibling of the "double black" node (y) is black and both children are black
				- recolor instead of making any structural changes to the tree
				- in particular color 'r' black then color 'y' red
				- to compensate, 'x' must be changed from red to black
				- if x was black, make 'x' "double black" and recheck case with 'x' as 'r'
					> if you get to root = 'x', just make root black as it will always be black and not affect black depth
			3.3) sibling of the "double black" node (y) is red
				- if 'y' is a right child of 'x', let 'z' be the right child of 'y'
				- else, let 'z' be the left child of 'y'
				- perform a restructuring on 'z', putting 'y' where 'x' used to be
				- perform either case 3.1 or case 3.2 as needed to deal with the "double black" node