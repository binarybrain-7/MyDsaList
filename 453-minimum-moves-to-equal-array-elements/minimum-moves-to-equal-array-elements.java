class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int min = nums[0];
        
        // Find the minimum element
        for (int i = 1; i < n; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        
        // Sum the differences from the minimum
        for (int i = 0; i < n; i++) {
            sum += nums[i] - min;
        }
        
        return sum;
    }
}