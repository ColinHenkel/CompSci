Topological Sort Preface
	- Goal of topological sort:
		> when given a list of items with dependencies
		> produce ordering of the items that satisfies the given constraints
	- In order for problem to be solvable, there can't be a cyclic set of constraints
		> we can't have 5 before 3, we can't have 3 before 7, and we cant have 7 before 5

Directed Acyclic Graph (DAG)
	- in CS and math, a DAG refers to a directed graph which has no directed cycles
	- e.g. if you start from a vertex you will not be able to come back to that vertex following any path
	- in order to do topological sort, the graph MUST be DAG
	- as all rooted trees are DAG, there is always a topological order of a rooted tree
		> there can be multiple topological sorts for the same graph

Topological Sort
	- dependencies (aka constraints) can be modeled using a DAG
	- given a set of items and constraints, a corresponding DAG can be created as follows:
		> each item corresponds to a vertex in the graph
		> for each constraint where item A must finish before item B, place a directed edge A->B
	- E.g.:
		> A = wake up, B = take shower, C = eat breakfast, D = leave home
		> A->B, A->C, B->D, C->D
		> above DAG ensures each item corresponds to a vertex, each constraint has directed edge, and there is no cycle
		> a topological sort would give A, B, C, D
	- we can use DFS in Topological Sort Algorithm to determine and sort in topological order
		> the idea is when you do DFS on DAG, eventually you hit a node with no outgoing edges, as there is no cycle
		> the node that you reach in a DFS is "safe" to place at the end of the topological sort
			= think of D from the above example
		> ∴ if we have added each vertex "below" the current vertex into our topological sort, then it is safe to add current
			= if we added D from above example, then it is safe to add B
	- Steps for Topological Sort:
		1.) Do a DFS starting with some node
		2.) The "last" node you reach before you are forced to backtrack goes at the end of the sort
		3.) Continue DFS placing each "dead end" node into the topo sort in reverse order
			