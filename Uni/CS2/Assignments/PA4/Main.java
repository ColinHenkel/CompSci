import java.util.*;

        /* COP 3503C Assignment 4
This program is written by: Colin J Henkel */

public class Main {
    // define the possible directions for movement
    final public static int[] DX = {1, -1, 0, 0};
    final public static int[] DY = {0, 0, 1, -1};
    public static int r, c;
    public static char[][] maze;
    public static Map<Character, List<Pair>> portals = new HashMap<>();

    // define a Pair class to store row and column information
    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        // read in the maze
        Scanner in = new Scanner(System.in);
        r = in.nextInt();
        c = in.nextInt();
        maze = new char[r][c];
        for (int i = 0; i < r; i++) {
            maze[i] = in.next().toCharArray();
        }
        in.close();

        // find '*' and '$' and portal locations and run a BFS from start to end
        Pair start = find('*');
        Pair end = find('$');
        int result = bfs(start, end);
        if (result == -1) {
            System.out.println("Call 911");
        } else {
            System.out.println(result);
        }
    }

    // runs a BFS from location s to location e
    public static int bfs(Pair s, Pair e) {

        // set up BFS
        LinkedList<Pair> q = new LinkedList<>();
        q.offer(s);

        // store -1 for haven't visited...otherwise a distance array
        int[][] dist = new int[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[s.x][s.y] = 0;

        // run until the queue is done
        while (!q.isEmpty()) {

            // get the next item
            Pair cur = q.poll();
            if (cur.x == e.x && cur.y == e.y) return dist[e.x][e.y];

            // try moving in all 4 directions
            for (int i = 0; i < DX.length; i++) {

                // calculate new position
                int nX = cur.x + DX[i];
                int nY = cur.y + DY[i];

                // off the board or have been there before: skip it
                if (!inBounds(nX, nY) || maze[nX][nY] == '!' || dist[nX][nY] != -1) continue;

                // mark the distance and add to queue
                dist[nX][nY] = dist[cur.x][cur.y] + 1;
                q.offer(new Pair(nX, nY));
            }

            // if the current position is a portal, add the portal's destinations to the queue
            char curChar = maze[cur.x][cur.y];
            if (Character.isLetter(curChar) && portals.containsKey(curChar)) {
                List<Pair> portalDestinations = portals.get(curChar);
                for (Pair destination : portalDestinations) {
                    if (dist[destination.x][destination.y] == -1) {
                        dist[destination.x][destination.y] = dist[cur.x][cur.y] + 1;
                        q.offer(destination);
                    }
                }
            }
        }

        // no path found
        return -1;
    }

    // returns true if (x, y) is within bounds
    public static boolean inBounds(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    // returns the first location where character c occurs or null if it doesn't
    public static Pair find(char ch) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze[i][j] == ch) {
                    return new Pair(i, j);
                } else if (Character.isLetter(maze[i][j])) {
                    char portalLetter = maze[i][j];
                    portals.computeIfAbsent(portalLetter, k -> new ArrayList<>()).add(new Pair(i, j));
                }
            }
        }
        return null;
    }
}
