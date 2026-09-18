import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrence for each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        // Step 2: Find all minimal valid intervals
        List<int[]> validIntervals = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;

            int start = first[c];
            int end = last[c];
            boolean isValid = true;

            for (int i = start; i <= end; i++) {
                int ch = s.charAt(i) - 'a';
                // If a character inside starts before our initial start, 
                // this interval isn't minimal from 'start'
                if (first[ch] < start) {
                    isValid = false;
                    break;
                }
                end = Math.max(end, last[ch]);
            }

            if (isValid) {
                validIntervals.add(new int[]{start, end});
            }
        }

        // Step 3: Greedily pick non-overlapping intervals sorted by end position
        validIntervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}