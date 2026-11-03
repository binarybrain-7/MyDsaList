class Solution {
    public boolean isSubsequence(String s, String t) {
        int low = 0; // pointer for t
        int low2 = 0; // pointer for s
        int n = s.length();
        int m = t.length();
        StringBuilder ans = new StringBuilder();
        
        while (low < m && low2 < n) {
            if (s.charAt(low2) == t.charAt(low)) {
                ans.append(s.charAt(low2));
                low2++;
                low++;
            } else {
                low++;
            }
        }
        
        return s.equals(ans.toString());
    }
}