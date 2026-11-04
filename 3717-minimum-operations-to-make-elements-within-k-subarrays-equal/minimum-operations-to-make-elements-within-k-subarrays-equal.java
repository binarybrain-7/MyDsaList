import java.util.*;

class Solution {

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window of size x
        long[] cost = new long[n - x + 1];

        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        for (int i = 0; i < n; i++) {

            int val = nums[i];

            // Insert element
            if (left.isEmpty() || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                leftSize++;
                leftSum += val;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                rightSize++;
                rightSum += val;
            }

            // Remove element outside window
            if (i >= x) {

                int remove = nums[i - x];

                if (left.containsKey(remove)) {
                    removeValue(left, remove);
                    leftSize--;
                    leftSum -= remove;
                } else {
                    removeValue(right, remove);
                    rightSize--;
                    rightSum -= remove;
                }
            }

            // Balance both halves
            while (leftSize > rightSize + 1) {

                int move = left.lastKey();

                removeValue(left, move);
                leftSize--;
                leftSum -= move;

                right.put(move, right.getOrDefault(move, 0) + 1);
                rightSize++;
                rightSum += move;
            }

            while (leftSize < rightSize) {

                int move = right.firstKey();

                removeValue(right, move);
                rightSize--;
                rightSum -= move;

                left.put(move, left.getOrDefault(move, 0) + 1);
                leftSize++;
                leftSum += move;
            }

            // Calculate minimum operations for current window
            if (i >= x - 1) {

                int median = left.lastKey();

                long operations =
                    (long) median * leftSize - leftSum
                    + rightSum - (long) median * rightSize;

                cost[i - x + 1] = operations;
            }
        }

        // Step 2: Dynamic Programming

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Option 1: Skip current element
                dp[i][j] = dp[i - 1][j];

                // Option 2: Select a window ending at i-1
                if (i >= x) {

                    long previous = dp[i - x][j - 1];

                    if (previous != INF) {

                        dp[i][j] = Math.min(
                            dp[i][j],
                            previous + cost[i - x]
                        );
                    }
                }
            }
        }

        return dp[n][k];
    }

    // Remove one occurrence from TreeMap
    private void removeValue(TreeMap<Integer, Integer> map, int val) {

        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}