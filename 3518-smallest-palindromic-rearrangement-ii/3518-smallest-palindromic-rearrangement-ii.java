class Solution {
    private static final int MAX = 1_000_001;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int[] half = new int[26];
        char mid = 0;
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            halfLen += half[i];
            if (cnt[i] % 2 == 1) mid = (char) ('a' + i);
        }

        if (count(half) < k) return "";

        StringBuilder left = new StringBuilder();
        for (int p = 0; p < halfLen; p++) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue;
                half[i]--;
                int ways = count(half);
                if (ways >= k) {
                    left.append((char) ('a' + i));
                    break;
                }
                k -= ways;
                half[i]++;
            }
        }

        String l = left.toString();
        String r = new StringBuilder(l).reverse().toString();
        return l + (mid == 0 ? "" : mid) + r;
    }

    private int count(int[] freq) {
        int total = 0;
        for (int f : freq) total += f;
        long res = 1;
        for (int f : freq) {
            if (f == 0) continue;
            res = res * nCk(total, f);
            if (res >= MAX) return MAX;
            total -= f;
        }
        return (int) res;
    }

    private long nCk(int n, int k) {
        k = Math.min(k, n - k);
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
            if (res >= MAX) return MAX;
        }
        return res;
    }
}