Dijkstra's Algorithm
	- algorithm to find the shortest path from a source vertex to all other vertices in a weighted directed graph without negative edge weights
	- it can be done for undirected graphs as well, however it is guranteed that it will not work with negative edge weights
	- General Steps:
		> assume 'src' is the source vertex
		> initialize the distance from src to src to zero and src to all other vertices to Infinity
		> pick the vertex 'u' with min distance and we have not worked for this yet (at first it will be src)
		> add 'u' to the completed list
		> get all connected vertex from 'u' assuming they are v1, v2, v3, ... vn
		> update the distance for vx if it is not in the completed list and if the update helps to minimize the distance
	- Example for a Graph G:
		> Vertices V = {V1,...Vn}
			=> edge weights Wij, for edge connecting Vi to Vj
			=> let source be V1
		> initialize a Set S = ∅:
			=> keep track of the vertices for which we've already computed their shortest path from source
		> initialize an array D of estimates of shortest distances:
			=> initialize D[V1] = 0, everything else D[i] = ∞
			=> this says our estimate from the source to the source is 0, everything else is initially ∞
		> while S!=V (or while we haven't considered all vertices):
			1) find the vertex with the minimum dist (not already in S)
			2) add this vertex, Vi to S
			3) recompute all estimates based on Vi (this is also called relaxation of V_i)
				=> specifically compute D[i] + w
				=> if this < D[j] then set D[j] = D[i] + w
		> as we need to find the minimum D at each step, maintaining a minheap makes this process easier
	- Dijkstra's algorithm relies on:
		> knowing that all shortest paths contain subpaths that are also shortest paths
		> the shortest path from a to b is 10, so:
			=> the shortest path from a to d has to go through b
			=> it will also contain the shortest path from a to b, which is 10
			=> plus the additional 2 = 12
