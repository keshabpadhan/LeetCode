class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);

        int[] freq = new int[mx + 1];
        for (int x : nums) freq[x]++;

        long[] cntG = new long[mx + 1];

        for (int g = mx; g >= 1; g--) {
            int v = 0;
            for (int m = g; m <= mx; m += g) {
                v += freq[m];
                cntG[g] -= cntG[m];
            }
            cntG[g] += (long) v * (v - 1) / 2;
        }

        for (int g = 2; g <= mx; g++) {
            cntG[g] += cntG[g - 1];
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = search(cntG, queries[i]);
        }
        return ans;
    }

    private int search(long[] prefix, long x) {
        int lo = 0, hi = prefix.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (prefix[mid] > x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}