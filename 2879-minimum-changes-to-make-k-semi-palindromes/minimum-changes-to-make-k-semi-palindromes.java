class Solution {
    private int[][] cost;
    private Integer[][] memo;
    private int n;
    private String s;

    public int minimumChanges(String str, int k) {
        this.s = str;
        this.n = s.length();
        this.cost = new int[n][n];
        
        // Precompute cost for all substrings s[i...j]
        for (int i = 0; i < n; i++) {
            cost[i][i] = 1000000; // Single characters cannot be semi-palindromes
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(i, j);
            }
        }
        
        memo = new Integer[n][k + 1];
        return solve(0, k);
    }
    
    // DP to partition s[i...] into k parts
    private int solve(int i, int k) {
        if (n - i < 2 * k) {
            return 1000000; // Impossible state: each semi-palindrome needs length >= 2
        }
        if (k == 1) {
            return cost[i][n - 1];
        }
        if (memo[i][k] != null) {
            return memo[i][k];
        }
        
        int minChanges = 1000000;
        // Try all possible split points for the current part (minimum length 2)
        // Leave at least 2 * (k - 1) characters for the remaining (k - 1) parts
        for (int end = i + 1; n - 1 - end >= 2 * (k - 1); end++) {
            int currentCost = cost[i][end] + solve(end + 1, k - 1);
            minChanges = Math.min(minChanges, currentCost);
        }
        
        return memo[i][k] = minChanges;
    }
    
    // Minimum changes to make substring s[i...j] a semi-palindrome
    private int getMinChanges(int i, int j) {
        int len = j - i + 1;
        int minChanges = 1000000;
        
        // Check all proper divisors d of len
        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int totalChanges = 0;
                // For each group determined by divisor d
                for (int offset = 0; offset < d; offset++) {
                    // Count changes to make this group a palindrome
                    int left = i + offset;
                    int right = i + len - d + offset;
                    while (left < right) {
                        if (s.charAt(left) != s.charAt(right)) {
                            totalChanges++;
                        }
                        left += d;
                        right -= d;
                    }
                }
                minChanges = Math.min(minChanges, totalChanges);
            }
        }
        return minChanges;
    }
}