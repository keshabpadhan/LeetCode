class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        HashMap<Integer, Integer> ranks = new HashMap<>();
        for (int num : sorted) {
            ranks.putIfAbsent(num, ranks.size() + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ranks.get(arr[i]);
        }
        return arr;
    }
}