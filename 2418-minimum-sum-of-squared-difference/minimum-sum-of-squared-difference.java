import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + k2;
        
        // Find maximum difference to size our frequency bucket array
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        // Count frequencies of each difference
        long[] count = new long[maxDiff + 1];
        long totalInitialDiffSum = 0;
        for (int d : diffs) {
            count[d]++;
            totalInitialDiffSum += d;
        }
        
        // If total operations can reduce all differences to 0
        if (totalInitialDiffSum <= totalOps) {
            return 0;
        }
        
        // Greedily reduce from the largest difference downwards
        for (int i = maxDiff; i > 0 && totalOps > 0; i--) {
            if (count[i] > 0) {
                long take = Math.min(totalOps, count[i]);
                count[i] -= take;
                count[i - 1] += take;
                totalOps -= take;
            }
        }
        
        // Calculate the final minimum sum of squared differences
        long minSumSqDiff = 0;
        for (int i = 0; i <= maxDiff; i++) {
            if (count[i] > 0) {
                minSumSqDiff += count[i] * (long) i * i;
            }
        }
        
        return minSumSqDiff;
    }
}