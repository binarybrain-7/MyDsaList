import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
           
            int incLen = dp.getOrDefault(num, 0) + 1;
            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), incLen));
            ans = Math.max(ans, dp.get(num + 1));

            // Option 2: Keep num as num
            int sameLen = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num, Math.max(dp.getOrDefault(num, 0), sameLen));
            ans = Math.max(ans, dp.get(num));
        }

        return ans;
    }
}