class Solution {
    private static final int[] C2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    private static final int[] C3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
    private static final int[] C5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    private static final int[] C7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

    public String smallestNumber(String num, long t) {
        long x = t;
        int a = 0, b = 0, c = 0, d = 0;
        while (x % 2 == 0) { a++; x /= 2; }
        while (x % 3 == 0) { b++; x /= 3; }
        while (x % 5 == 0) { c++; x /= 5; }
        while (x % 7 == 0) { d++; x /= 7; }
        if (x != 1) return "-1";

        int L = num.length();
        char[] s = num.toCharArray();
        int[] p2 = new int[L + 1], p3 = new int[L + 1], p5 = new int[L + 1], p7 = new int[L + 1];
        boolean[] hasZero = new boolean[L + 1];
        for (int i = 0; i < L; i++) {
            int dig = s[i] - '0';
            p2[i + 1] = p2[i] + C2[dig];
            p3[i + 1] = p3[i] + C3[dig];
            p5[i + 1] = p5[i] + C5[dig];
            p7[i + 1] = p7[i] + C7[dig];
            hasZero[i + 1] = hasZero[i] || (dig == 0);
        }

        if (!hasZero[L] && p2[L] >= a && p3[L] >= b && p5[L] >= c && p7[L] >= d)
            return num;

        for (int i = L - 1; i >= 0; i--) {
            if (hasZero[i]) continue;
            int cur = s[i] - '0';
            int need2 = Math.max(0, a - p2[i]);
            int need3 = Math.max(0, b - p3[i]);
            int need5 = Math.max(0, c - p5[i]);
            int need7 = Math.max(0, d - p7[i]);
            int suffixLen = L - 1 - i;
            for (int dig = cur + 1; dig <= 9; dig++) {
                int n2 = Math.max(0, need2 - C2[dig]);
                int n3 = Math.max(0, need3 - C3[dig]);
                int n5 = Math.max(0, need5 - C5[dig]);
                int n7 = Math.max(0, need7 - C7[dig]);
                if (feasible(n2, n3, n5, n7, suffixLen)) {
                    StringBuilder sb = new StringBuilder();
                    if (i > 0) sb.append(num, 0, i);
                    sb.append((char) ('0' + dig));
                    sb.append(minFill(n2, n3, n5, n7, suffixLen));
                    return sb.toString();
                }
            }
        }

        int minDigits = minDigits(a, b) + c + d;
        return minFill(a, b, c, d, Math.max(L + 1, minDigits));
    }

    private static int minDigits(int r2, int r3) {
        int best = Integer.MAX_VALUE;
        for (int z = 0; z <= Math.min(r2, r3); z++) {
            int eights = (r2 - z + 2) / 3;   // ceil((r2 - z)/3)  → digit 8 = 2³
            int nines  = (r3 - z + 1) / 2;   // ceil((r3 - z)/2)  → digit 9 = 3²
            best = Math.min(best, z + eights + nines); // z digit-6s give (1,1) each
        }
        return best;
    }

    private static boolean feasible(int r2, int r3, int r5, int r7, int m) {
        return r5 + r7 <= m && minDigits(r2, r3) <= m - r5 - r7;
    }

    private static String minFill(int r2, int r3, int r5, int r7, int m) {
        StringBuilder sb = new StringBuilder(m);
        for (int pos = 0; pos < m; pos++) {
            int left = m - pos - 1;
            for (int dig = 1; dig <= 9; dig++) {
                int n2 = Math.max(0, r2 - C2[dig]);
                int n3 = Math.max(0, r3 - C3[dig]);
                int n5 = Math.max(0, r5 - C5[dig]);
                int n7 = Math.max(0, r7 - C7[dig]);
                if (feasible(n2, n3, n5, n7, left)) {
                    sb.append((char) ('0' + dig));
                    r2 = n2; r3 = n3; r5 = n5; r7 = n7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}