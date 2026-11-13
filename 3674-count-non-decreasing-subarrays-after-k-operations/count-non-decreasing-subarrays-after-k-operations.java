import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        // Stores pairs of (element_value, count) or indices depending on implementation
        Deque<long[]> dq = new ArrayDeque<>();
        int j = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; --i) {
            long num = nums[i];
            long count = 1;
            
            // Maintain monotonic decreasing order in deque
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                long[] prev = dq.pollLast();
                long nextNum = prev[0];
                long nextCount = prev[1];
                count += nextCount;
                cost += (num - nextNum) * nextCount;
            }
            dq.offerLast(new long[]{num, count});

            // Shrink the window from the right if cost exceeds k
            while (cost > k) {
                long[] front = dq.peekFirst();
                long rightmostNum = front[0];
                long rightmostCount = front[1];
                
                cost -= rightmostNum - nums[j];
                j--;
                
                if (rightmostCount > 1) {
                    dq.pollFirst();
                    dq.offerFirst(new long[]{rightmostNum, rightmostCount - 1});
                } else {
                    dq.pollFirst();
                }
            }
            
            ans += (j - i + 1);
        }
        
        return ans;
    }
}