import java.util.*;

/*         COP3503C Assignment 3
This program is written by: Colin Henkel */

public class PA3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();

        DisjointSet ds = new DisjointSet(n);

        int[][] connections = new int[m][2];

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            connections[i][0] = u;
            connections[i][1] = v;
            ds.union(u, v);
        }
        System.out.println(ds.getConnectivity());

        for (int i = d - 1; i >= 0; i--) { // start from the last destruction and move backward
            int connectionToDestroy = scanner.nextInt();
            int u = connections[connectionToDestroy - 1][0];
            int v = connections[connectionToDestroy - 1][1];

            // find the root nodes of the sets containing u and v
            int rootU = ds.find(u);
            int rootV = ds.find(v);

            // reverse the union operation
            if (rootU == rootV) {
                ds.parent[rootU] = rootU; // set the parent of u to itself
                ds.size[rootV] -= ds.size[rootU];
                ds.size[rootU] = 1;
            }
            System.out.println(ds.getConnectivity());
        }


        scanner.close();
    }
}

class DisjointSet {
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }

        return true;
    }

    public int getSize(int x) {
        return size[find(x)];
    }

    public int getConnectivity() {
        int total = 0;
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] == i) {
                int componentSize = size[i];
                total += componentSize * componentSize;
            }
        }
        return total;
    }
}
