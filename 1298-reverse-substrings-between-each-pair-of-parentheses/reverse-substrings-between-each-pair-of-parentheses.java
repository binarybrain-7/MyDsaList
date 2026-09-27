class Solution {
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == ')') {
                // Collect characters inside current parentheses
                StringBuilder temp = new StringBuilder();
                while (sb.charAt(sb.length() - 1) != '(') {
                    temp.append(sb.charAt(sb.length() - 1));
                    sb.deleteCharAt(sb.length() - 1);
                }
                // Remove the matching '('
                sb.deleteCharAt(sb.length() - 1);
                
                // Append the reversed inner string back
                sb.append(temp);
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}