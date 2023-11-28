// Java program to print BFS and DFS traversal from a given source vertex.

import java.util.*;
 
// This class represents an undirected graph using adjacency list (simply change the addEdge method to make it directed)
class BFSDFS
{
    private int V;   // Nunber of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
 
    // Constructor added this to remove warning @SuppressWarnings("unchecked")
    @SuppressWarnings("unchecked")BFSDFS(int v)
    {
        V = v;
      //maintain a list for each vertex
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList<Integer>();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
      //undirected graph. So, we put both direction in the list. If you want to make it directed, simply remove the second line.
      
        adj[v].add(w);
        adj[w].add(v);
    }
 
    // prints BFS traversal from a given source s
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

  // A function used by DFS
    void DFSUtil(int v, boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");
 
        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    // The function to do DFS traversal. It uses recursive
    // DFSUtil()
    void DFS()
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper function to print DFS
        // traversal starting from all unvisited vertices one by one to deal with disconnected graph
        for (int i = 0; i < V; ++i)
            if (visited[i] == false)
                DFSUtil(i, visited);
    }
 
    // Driver method to
    public static void main(String args[])
    {
        BFSDFS g = new BFSDFS(7);
      
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 6);
 
        System.out.println("BFS starting from vertex 0)");
 
        g.BFS(0); //starting from zero. If you have a disconnected graph, call BFS for each unvisited vertices using a loop

        System.out.println("\nDFS starting from vertex 0)");      

        g.DFS();
    }
}