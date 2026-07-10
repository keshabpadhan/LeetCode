import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Sort nodes by their value in nums
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        // Map original node indices to their sorted rank positions
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[order[i]] = i;

        // Build greedy transitions (furthest reachable node with value <= nums[curr] + maxDiff)
        int[][] up = new int[n][18];
        int right = 0;
        for (int i = 0; i < n; i++) {
            while (right < n && nums[order[right]] - nums[order[i]] <= maxDiff) {
                right++;
            }
            up[i][0] = right - 1;
        }

        // Fill the sparse table for binary lifting
        for (int k = 1; k < 18; k++) {
            for (int i = 0; i < n; i++) {
                up[i][k] = up[up[i][k - 1]][k - 1];
            }
        }

        // Answer each query using binary lifting
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = rank[queries[q][0]];
            int v = rank[queries[q][1]];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            if (u > v) { int tmp = u; u = v; v = tmp; }

            int steps = 0;
            for (int k = 17; k >= 0; k--) {
                if (up[u][k] < v) {
                    u = up[u][k];
                    steps += (1 << k);
                }
            }

            ans[q] = (up[u][0] >= v) ? steps + 1 : -1;
        }

        return ans;
    }
}