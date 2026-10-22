class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        int idx0 = -1, idxN = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) idx0 = i;
            if (nums[i] == n - 1) idxN = i;
        }
        
        // Check if it's an increasing cyclic shift
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }
        
        // Check if it's a decreasing cyclic shift
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idxN + i) % n] != (n - 1 - i)) {
                isDecreasing = false;
                break;
            }
        }
        
        if (!isIncreasing && !isDecreasing) {
            return -1;
        }
        
        int minCost = Integer.MAX_VALUE;
        
        if (isIncreasing) {
            // Option 1: Only left rotations
            // To bring 0 to index 0, we need `idx0` left rotations.
            minCost = Math.min(minCost, idx0);
            
            // Option 2: Reverse -> Left Rotate -> Reverse
            // Reversing brings the array to a decreasing state, rotating, then reversing back.
            // Cost: 1 (reverse) + (n - idx0) % n (rotate) + 1 (reverse) = (n - idx0) % n + 2
            minCost = Math.min(minCost, (n - idx0) % n + 2);
        }
        
        if (isDecreasing) {
            // Option 1: Reverse first to make it increasing, then rotate
            // Cost: 1 (reverse) + shift rotations
            // After reversing, the position of 0 is at (n - 1 - idxN) or similar. 
            // Let's test all possible rotation shifts for the decreasing family:
            for (int shift = 0; shift < n; shift++) {
                boolean match = true;
                for (int i = 0; i < n; i++) {
                    if (nums[(shift + i) % n] != (n - 1 - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    // Path to sort a decreasing cyclic shift:
                    // We can either:
                    // 1. Rotate by `shift`, then Reverse (cost: shift + 1)
                    // 2. Reverse first, then rotate (cost: 1 + rotations)
                    int cost1 = shift + 1;
                    int cost2 = (n - shift) % n + 1;
                    minCost = Math.min(minCost, Math.min(cost1, cost2));
                }
            }
        }
        
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
}
