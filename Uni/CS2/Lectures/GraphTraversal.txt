Graph Traversals
	- to traverse means to visit the vertices in a graph in some systematic order
	- in order to traverse graphs, we use:
		> Breadth First Search (BFS)
		> Depth First Search (DFS)
	- for traversal we need 2 terms:
		> visiting
		> exploration

Breadth First Search (BFS)
	- BFS uses a Queue data structure which follows first in first out
	- in BFS, one vertex is selected at a time when it is visited and marked then its adjacent are visited and stored in the queue
	- Consider the following graph:
		> Vertex set: {1,2,3,4,5,6,7}
		> Edge set: {{1,5},{1,4},{1,2},{2,7},{2,6},{2,3}}
		> BFS traversal solution would be: 1, 2, 4, 5, 3, 6, 7
		> Steps:
			1) start from any vertex (we start from 1) and once we visit 1 we explore it in Step 2
			2) visit the adjacenct vertices (for 1 they are 2, 4, 5)
				=> you can visit them in any order, example did 2, 4, and 5
			3) explore 2 visiting vertex 3, 6, and 7
			4) explore 4 finding nothing
			5) explore 3, 6, and 7 also finding nothing
		> BFS remembers the sequence by maintaining a queue to remember which vertex to explore next
	- Time Complexity:
		> O(V+E) where V is the number of vertices and E is the number of edges

Depth First Search (DFS)
	- DFS uses a Stack data structure, which is taken care of by recursion
	- Consider the same above graph:
		> DFS traversal solution would be: 1, 2, 3, 6, 7, 4, 5
		> Steps:
			1) start from 1, begin exploring
			2) visit ONE of the adjacent vertices
				=> can be visited in any order, example visits 2
			3) explore 2, visiting ONE of its adjacent vertices, example uses 3
			4) explore 3, find nothing so 3 is completely explored and go back to 2
			5) visit and explore 6, find nothing and go back to 2
			6) visit and explore 7, find nothing and go back to 2
			7) all adjacencies of 2 have been explored so go back to 1
			8) visit and explore 4 and 5 in the same way as 6 and 7
	- when traversing a tree (as trees are also graphs) BFS will always be LEVEL ORDER and DFS will always be PRE-ORDER
	- Time Complexity:
		> O(V+E) where V is the number of vertices and E is the number of edges
