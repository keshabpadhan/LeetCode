class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            frequency.put(nums[right], frequency.getOrDefault(nums[right], 0) + 1);

            while (frequency.get(nums[right]) > k) {
                frequency.put(nums[left], frequency.get(nums[left]) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}