class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Edge case: if k <= 1, no positive integer product can be strictly less than k
        if (k <= 1) return 0;

        int left = 0;
        int product = 1;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            // Shrink the window from the left while product is >= k
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            // Add the number of valid subarrays ending at the current 'right' pointer
            count += right - left + 1;
        }

        return count;
    }
}