class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] suf = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = m - 1 - j;
        }

        List<Integer> ans = new ArrayList<>();
        j = 0;
        boolean changed = false;

        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans.add(i);
                j++;
            } else if (!changed && suf[i + 1] >= m - j - 1) {
                ans.add(i);
                j++;
                changed = true;
            }
        }

        if (ans.size() != m) {
            return new int[0];
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}