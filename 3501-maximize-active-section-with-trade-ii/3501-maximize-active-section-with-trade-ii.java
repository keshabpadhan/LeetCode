class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        List<int[]> groups = new ArrayList<>();
        int[] lookup = new int[n];
        Arrays.fill(lookup, -1);
        int totalOnes = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (!groups.isEmpty() &&
                    groups.get(groups.size() - 1)[0] +
                            groups.get(groups.size() - 1)[1] == i) {
                    groups.get(groups.size() - 1)[1]++;
                } else {
                    groups.add(new int[]{i, 1});
                }
            } else {
                totalOnes++;
            }
            lookup[i] = groups.size() - 1;
        }

        if (groups.isEmpty()) {
            List<Integer> ans = new ArrayList<>(queries.length);
            for (int i = 0; i < queries.length; i++) ans.add(totalOnes);
            return ans;
        }

        int g = groups.size();
        int[] adjSum = new int[g - 1];
        for (int i = 0; i < g - 1; i++) {
            adjSum[i] = groups.get(i)[1] + groups.get(i + 1)[1];
        }
        SparseTable st = new SparseTable(adjSum);

        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int left = lookup[l] + 1;
            int right = lookup[r] - (s.charAt(r) == '0' ? 1 : 0);
            int best = 0;

            if (left <= right - 1) {
                best = Math.max(best, st.query(left, right - 1));
            }

            if (s.charAt(l) == '0') {
                int idxL = lookup[l];
                int leftLen = groups.get(idxL)[1] - (l - groups.get(idxL)[0]);
                if (lookup[l] + 1 <= right) {
                    best = Math.max(best, leftLen + groups.get(lookup[l] + 1)[1]);
                }
                if (s.charAt(r) == '0' && lookup[l] + 1 == lookup[r]) {
                    int rightLen = r - groups.get(lookup[r])[0] + 1;
                    best = Math.max(best, leftLen + rightLen);
                }
            }

            if (s.charAt(r) == '0') {
                int idxR = lookup[r];
                int rightLen = r - groups.get(idxR)[0] + 1;
                if (left <= lookup[r] - 1) {
                    best = Math.max(best, groups.get(lookup[r] - 1)[1] + rightLen);
                }
            }

            ans.add(totalOnes + best);
        }
        return ans;
    }

    static class SparseTable {
        int[][] st;
        int[] log;

        SparseTable(int[] arr) {
            int n = arr.length;
            if (n == 0) return;
            int K = Integer.SIZE - Integer.numberOfLeadingZeros(n);
            st = new int[K][n];
            log = new int[n + 1];
            for (int i = 2; i <= n; i++) log[i] = log[i >> 1] + 1;
            System.arraycopy(arr, 0, st[0], 0, n);
            for (int i = 1; i < K; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    st[i][j] = Math.max(st[i - 1][j],
                                        st[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        int query(int l, int r) {
            if (l > r) return 0;
            int i = log[r - l + 1];
            return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
        }
    }
}