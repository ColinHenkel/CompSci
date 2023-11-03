import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    private int[][] square;
    private int magicConstant;
    private int totalCells;

    // constructer with n passed
    public Main(int n) {
        square = new int[n][n];
        magicConstant = n * (n * n + 1) / 2;
        totalCells = n * n;
    }

    // backtracking method for filling matrix and finding magicSquares
    public void fill(int row, int col, Set<Integer> used) {
        if(row == square.length) {
            if(validSquare())
                printSquare();
            return;
        }

        // iterate through all valid inputs and backtrack when needed
        for(int i = 1; i <= totalCells; i++) {
            if(!used.contains(i)) {
                square[row][col] = i;
                used.add(i);
                int nextRow = row;
                int nextCol = col + 1;

                // check if within bounds
                if(nextCol == square.length) {
                    nextRow += 1;
                    nextCol = 0;
                }
                fill(nextRow, nextCol, used);
                used.remove(i);
                square[row][col] = 0;
            }
        }
    }

    // method for confirming current permutation is a valid square
    public boolean validSquare() {
        // rowSum check
        int rowSum = 0;
        for(int r = 0; r < square.length; r++) {
            rowSum = 0;
            for(int c = 0; c < square.length; c++) {
                rowSum += square[r][c];
            }
            if(rowSum != magicConstant)
                return false;
        }

        // colSum check
        int colSum = 0;
        for(int c = 0; c < square.length; c++) {
            colSum = 0;
            for(int r = 0; r < square.length; r++) {
                colSum += square[r][c];
            }
            if(colSum != magicConstant)
                return false;
        }

        // diagsum check
        int diag1Sum = 0;
        int diag2Sum = 0;
        for(int i = 0; i < square.length; i++) {
            diag1Sum += square[i][i];
            diag2Sum += square[i][square.length - 1 - i];
        }

        return diag1Sum == magicConstant && diag2Sum == magicConstant;
    }

    public void printSquare() {
        System.out.println("Done!!");
        for(int i = 0; i < square.length; i++) {
            System.out.println(Arrays.toString(square[i]));
        }
    }

    public static void main(String args[]) {
        // reading matrix dimension and instantiating
        int n = in.nextInt();
        Main mm = new Main(n);
        Set<Integer> used = new HashSet<>();
        System.out.println("N = " + n);
        // filling object with magic squares and printing
        mm.fill(0, 0, used);
        in.close();
    }
}
