class Solution {
    public long maxAlternatingSum(int[] nums) {
        // Square or take absolute values since signs don't matter for squares,
        // but sorting by absolute value or normal value works. Let's map elements to their squares.
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squares[i] = val * val;
        }
        
        java.util.Arrays.sort(squares);
        
        long ans = 0;
        int low = 0;
        int high = n - 1;
        boolean add = true; // Alternating flag
        
        // Greedily pick the largest remaining elements for positive positions, 
        // and smallest remaining elements for negative positions.
        // Or more simply: the largest n/2 elements are added, and the rest are subtracted.
        // Let's use a two-pointer approach from the sorted array:
        // The largest elements go to even indices (+), smallest to odd indices (-).
        
        // Let's do it cleanly:
        long score = 0;
        int l = 0, r = n - 1;
        // We want alternating: largest, smallest, second largest, second smallest... wait:
        // Score = arr[0]^2 - arr[1]^2 + arr[2]^2 - arr[3]^2 + ...
        // Notice that arr[0] is added, arr[1] is subtracted, arr[2] is added, arr[3] is subtracted.
        // To maximize this, we want the sequence of sorted squares arranged so that 
        // large values are at even positions and small values at odd positions.
        
        // If we sort the array ascending: squares[0] <= squares[1] <= ... <= squares[n-1]
        // We can assign:
        // odd indices get the smallest elements: squares[0], squares[1], ...
        // even indices get the largest elements: squares[n-1], squares[n-2], ...
        
        long total = 0;
        int idx = n - 1;
        
        // Even indices (0, 2, 4...) get the largest numbers
        // Odd indices (1, 3, 5...) get the smallest numbers
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                total += squares[idx--];
            } else {
                total += squares[l++]; // subtracted? Wait, if we pull from the smallest for odd:
            }
        }
        
        // Let's re-verify:
        // If i is even (0, 2, 4...), it contributes positively: +squares[idx--] (from the largest side)
        // If i is odd (1, 3, 5...), it contributes negatively: -squares[l++] (from the smallest side)
        
        score = 0;
        l = 0;
        idx = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                score += squares[idx--];
            } else {
                score -= squares[l++];
            }
        }
        
        return score;
    }
}