class Solution {
    private long[][] dp;

    private long f(long[] sum, int i, int j) {
        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        
        long ans = 0;
        for (int k = i; k < j; k++) {
            long firstRowSum = sum[k] - (i > 0 ? sum[i - 1] : 0);
            long secondRowSum = sum[j] - sum[k];
            
            if (firstRowSum > secondRowSum) {
                ans = Math.max(ans, secondRowSum + f(sum, k + 1, j));
            } else if (firstRowSum < secondRowSum) {
                ans = Math.max(ans, firstRowSum + f(sum, i, k));
            } else {
                ans = Math.max(ans, Math.max(secondRowSum + f(sum, k + 1, j), firstRowSum + f(sum, i, k)));
            }
        }
        return dp[i][j] = ans;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            sum[i] = stoneValue[i];
            if (i > 0) sum[i] += sum[i - 1];
        }
        
        return (int) f(sum, 0, n - 1);
    }
}