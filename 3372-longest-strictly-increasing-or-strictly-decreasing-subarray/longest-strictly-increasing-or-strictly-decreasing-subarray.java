class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int maxcnt = 1;
        
        // 1. Check for strictly increasing subarray
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                cnt++;
            } else if (nums[i] < nums[i - 1]) {
                cnt = 1; // Restart streak for decreasing element
            } else {
                cnt = 1; // Equal elements break strict monotonicity
            }
            maxcnt = Math.max(maxcnt, cnt);
        }
        
        // 2. Check for strictly decreasing subarray
        cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                cnt++;
            } else if (nums[i] > nums[i - 1]) {
                cnt = 1; // Restart streak for increasing element
            } else {
                cnt = 1; // Equal elements break strict monotonicity
            }
            maxcnt = Math.max(maxcnt, cnt);
        }
        
        return maxcnt;
    }
}