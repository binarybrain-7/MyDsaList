class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int reversedAlphabetPos = 'z' - s.charAt(i) + 1;
            int stringPos = i + 1; // 1-indexed string position
            
            sum += reversedAlphabetPos * stringPos;
        }
        
        return sum;
    }
}