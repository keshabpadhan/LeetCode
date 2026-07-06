import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start point ascending. If starts are equal, sort by end point descending.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int remaining = intervals.length;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            int end = interval[1];
            // If the current end is covered by the previous maxEnd, remove it
            if (end <= maxEnd) {
                remaining--;
            } else {
                maxEnd = end;
            }
        }

        return remaining;
    }
}