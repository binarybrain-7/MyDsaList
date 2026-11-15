class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case: prefix sum of 0 is even
        long currentPrefixSum = 0;
        long totalSubarrays = 0;
        int MOD = 1000000007;

        for (int num : arr) {
            currentPrefixSum += num;

            if (currentPrefixSum % 2 != 0) {
                // If current prefix sum is odd, it forms an odd subarray with all previous even prefix sums
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // If current prefix sum is even, it forms an odd subarray with all previous odd prefix sums
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalSubarrays;
    }
}