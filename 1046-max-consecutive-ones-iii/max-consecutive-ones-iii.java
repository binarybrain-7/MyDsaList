class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;
        int left = 0, right = 0;
        int zeroes = 0;
        
        while (right < n) {
            if (nums[right] == 0) {
                zeroes++;
            }
            
            // Fix: Increment left and adjust zeroes properly
            while (zeroes > k) {
                if (nums[left] == 0) {
                    zeroes--;
                }
                left++; // This must be outside the `if` block so left always advances!
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        
        return maxLen;
    }
}