import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // 1. Calculate minimum removals needed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, leftRem, rightRem, '\0', new StringBuilder(), result);
        return result;
    }

    private void backtrack(String s, int index, int openCount, int leftRem, int rightRem, char lastRemoved, StringBuilder path, List<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && openCount == 0) {
                result.add(path.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int len = path.length();

        // Option 1: Remove current character
        // Only remove if it's not a duplicate keep-step after a removal of the same character
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, openCount, leftRem - 1, rightRem, '(', path, result);
        }
        if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, openCount, leftRem, rightRem - 1, ')', path, result);
        }

        // Option 2: Keep current character
        // Skip keeping if the previous step was a removal of the SAME character (forces consecutive removals first)
        if (currentChar == lastRemoved) {
            return;
        }

        if (currentChar == '(') {
            path.append(currentChar);
            backtrack(s, index + 1, openCount + 1, leftRem, rightRem, '\0', path, result);
            path.setLength(len);
        } else if (currentChar == ')') {
            if (openCount > 0) {
                path.append(currentChar);
                backtrack(s, index + 1, openCount - 1, leftRem, rightRem, '\0', path, result);
                path.setLength(len);
            }
        } else {
            path.append(currentChar);
            backtrack(s, index + 1, openCount, leftRem, rightRem, '\0', path, result);
            path.setLength(len);
        }
    }
}