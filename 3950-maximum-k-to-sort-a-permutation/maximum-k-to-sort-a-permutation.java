class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        
        // Check if the array is already sorted
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                isSorted = false;
                break;
            }
        }
        
        if (isSorted) {
            return 0;
        }
        
        // Find the bitwise AND of all elements not in their correct positions
        int maxK = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                if (maxK == -1) {
                    maxK = nums[i];
                } else {
                    maxK &= nums[i];
                }
            }
        }
        
        return maxK == -1 ? 0 : maxK;
    }
}