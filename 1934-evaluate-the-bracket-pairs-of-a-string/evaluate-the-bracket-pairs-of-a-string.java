import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Store knowledge in a HashMap for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();
        int i = 0;

        // Step 2: Iterate through the string s
        while (i < n) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                // Collect the key inside brackets
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                
                // Append value if present, otherwise append "?"
                result.append(map.getOrDefault(key, "?"));
                
                // Move index past ')'
                i = j + 1;
            } else {
                result.append(ch);
                i++;
            }
        }

        return result.toString();
    }
}