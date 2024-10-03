import java.util.*;

public class RowReduction {
    public static void main(String[] args) {
        // main method to test the row reduction methods
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the number of rows: ");
        int rows = input.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = input.nextInt();

        double[][] matrix = new double[rows][cols];

        System.out.println("Enter the matrix row by row:");
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }

        System.out.println("The matrix you entered is:");
        printMatrix(matrix);
        System.out.println("The matrix in row echelon form is:");
        printMatrix(ref(matrix));
        System.out.println("The matrix in reduced row echelon form is:");
        printMatrix(rref(matrix));
        input.close();
    }

    // method to reduce a matrix to row echelon form
    public static double[][] ref(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int pivot = -1;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    pivot = j;
                    break;
                }
            }
            if (pivot != -1) {
                matrix = rowScale(matrix, i, 1 / matrix[i][pivot]);
                for (int j = i + 1; j < matrix.length; j++) {
                    if (matrix[j][pivot] != 0) {
                        matrix = rowAddScale(matrix, j, i, -matrix[j][pivot]);
                    }
                }
            }
        }
        return matrix;
    }

    // method to reduce a matrix to reduced row echelon form using helper methods
    public static double[][] rref(double[][] matrix) {
        matrix = ref(matrix);
        for (int i = 0; i < matrix.length; i++) {
            int pivot = -1;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    pivot = j;
                    break;
                }
            }
            if (pivot != -1) {
                matrix = rowScale(matrix, i, 1 / matrix[i][pivot]);
                for (int j = 0; j < matrix.length; j++) {
                    if (j != i && matrix[j][pivot] != 0) {
                        matrix = rowAddScale(matrix, j, i, -matrix[j][pivot]);
                    }
                }
            }
        }
        return matrix;
    }

    // method to scale a row by a scalar
    private static double[][] rowScale(double[][] matrix, int row, double scalar) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] *= scalar;
        }
        return matrix;
    }

    // method to add a scalar multiple of one row to another
    private static double[][] rowAddScale(double[][] matrix, int row1, int row2, double scalar) {
        for (int i = 0; i < matrix[row1].length; i++) {
            matrix[row1][i] += matrix[row2][i] * scalar;
        }
        return matrix;
    }

    // method to print a matrix
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("%8.2f", element);
            }
            System.out.println();
        }
    }
}
