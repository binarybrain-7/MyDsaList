class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int left = 0;
        HashSet<Character> charSet = new HashSet<>();
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // If the character is already in the set, shrink the window from the left
            while (charSet.contains(c)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            // Add the current character and update the max length
            charSet.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}