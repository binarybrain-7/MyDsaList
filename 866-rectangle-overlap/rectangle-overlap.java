class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Overlap along X-axis
        boolean xOverlap = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]);
        
        // Overlap along Y-axis
        boolean yOverlap = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
        
        return xOverlap && yOverlap;
    }
}