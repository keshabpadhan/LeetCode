import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS directly from the single suspicious method 'k'
        isSuspicious[k] = true;
        queue.offer(k);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // Check if any non-suspicious method invokes a suspicious method
        for (int[] inv : invocations) {
            if (!isSuspicious[inv[0]] && isSuspicious[inv[1]]) {
                // If a clean method calls a suspicious one, we cannot remove any
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                remaining.add(i);
            }
        }

        return remaining;
    }
}
