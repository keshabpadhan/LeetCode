class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int max = 0;
        for (int v : nums) max = Math.max(max, v);
        int size = 2 * max;

        boolean[] pair = new boolean[size];
        boolean[] triplet = new boolean[size];

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }

        for (int v = 0; v < size; v++) {
            if (pair[v]) {
                for (int x : nums) {
                    triplet[v ^ x] = true;
                }
            }
        }

        int ans = 0;
        for (boolean b : triplet) if (b) ans++;
        return ans;
    }
}