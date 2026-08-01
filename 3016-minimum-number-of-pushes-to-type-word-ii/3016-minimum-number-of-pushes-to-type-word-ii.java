import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Sort frequencies in descending order
        Arrays.sort(freq);
        reverse(freq);

        int totalPushes = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) break;
            // Cost: (i / 8 + 1) * frequency
            totalPushes += (i / 8 + 1) * freq[i];
        }

        return totalPushes;
    }

    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}