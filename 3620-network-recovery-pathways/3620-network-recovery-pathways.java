import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Build the adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int maxCost = -1;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj.get(u).add(new int[]{v, cost});
            maxCost = Math.max(maxCost, cost);
        }
        
        // Compute in-degrees for topological sort
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }
        
        // Standard Topological Sort order for the entire DAG
        List<Integer> topoOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        
        // Binary search for the maximum possible minimum-edge cost
        int low = 0, high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(n, topoOrder, adj, online, k, mid)) {
                ans = mid;
                low = mid + 1; // Try to find a larger minimum edge cost
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int n, List<Integer> topoOrder, List<List<int[]>> adj, boolean[] online, long k, int minEdgeCost) {
        // dp[i] stores the minimum path cost from node 0 to node i
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        
        for (int u : topoOrder) {
            if (dp[u] == Long.MAX_VALUE) continue;
            
            // If we are at an intermediate node and it's offline, we cannot proceed
            if (u != 0 && u != n - 1 && !online[u]) continue;
            
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int cost = neighbor[1];
                
                // Only consider edges with cost >= minEdgeCost
                if (cost >= minEdgeCost) {
                    if (dp[u] + cost < dp[v]) {
                        dp[v] = dp[u] + cost;
                    }
                }
            }
        }
        
        return dp[n - 1] <= k;
    }
}