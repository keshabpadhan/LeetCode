class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int groups = n / 8;
        int rem = n % 8;
        return 8 * groups * (groups + 1) / 2 + rem * (groups + 1);
    }
}