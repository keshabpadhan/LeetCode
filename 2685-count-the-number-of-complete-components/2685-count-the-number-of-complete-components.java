import java.util.ArrayList;
import java.util.List;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] componentData = dfs(i, adj, visited);
                int nodeCount = componentData[0];
                int totalDegrees = componentData[1];

                // In a complete graph, the sum of degrees equals V * (V - 1)
                if (totalDegrees == nodeCount * (nodeCount - 1)) {
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    private int[] dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        int nodes = 1;
        int degrees = adj.get(node).size();

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                int[] nextData = dfs(neighbor, adj, visited);
                nodes += nextData[0];
                degrees += nextData[1];
            }
        }
        return new int[]{nodes, degrees};
    }
}