class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1000000007;

        // 1. Extract only non-zero digits
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') count++;
        }

        int[] digits = new int[count];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                digits[idx++] = s.charAt(i) - '0';
            }
        }

        // 2. Precompute powers of 10, prefix sums, and prefix values
        long[] pow10 = new long[count + 1];
        long[] prefSum = new long[count + 1];
        long[] prefVal = new long[count + 1];
        
        pow10[0] = 1;
        for (int i = 0; i < count; i++) {
            pow10[i + 1] = (pow10[i] * 10) % MOD;
            prefSum[i + 1] = prefSum[i] + digits[i];
            prefVal[i + 1] = (prefVal[i] * 10 + digits[i]) % MOD;
        }

        // 3. Map original indices to non-zero array indices
        int[] nextNonZero = new int[m];
        int[] prevNonZero = new int[m];
        int pos = count;
        
        for (int i = m - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') pos--;
            nextNonZero[i] = pos;
        }
        
        pos = -1;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') pos++;
            prevNonZero[i] = pos;
        }

        // 4. Process queries in O(1) time
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int i = nextNonZero[queries[q][0]];
            int j = prevNonZero[queries[q][1]];

            if (i > j) {
                answer[q] = 0;
                continue;
            }

            long sum = prefSum[j + 1] - prefSum[i];
            long x = (prefVal[j + 1] - (prefVal[i] * pow10[j - i + 1]) % MOD + MOD) % MOD;

            answer[q] = (int) ((x * sum) % MOD);
        }

        return answer;
    }
}