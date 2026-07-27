class Solution {
    public int maxProduct(int[] nums) {
        int max = 0, secondMax = 0;
        for (int x : nums) {
            if (x > max) {
                secondMax = max;
                max = x;
            } else if (x > secondMax) {
                secondMax = x;
            }
        }
        return (max - 1) * (secondMax - 1);
    }
}