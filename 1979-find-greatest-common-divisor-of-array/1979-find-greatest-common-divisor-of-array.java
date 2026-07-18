class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int x : nums) {
            if (x < min) min = x;
            if (x > max) max = x;
        }
        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}