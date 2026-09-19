class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the x-coordinate of the closest point on the rectangle
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        
        // Find the y-coordinate of the closest point on the rectangle
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the squared distance between the circle's center and the closest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;
        int squaredDistance = dx * dx + dy * dy;
        
        // Check if the distance is within the radius
        return squaredDistance <= radius * radius;
    }
}