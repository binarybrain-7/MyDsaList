class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Check if it's impossible
        if ((long) num * 9 < sum || sum < 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int currentSum = sum;
        
        for (int i = 0; i < num; i++) {
            int remainingPositions = num - i - 1;
            // Try digits from 9 down to 0 to get the lexicographically largest result
            for (int d = 9; d >= 0; d--) {
                if (currentSum - d >= 0 && (long) remainingPositions * 9 >= currentSum - d) {
                    sb.append(d);
                    currentSum -= d;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}