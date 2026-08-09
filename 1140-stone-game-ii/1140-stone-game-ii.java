class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int[] prefixSum = new int[n + 1];

        // Compute prefix sum array
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + piles[i];
        }

        // Fill DP table from the end to the start
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                for (int x = 1; x <= 2 * m && i + x <= n; x++) {
                    int currentStones = prefixSum[i + x] - prefixSum[i];
                    int opponentStones = (i + x < n) ? dp[i + x][Math.max(m, x)] : 0;
                    dp[i][m] = Math.max(dp[i][m], currentStones + (prefixSum[n] - prefixSum[i + x] - opponentStones));
                }
            }
        }

        return dp[0][1];
    }
}