import java.util.*;

		/* COP3503C Assignment 2 */
/* This program is written by: Colin Henkel */

public class PA2 {
	static Scanner scanner = new Scanner(System.in);

	// main method
    public static void main(String[] args) {
        int M, N, S;
        M = scanner.nextInt(); // # rows
        N = scanner.nextInt(); // # columns
        S = scanner.nextInt(); // # words
        scanner.nextLine(); // newline
        
        // read and fill the matrix
        char[][] matrix = new char[M][N];
        for (int i = 0; i < M; i++) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = tokens[j].charAt(0);
            }
        }
        
        // read the words and search
        for (int s = 0; s < S; s++) {
            String word = scanner.nextLine();
            List<int[]> foundIndexes = new ArrayList<>();
            boolean found = findWord(matrix, word, foundIndexes);
            System.out.println("Looking for " + word);
            if (found) {
                printMatrix(matrix, foundIndexes);
            } else {
                System.out.println(word + " not found!");
            }
        }
        
        scanner.close();
    }
    
    // method for finding word using search
    public static boolean findWord(char[][] matrix, String word, List<int[]> foundIndexes) {
        int M = matrix.length;
        int N = matrix[0].length;
        int len = word.length();
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (search(matrix, word, i, j, len, 0, foundIndexes)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // method of searching matrix for given word using backtracking and collecting indices of chars for matrix printing
    public static boolean search(char[][] matrix, String word, int x, int y, int len, int idx, List<int[]> foundIndexes) {
        // terminating conditions
        if (idx == len) {
            return true;
        }
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != word.charAt(idx)) {
            return false;
        }
        
        char temp = matrix[x][y];
        matrix[x][y] = ' ';
        foundIndexes.add(new int[]{x, y}); // store index
        
        // recursively search surrounding cells
        boolean found = search(matrix, word, x, y + 1, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x, y - 1, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x + 1, y, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x - 1, y, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x + 1, y + 1, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x + 1, y - 1, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x - 1, y + 1, len, idx + 1, foundIndexes) ||
                        search(matrix, word, x - 1, y - 1, len, idx + 1, foundIndexes);
        
        matrix[x][y] = temp;
        if (!found) {
            foundIndexes.remove(foundIndexes.size() - 1); // backtrack
        }
        return found;
    }
    
    // method for printing found word as it appears in matrix
    public static void printMatrix(char[][] matrix, List<int[]> foundIndexes) {
        int M = matrix.length;
        int N = matrix[0].length;
        
        for (int i = 0; i < M; i++) {
        	System.out.print("[");
            for (int j = 0; j < N; j++) {
                boolean found = false;
                for (int[] index : foundIndexes) {
                    if (index[0] == i && index[1] == j) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                	if(j != N - 1) { System.out.print(matrix[i][j] + ", "); }
                	else { System.out.print(matrix[i][j]); }
                } else {
                	if(j != N - 1) { System.out.print(" , "); }
                	else { System.out.print(" "); }
                }
            }
            System.out.println("]");
        }
        System.out.println();
    }
}
