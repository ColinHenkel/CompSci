import java.util.*;

public class FibonacciDP {

	final public static int N = 44;
	public static int[] fibarray;

	public static void main(String[] args) {
    long start, end;

    System.out.println("N = " + N);
    
    System.out.println("Calling the memoize version: ");
    start = System.currentTimeMillis();
    int res = CalculateFibonacciMemo(N);
    end = System.currentTimeMillis();
    System.out.println("res is "+res+" took "+(end-start)+" ms.");

    System.out.println("Calling the tabulation version: ");
    start = System.currentTimeMillis();
    res = CalculateFibonacciTabulation(N);
    end = System.currentTimeMillis();
    System.out.println("res is "+res+" took "+(end-start)+" ms.");

    System.out.println("Calling the regular slow version: ");
    start = System.currentTimeMillis();
    res = CalculateFibonacciSlow(N);
    end = System.currentTimeMillis();
    System.out.println("res is "+res+" took "+(end-start)+" ms.");    

    
	}

  //the regular slow one
   public static int CalculateFibonacciSlow(int n) {
    if(n < 2)
      return n;
    return CalculateFibonacciSlow(n-1) + CalculateFibonacciSlow(n-2);
  }


  //memoization wrapper
  public static int CalculateFibonacciMemo(int n) {
    int memoize[] = new int[n+1];
    Arrays.fill(memoize, -1);
    return CalculateFibonacciRecursiveMemo(memoize, n);
  }

  //memoization
  public static int CalculateFibonacciRecursiveMemo(int[] memoize, int n) {
    if(n < 2)
      return n;

    // if we have already solved this subproblem, simply return the result from the cache
    if(memoize[n] != -1)
      return memoize[n];

    memoize[n] = CalculateFibonacciRecursiveMemo(memoize, n-1) + CalculateFibonacciRecursiveMemo(memoize, n-2);
    
    return memoize[n];
  }

  //Tabulation DP
  public static int CalculateFibonacciTabulation(int n) {
    if (n==0) return 0;
    int dp[] = new int[n+1];

    //base cases
    dp[0] = 0;
    dp[1] = 1;

    for(int i=2; i<=n; i++)
      dp[i] = dp[i-1] + dp[i-2];

    return dp[n];
  }

      
  

	
}