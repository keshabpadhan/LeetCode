class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int prev = 0, curr = 0, mx = 0, cnt1 = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                curr++;
            } else {
                if (curr > 0) {
                    prev = curr;
                    curr = 0;
                }
                cnt1++;
            }
            mx = Math.max(mx, prev + curr);
        }

        return (mx == prev || mx == curr) ? cnt1 : cnt1 + mx;
    }
}