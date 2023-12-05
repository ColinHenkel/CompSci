/* Dynamic Programming Java implementation of LCS problem */
import java.util.*;

public class LCS
{

  // Returns length of LCS for X[0..m-1], Y[0..n-1]
   int lcs_recSlow(String X, String Y, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
 
        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            return 1 + lcs_recSlow(X, Y, m - 1, n - 1);
        } else {
            return Math.max(lcs_recSlow(X, Y, m, n - 1),
                    lcs_recSlow(X, Y, m - 1, n));
        }
    }

  // Returns length of LCS for X[0..m-1], Y[0..n-1] */
// memoization applied in recursive solution
    static int lcs_memo(String X, String Y, int m, int n, int dp[][]) {
        // base case
        if (m == 0 || n == 0) {
            return 0;
        }
 
        // if the same state has already been
        // computed
        if (dp[m - 1][n - 1] != -1) {
            return dp[m - 1][n - 1];
        }
 
        // if equal, then we store the value of the
        // function call
        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
 
            // store it in arr to avoid further repetitive
            // work in future function calls
            dp[m - 1][n - 1] = 1 + lcs_memo(X, Y, m - 1, n - 1, dp);
 
            return dp[m - 1][n - 1];
        } else {
 
            // store it in arr to avoid further repetitive
            // work in future function calls
            dp[m - 1][n - 1] = Math.max(lcs_memo(X, Y, m, n - 1, dp),
                    lcs_memo(X, Y, m - 1, n, dp));
 
            return dp[m - 1][n - 1];
        }
    }


  
 
  /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
  int lcs_tabulation( char[] X, char[] Y, int m, int n )
  {
      int L[][] = new int[m+1][n+1];
   
      /* Following steps build L[m+1][n+1] in bottom up fashion. Note
          that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
      for (int i=0; i<=m; i++)
      {
        for (int j=0; j<=n; j++)
        {
            if (i == 0 || j == 0)
                L[i][j] = 0;
            else if (X[i-1] == Y[j-1])
                L[i][j] = L[i-1][j-1] + 1;
            else
                L[i][j] = max(L[i-1][j], L[i][j-1]);
        }
      }
      return L[m][n];
  }
   
  /* Utility function to get max of 2 integers */
  int max(int a, int b)
  {
      return (a > b)? a : b;
  }
   
  public static void main(String[] args)
  {
      Main lcs = new Main();
      String s1 = "AGGTAB";
      String s2 = "GXTXAYB";
   
      char[] X=s1.toCharArray();
      char[] Y=s2.toCharArray();
      int m = X.length;
      int n = Y.length;
   
      System.out.println(" lcs_tabulation: Length of LCS is" + " " +
                                  lcs. lcs_tabulation( X, Y, m, n ) );



      int memo[][] = new int[m+1][n+1];
 
        // assign -1 to all positions
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
 
        System.out.println("lcs with moization: Length of LCS: " + lcs_memo(s1, s2, m, n, memo));

    
  
     System.out.println(" lcs_recursive slow: Length of LCS is" + " " +
                                  lcs.lcs_recSlow( s1, s2, m, n ) );
  
    
  }
 
}