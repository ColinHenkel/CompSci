public class PerfectSquares {
    public static void main(String[] args) {
        // main method to test numSquares method
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(12)); // 3
        System.out.println(ps.numSquares(13)); // 2
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while (i - j * j >= 0) {
                min = Math.min(min, dp[i - j * j] + 1);
                j++;
            }
            dp[i] = min;
        }
        return dp[n];
    }
}
