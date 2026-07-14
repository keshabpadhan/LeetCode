class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);

        int[][] dp = new int[mx + 1][mx + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] ndp = new int[mx + 1][mx + 1];
            for (int g1 = 0; g1 <= mx; g1++) {
                for (int g2 = 0; g2 <= mx; g2++) {
                    int cur = dp[g1][g2];
                    if (cur == 0) continue;
                    // skip this element
                    ndp[g1][g2] = (ndp[g1][g2] + cur) % MOD;
                    // add to seq1
                    ndp[gcd(g1, x)][g2] = (ndp[gcd(g1, x)][g2] + cur) % MOD;
                    // add to seq2
                    ndp[g1][gcd(g2, x)] = (ndp[g1][gcd(g2, x)] + cur) % MOD;
                }
            }
            dp = ndp;
        }

        int ans = 0;
        for (int g = 1; g <= mx; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}