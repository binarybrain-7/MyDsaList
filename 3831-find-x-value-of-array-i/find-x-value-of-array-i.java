class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // dp[r] stores the number of valid subarrays ending at the previous index
        // whose product modulo k equals r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;

            // Single element subarray starting at the current position
            nextDp[val] += 1;

            // Extend all previous subarrays by multiplying with the current element
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextRem = (r * val) % k;
                    nextDp[nextRem] += dp[r];
                }
            }

            // Accumulate counts into the final result array
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }

            // Move to the next index
            dp = nextDp;
        }

        return result;
    }
}
