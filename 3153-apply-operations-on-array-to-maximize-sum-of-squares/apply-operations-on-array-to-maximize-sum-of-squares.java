import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[30];
        
        // Step 1: Count the total number of set bits at each position
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long MOD = 1_000_000_007;
        long totalSum = 0;
        
        // Step 2: Greedily form the k numbers to maximize the sum of squares
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 30; bit++) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCount[bit]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}