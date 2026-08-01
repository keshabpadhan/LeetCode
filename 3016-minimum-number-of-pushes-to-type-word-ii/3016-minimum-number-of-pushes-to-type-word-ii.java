class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (int i = word.length() - 1; i >= 0; i--) {
            freq[word.charAt(i) - 'a']++;
        }

        java.util.Arrays.sort(freq);

        int total = 0;
        for (int i = 25; i >= 0 && freq[i] > 0; i--) {
            total += ((25 - i) / 8 + 1) * freq[i];
        }

        return total;
    }
}