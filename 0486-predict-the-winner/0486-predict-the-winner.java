class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        for (int l = n - 1; l >= 0; l--) {
            dp[l] = nums[l];
            for (int r = l + 1; r < n; r++) {
                dp[r] = Math.max(nums[l] - dp[r], nums[r] - dp[r - 1]);
            }
        }

        return dp[n - 1] >= 0;
    }
}