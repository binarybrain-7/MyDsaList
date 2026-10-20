class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefixMax = new int[n + 1];
        int[] suffixMin = new int[n + 1];
        int[] prefixDrops = new int[n];

        for (int i = 0; i < n; i++) {
            prefixMax[i + 1] = Math.max(prefixMax[i], nums[i]);
        }

        suffixMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        for (int i = 1; i < n; i++) {
            prefixDrops[i] = prefixDrops[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int answer = 0;

        for (int k = 1; k <= n / k; k++) {
            if (n % k == 0) {
                if (isSortable(nums, prefixMax, suffixMin,
                        prefixDrops, k)) {
                    answer += k;
                }

                int other = n / k;

                if (other != k &&
                    isSortable(nums, prefixMax, suffixMin,
                            prefixDrops, other)) {
                    answer += other;
                }
            }
        }

        return answer;
    }

    private boolean isSortable(
            int[] nums,
            int[] prefixMax,
            int[] suffixMin,
            int[] prefixDrops,
            int k) {

        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            // Count descents inside this block.
            int drops = prefixDrops[end] - prefixDrops[start];

            // Include the circular boundary.
            if (nums[end] > nums[start]) {
                drops++;
            }

            // A block must be cyclically non-decreasing.
            if (drops > 1) {
                return false;
            }

            // Check whether adjacent blocks can be joined.
            if (start > 0 &&
                prefixMax[start] > suffixMin[start]) {
                return false;
            }
        }

        return true;
    }
}