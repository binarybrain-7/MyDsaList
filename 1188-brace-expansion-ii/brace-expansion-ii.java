import java.util.*;

class Solution {
    private int index = 0;

    public List<String> braceExpansionII(String expression) {
        index = 0;
        Set<String> resultSet = parse(expression);
        
        // Convert set to a sorted list as required by the problem
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private Set<String> parse(String expr) {
        Set<String> res = new HashSet<>();
        Set<String> cur = new HashSet<>();
        cur.add(""); // Base string for concatenation

        while (index < expr.length() && expr.charAt(index) != '}') {
            char ch = expr.charAt(index);

            if (ch == '{') {
                index++; // Skip '{'
                Set<String> next = parse(expr);
                index++; // Skip '}'
                cur = product(cur, next);
            } else if (ch == ',') {
                res.addAll(cur);
                cur = new HashSet<>();
                cur.add("");
                index++; // Skip ','
            } else {
                // Single letter character
                Set<String> next = new HashSet<>();
                next.add(String.valueOf(ch));
                cur = product(cur, next);
                index++;
            }
        }

        res.addAll(cur);
        return res;
    }

    // Computes the Cartesian product of two sets of strings
    private Set<String> product(Set<String> setA, Set<String> setB) {
        Set<String> result = new HashSet<>();
        for (String a : setA) {
            for (String b : setB) {
                result.add(a + b);
            }
        }
        return result;
    }
}