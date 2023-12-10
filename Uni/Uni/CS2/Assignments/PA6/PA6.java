import java.util.*;

        /* COP 3503C Assignment 6
This program is written by: Colin J Henkel */

public class PA6 {
    public static void main(String[] args) {
        // declare variables
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] stuProblems = new int[2][n];

        // take array input
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < n; j++)
                stuProblems[i][j] = in.nextInt();
        }

        // calculate and print max number of problems
        System.out.println(maxProblemsTabulation(n, stuProblems));
        in.close();
    }

    public static int maxProblemsTabulation(int n, int[][] problems) {
        // init dp table and base cases
        int[][] dp = new int[2][n+1];
        dp[0][0] = problems[0][0];
        dp[1][0] = problems[1][0];
        dp[0][1] = problems[0][0];
        dp[1][1] = problems[1][0];

        for (int i = 2; i <= n; i++) {
            // calculate the maximum problems based on row
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + problems[0][i - 1];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + problems[1][i - 1];
        }

        return Math.max(dp[0][n], dp[1][n]);
    }
}
