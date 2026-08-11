class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Find the longest sequential prefix and its sum
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Find the smallest missing integer >= sum
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int x = sum;
        while (numSet.contains(x)) {
            x++;
        }

        return x;
    }
}