Sets:
	• In mathematics, sets are unordered collections without duplicates. Objects that
	are contained inside sets are called elements of that set.
	• When writing out sets mathematically we can use the following notation (called
	the roster method):
	• 𝑎𝑝𝑝𝑙𝑒, 𝑜𝑟𝑎𝑛𝑔𝑒, 𝑝𝑒𝑎𝑐ℎ𝑒𝑠, 𝑔𝑟𝑎𝑝𝑒𝑠 | 1, 2, 3, 4, 5
	• Notice that the two following sets are equal under our definition:
	• 1, 3, 2 = {2, 1, 3}
	• Two sets 𝐴 and 𝐵 are equal if the elements in 𝐴 are all contained in set 𝐵 and all
	elements of set 𝐵 are contained in set 𝐴. Also notice that each element may be
	contained only once in the set. We cannot have the following set:
	• {𝑜𝑟𝑎𝑛𝑔𝑒, 𝑜𝑟𝑎𝑛𝑔𝑒}

Set Theory:
	• There are some other useful definitions to know about sets.
	• A set 𝐴 is called a subset of set 𝐵 if all elements in set 𝐴 are also in set
	𝐵. We use the following notation to denote subset:
	• 𝐴 ⊆ 𝐵
	• An alternative definition of set equality is 𝐴 = 𝐵 if and only if 𝐴 ⊆ 𝐵
	and 𝐵 ⊆ 𝐴.
	• Another piece of notation from set theory is the element of symbol. We use
	this to denote when one element is contained in a set. So the following is a true
	statement:
	• 𝑎𝑝𝑝𝑙𝑒 ∈ {𝑎𝑝𝑝𝑙𝑒, 𝑝𝑒𝑎𝑐ℎ, 𝑜𝑟𝑎𝑛𝑔𝑒}
	• Set Union
	• Given two sets 𝐴 and 𝐵, the union of those sets is defined as the set containing all elements in either 𝐴 or 𝐵.
	Union is denoted 𝐴 ∪ 𝐵.
	• 𝑎𝑝𝑝𝑙𝑒, 𝑜𝑟𝑎𝑛𝑔𝑒, 𝑝𝑒𝑎𝑐ℎ ∪ 𝑎𝑝𝑝𝑙𝑒, 𝑡𝑜𝑚𝑎𝑡𝑜, 𝑐𝑎𝑟𝑟𝑜𝑡 = {𝑎𝑝𝑝𝑙𝑒, 𝑜𝑟𝑎𝑛𝑔𝑒, 𝑝𝑒𝑎𝑐ℎ, 𝑡𝑜𝑚𝑎𝑡𝑜, 𝑐𝑎𝑟𝑟𝑜𝑡}
	• Set Intersection
	• Given two sets 𝐴 and 𝐵, the intersection of those sets is defined as the set containing all elements in both 𝐴
	and 𝐵. Intersection is denoted 𝐴 ∩ 𝐵.
	• 𝑎𝑝𝑝𝑙𝑒, 𝑜𝑟𝑎𝑛𝑔𝑒, 𝑝𝑒𝑎𝑐ℎ ∩ 𝑎𝑝𝑝𝑙𝑒, 𝑡𝑜𝑚𝑎𝑡𝑜, 𝑐𝑎𝑟𝑟𝑜𝑡 = {𝑎𝑝𝑝𝑙𝑒}
	• Set Difference:
		- Given two sets 𝐴 and 𝐵, the set difference of those sets is defined as the set containing all elements in 𝐴 but
		not in 𝐵. Set difference is denoted 𝐴 − 𝐵.
		- 𝑎𝑝𝑝𝑙𝑒, 𝑜𝑟𝑎𝑛𝑔𝑒, 𝑝𝑒𝑎𝑐ℎ − 𝑎𝑝𝑝𝑙𝑒, 𝑡𝑜𝑚𝑎𝑡𝑜, 𝑐𝑎𝑟𝑟𝑜𝑡 = {𝑜𝑟𝑎𝑛𝑔𝑒, 𝑝𝑒𝑎𝑐ℎ}

Disjoint Sets:
	• Two or more sets with no common elements are called disjoint sets. Eg, S1 = {1,2,3}, S2 = {4,5}
	• As S1 ∩ S2 = Ø Empty set (S1 and S2 have nothing in common), we can call them a disjoint set
Disjoint Sets Usage:
	• It can help keep track of the set an element belongs to
	• It is easier to check whether two given elements belong to the same subset or not (Find Operation)
	• E.g., S1 = {1,2,3,4} S2 = {5,6,7}
	• find(1) == find(2) will return true as both 1 and 2 belong to the same set
	• They can help merge 2 sets into 1 (Union Operation)
	• E.g., S1 = {1,2,3,4} S2 = {5,6,7}
	• Union will be {1,2,3,4,5,6,7}
	• In a disjoint set, Union(2,6) will give you the same set as something like Union(1,5)
	• Union(2,6) will find the set where 2 belongs to and finds the set where 6 belongs to and performs union between the sets, similarly Union(1,5) will do the same with 1 and 5
Representative:
	• Each set is represented by an element in the set, EG:
		- {1},{2},{3},{4},{5}, in the list of sets, each number is the representative of its own set
Disjoint Set Operations:
	• Given a disjoint set, we can edit them using the union operation
	• EG:
		- union(1,3) would make the previous structure look like {1,3},{2},{4},{5}
		- Here we designate either 1 or 3 as the marker, choosing 1
		- If the following operations are carried out: union(1,4) and union(2,5) (assuming 2 is marked)
		- The structure will be updated to {1,3,4}, {2,5}
		- Now findset operation can also be performed, findset(3) should return 1, as 1 is the marked element in the set that contains 3
Disjoint Set Implementation:
	• A set within disjoint sets can be represented in several ways
	• Consider {2,4,5,8} with 5 as marked element
	• A few ways that could be stored:
		5			5			5
	  / | \		    | \			|
	 2	4  8		2  8		8
	 				|		   / \
	 				4		  4   2
Representing Disjoint Sets in Arrays:
	• A disjoint set can be stored in an array
	• EG, {2,4,5,8},{1},{3,6,7} could be stored as follows:
		- index: 1   2   3   4   5   6   7   8
		  array: 1   5   7   5   5   7   7   2
	• The 5 stored in array location 2 signifies that 5 is 2's parent
	• 5 at location 5 represents 5 is the parent of itself, thus 5 is the representative of the set
	• Let's say we would like to find(8) (the representative of the set where 8 belongs to)
	• We go to index it, find that 2 is the parent, then we go to index 2 and found 5 is the parent, then we go to index 5 and found 5 is the parent, so 5 is the representative of the set where 8 belongs to