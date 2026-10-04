class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum required open brackets '('
        int maxOpen = 0; // Maximum possible open brackets '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*'
                // '*' can be ')', empty "", or '('
                minOpen--; // If treated as ')'
                maxOpen++; // If treated as '('
            }

            // More ')' than '(' is possible even with wildcards
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative since we can't have negative open count
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // String is valid if minOpen balance can reach 0 at the end
        return minOpen == 0;
    }
}