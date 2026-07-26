class Solution {
    public int maximumProduct(int[] nums) {
        int mi1 = Integer.MAX_VALUE, mi2 = Integer.MAX_VALUE;
        int mx1 = Integer.MIN_VALUE, mx2 = Integer.MIN_VALUE, mx3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x > mx1) {
                mx3 = mx2;
                mx2 = mx1;
                mx1 = x;
            } else if (x > mx2) {
                mx3 = mx2;
                mx2 = x;
            } else if (x > mx3) {
                mx3 = x;
            }

            if (x < mi1) {
                mi2 = mi1;
                mi1 = x;
            } else if (x < mi2) {
                mi2 = x;
            }
        }

        return Math.max(mi1 * mi2 * mx1, mx1 * mx2 * mx3);
    }
}