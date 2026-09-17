import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // dp[i] stores the minimum length of a valid subarray ending at or before index i
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        int minLenSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the sliding window if sum exceeds target
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            // Found a valid subarray with sum equal to target
            if (sum == target) {
                int currentLen = right - left + 1;
                
                // If a valid non-overlapping subarray exists before 'left', update global result
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    minSum = Math.min(minSum, currentLen + dp[left - 1]);
                }
                
                // Track the shortest subarray length found up to current index
                minLenSoFar = Math.min(minLenSoFar, currentLen);
            }
            
            // Maintain prefix minimum subarray length
            dp[right] = minLenSoFar;
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}