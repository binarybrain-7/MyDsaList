class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        
        // Pick each starting index
        for (int i = 0; i < n; i++) {
            int currSum = 0;
            // Extend the subarray
            for (int j = i; j < n; j++) {
                currSum += nums.get(j);
                int len = j - i + 1;
                
                // Check if length is within [l, r] and sum is positive
                if (len >= l && len <= r && currSum > 0) {
                    minSum = Math.min(minSum, currSum);
                }
                
                // Optimization: if length exceeds r, we can stop extending this subarray
                if (len > r) {
                    break;
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}