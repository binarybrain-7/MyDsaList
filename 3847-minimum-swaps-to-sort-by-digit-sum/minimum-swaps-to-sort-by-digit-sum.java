import java.util.*;

class Solution {
    // Helper function to calculate digit sum
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Store elements with their digit sum, value, and original index
        class Item implements Comparable<Item> {
            int val, sum, idx;
            Item(int val, int sum, int idx) {
                this.val = val;
                this.sum = sum;
                this.idx = idx;
            }
            public int compareTo(Item other) {
                if (this.sum != other.sum) {
                    return Integer.compare(this.sum, other.sum);
                }
                return Integer.compare(this.val, other.val);
            }
        }

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(nums[i], getDigitSum(nums[i]), i);
        }

        // Sort based on digit sum, then by value
        Arrays.sort(items);

        // Find the number of cycles to determine minimum swaps
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // If element is already visited or already in the correct position
            if (visited[i] || items[i].idx == i) {
                continue;
            }

            // Count the size of the cycle
            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = items[curr].idx;
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }
}