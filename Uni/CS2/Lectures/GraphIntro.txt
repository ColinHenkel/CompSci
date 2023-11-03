Graphs
	- Definition (unweighted, undirected):
		> a graph G consists of
			=> a set of vertices, V
			=> a set of edges, E
			=> where each edge is associated with a pair of vertices
		> we write: G = (V, E)
		> eg:
			=> Vertex set V = {1,2,3,4,5,6}
			=> Edge set E = {{1,2},{1,5},{2,3},{2,5},{3,4},{4,5},{4,6}}
	- Weighted Graph:
		> same as above, but where each edge also has an associated real number with it, known as edge weight
			=> edge weight = cost of traversing that edge in either direction aka sum of the weights of selected edges
	- Directed Graph:
		> a graph in which edges represent direction is called a directed graph
		> in a directed graph, arrows are used instead of lines/edges
		> direction denotes the intended path to reach from one node to another
		> directed graphs can be traversed in either one direction or bidirectional depending on arrows
		> eg:
			=> Edge set E = {{1->2},{1->3},{3->2},{3->4},{4->3}}
	- Vertex Degree:
		> in graph theory, the degree of a vertex is the number of edges connected to it
		> a vertex with degree 1 is called an "end vertex"
		> in a directed graph, each vertex has an INDEGREE and an OUTDEGREE
			=> Indegree: indegree of vertex V is the number of edges which are coming into the vertex V
			=> Outdegree: outdegree of Vertex V is the number of edges which are going out from the vertex V

Data Structures For Graphs
	- Edge List:
		> each vertex object contains a label for that vertex
		> each edge object contains a label for that edge, as well as two references
			=> one to the starting vertex and one to the ending vertex
			=> the designation of starting and ending is unecessary in an undirected graph
		> Example: Vertex List = 1,2,3,4,5,6
			=> Edge List = 1-6, 2-1, 2-5, 3-2, 3-5, 4-6, 5-1, 5-3, 5-4, 5-6 (CHECK SLIDE FOR VISUAL REPRESENTATION)
		> performance of using edgelist:
			1) accessing information about the graph such as its size, numbers of vertices etc. can be done in constant time by keeping counters in the data structure
			2) function can loop through all vertices and edges in O(V) or O(E) time, respectively
			3) insertion of edges and vertices can be done in O(1) time, assuming you already have access to the two vertices you are connecting with an edge
			4) access to edges incident to a vertex takes O(E) time, since all edges have to be inspected
	- Adjacency List:
		> an adjacency list represents a graph as an array of linked lists
		> the index of the array represents a vertex and each element in its linked list represents the other vertices that form an edge with the vertex
		> eg:
			=> 0->1->2->3
			   1->0->2
			   2->0->1
			   3->0
		> Pros:
			=> efficient in terms of storage because it only needs to store the values for the edges
				- (for a sparse graph with millions of vertices and edges, this can mean a lot of saved space)
			=> also helps to find all the vertices adjacent to a vertex easily
		> Cons:
			=> finding adjacent list is not quicker than the adjacency matrix because all connected nodes must first be explored to find them
	- Adjacency Matrix:
		> certain operations are slow using just an adjacency list because one does not have quick access to incident edges of a vertex
		> we can add to the Adjacency List structure:
			=> a list of each edge that is incident to a vertex sotred at that vertex
			=> this gives the direct access to incident edges that speeds up many algorithms
				- in Dijkstra's we always update estimates by looking at each edge incident to a particular vertex
		> the standard adjacency matrix stores a matrix as a 2-D array with each slot in A[i][j] being a 1 if there is an edge from vertex i to vertex j, storing 0 otherwise
			=> can have a more O-O design where each entry in the array is null if no edge is connecting those vertices, or an edge object that stores all necessary edge information
			=> eg: 1 2 3 4 5 6
				 1|0 1 0 0 1 0
				 2|1 0 1 0 1 0
				 3|0 1 0 1 0 0
				 4|0 0 1 0 1 1
				 5|1 1 0 1 0 0
				 6|0 0 0 1 0 0
			=> for example, 2 is connected to 1, 3, and 5
		> although matrix is very easy to work with mathematically, they are more inefficient than adjacency lists for several tasks
			=> for example, you have to scan all vertices to find all the edges incident to a vertex

Application of Graphs
	- can represent several types of data as a graph and use them for different applications
	- representing a social network
	- representing a road network
	- indoor topologies
	- connections of computers in a network
	- shortest path
	- finding path that minimizes the cost
	- etc

Graph Definitions & Terms
	- Complete undirected unweighted graph:
		> there is an edge connecting all possible pairs of vertices in a graph
		> the complete graph with n vertices is denoted as Kn
	- Bipartite
		> a graph is bipartite if there exists a way to partition the set of vertices V in the graph into two sets V1 and V2
		> where V1 ∪ V2 = V and V1 ∩ V2 = ∅ such that each edge in E contains one vertex from V1 and the other vertex from V2
	- Complete Bipartite Graph
		> a complete bipartite graph on m and n vertices is denoted by Kmn and consists of m+n vertices
		> each of the first m vertices is connected to all of the other n vertices and no others
	- Path
		> a path of length n from vertex v0 to vertex vn is an alternating sequence of n+1 vertices and n edges beginning with v0 and ending with vn
		> edge ei must be incident upon vertices vi-1 and vi
	- Connected Graph
		> a graph is connected if any two vertices of the graph are connected by a path
	- Disconnected Graph
		> a graph is disconnected if at least two vertices of the graph are not connected by a path
	- Function dist(v,w)
		> v and w are two vertices in a graph, returns the shortest path from v to w as the sum of all weights of traversal path
		> eg: dist(b,e) = 8 if {b->e} = 10, {b->d} = 3, and {d->e} = 5
	- Subgraph
		> graph whose vertices and edges are subsets of another graph
	- Simple Path
		> path containing no repeated vertices
	- Cycle
		> path of non-zero length to and from the same vertex with no repeated edges
	- Simple Cycle
		> a cycle with no repeated vertices except for the first and last
		> rule of thumb is if a cycle cant be broken down into 2 or more cycles, it is a simple cycle
	- Hamiltonian Path
		> path in an undirected or directed graph that visits each vertex exactly once
	- Hamiltonian Cycle
		> a closed path that visits every vertex exactly once except the initial vertices
	- Euler Cycle
		> a cycle that contains EVERY edge in the graph exactly once
			=> a vertex can be in a euler cycle more than once, but these are often known as euler circuits
	- Complement
		> the complement of a graph G is a graph G' which contains all vertices of G
		> for each edge that exists in G, it is NOT in G', and for each possible edge NOT in G, it is in G'
	- Chromatic Number
		> minimum number of colors that is necessary to color a graph

Graph Coloring
	- for unweighted undirected graphs:
		> assign a color to each vertex such that no two vertices connected by an edge are the same color
		> a graph where all vertice are connected (complete graph) must have all of its vertices colored separate colors
	- all bipartite graphs can be colored with only two colors, and all graphs that can be colored with two colors are bipartite
