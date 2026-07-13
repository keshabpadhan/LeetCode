class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String digits = "123456789";
        List<Integer> res = new ArrayList<>();

        for (int len = 2; len <= 9; len++) {
            for (int start = 0; start <= 9 - len; start++) {
                int num = Integer.parseInt(digits.substring(start, start + len));
                if (num > high) break;
                if (num >= low) res.add(num);
            }
        }

        return res;
    }
}