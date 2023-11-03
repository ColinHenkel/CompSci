import java.util.*;
import java.io.*;

public class Main {
	// main class members
	static Scanner in = new Scanner(System.in);
	public static int N; // # nodes/barns
	public static int M; // # paths
	public static ArrayList<Integer>[] graph; // adjancecy list

	public static void main(String[] args) {
		// taking input of # nodes and # of paths
		N = in.nextInt();
		M = in.nextInt();

		// initialize adjacency list
		graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// take input for all paths
		for(int i = 0; i < M; i++) {
			int node1 = in.nextInt() - 1;
			int node2 = in.nextInt() - 1;
			graph[node1].add(node2);
			graph[node2].add(node1);
		}

		// take input for order of node closing
		int[] removalList = new int[N];
		for(int i = 0; i < N; i++) {
			int node = in.nextInt() - 1;
			removalList[i] = node;
		}

		// disjointset algorithm for closing nodes and checking connection
		DisjointSet ds = new DisjointSet(N);
		boolean[] isOpen = new boolean[N];
		boolean[] result = new boolean[N];

		result[N-1] = true; // marking single bar as open
		isOpen[removalList[N-1]] = true; // marking last barn as open

		for(int i = N - 2; i >= 0; i--) { // disjointset operations
			int currentNode = removalList[i];

			for(int j = 0; j < graph[currentNode].size(); j++) { // iterate through neighbors and union if open
				int neighborNode = graph[currentNode].get(j);
				if(isOpen[neighborNode]) {
					ds.union(currentNode, neighborNode);
				}
			}

			result[i] = (ds.numTrees == i + 1);
			isOpen[currentNode] = true;
		}

		// iterate through and output result
		for(int i = 0; i < N; i++) {
			System.out.println(result[i] ? "YES" : "NO");
		}

		in.close();
	}
}

// class for disjoint set
class DisjointSet {
	int[] parent;
	int[] height;
	int numTrees;

	// constructor for DisjointSet with given value n
	public DisjointSet(int n) {
		parent = new int[n];
		height = new int[n];
		numTrees = n;

		for(int i = 0; i < n; i++) {
			parent[i] = i;
			height[i] = 0;
		}
	}

	// method for finding set containing id
	public int find(int id) {
		if(parent[id] != id) {
			parent[id] = find(parent[id]);
		}

		return parent[id];
	}

	// method for unionizing two values
	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if(rootX != rootY) {
			if(height[rootX] < height[rootY]) {
				parent[rootX] = rootY;
			} else if(height[rootX] > height[rootY]) {
				parent[rootY] = rootX;
			} else {
				parent[rootY] = rootX;
				height[rootX]++;
			}
			numTrees--;
		}
	}
}
