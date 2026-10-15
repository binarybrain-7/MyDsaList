import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        // Sort intervals based on their end times in ascending order using a lambda
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 0;
        int freeTime = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            // If the current interval starts before or when the previous one ends, it overlaps
            if (intervals[i][0] < freeTime) {
                count++; // We need to remove this overlapping interval
            } else {
                freeTime = intervals[i][1]; // Update freeTime to the end of the current non-overlapping interval
            }
        }
        
        return count;
    }
}