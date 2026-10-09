class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int open = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                open++;
            } else {
                // We found ')'
                // Check if the next character is also ')'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Skip the next character since it forms a pair ))
                } else {
                    // Missing a second ')', so we need 1 insertion
                    ans++;
                }
                
                // Now match with an open parenthesis if available
                if (open > 0) {
                    open--;
                } else {
                    // No open parenthesis available, we need to insert '('
                    ans++;
                }
            }
        }
        
        // Each remaining open parenthesis requires 2 closing parentheses
        ans += open * 2;
        
        return ans;
    }
}