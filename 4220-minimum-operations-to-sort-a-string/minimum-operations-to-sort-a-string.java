class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // 1. Check if already sorted
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) return 0;
        
        // 2. Length 2 that is not sorted cannot be fixed
        if (n == 2) return -1;
        
        char smallest = s.charAt(0);
        char largest = s.charAt(0);
        for (int i = 1; i < n; i++) {
            smallest = (char) Math.min(smallest, s.charAt(i));
            largest = (char) Math.max(largest, s.charAt(i));
        }
        
        // 3. Check if smallest is at index 0 (proper prefix constraint) or largest is at n-1
        boolean minAtStart = (s.charAt(0) == smallest);
        boolean maxAtEnd = (s.charAt(n - 1) == largest);
        
        if (minAtStart || maxAtEnd) {
            return 1;
        }
        
        // Check if smallest exists anywhere in any proper prefix and largest in any proper suffix
        boolean hasMinInProperPrefix = false;
        boolean hasMaxInProperSuffix = false;
        
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == smallest) hasMinInProperPrefix = true;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == largest) hasMaxInProperSuffix = true;
        }
        
        if (!hasMinInProperPrefix && !hasMaxInProperSuffix) {
            return 3;
        }
        
        return 2;
    }
}