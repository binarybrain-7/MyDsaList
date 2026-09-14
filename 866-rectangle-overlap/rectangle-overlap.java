class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Overlap along X-axis
        boolean xOverlap = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]);
        
        // Overlap along Y-axis
        boolean yOverlap = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
        
        return xOverlap && yOverlap;
        // if (rec1[2] <= rec2[0] || // rec1 is left of rec2
        //     rec1[0] >= rec2[2] || // rec1 is right of rec2
        //     rec1[3] <= rec2[1] || // rec1 is below rec2
        //     rec1[1] >= rec2[3]) { // rec1 is above rec2
        //     return false;
        // }
        // return true;
    }
}