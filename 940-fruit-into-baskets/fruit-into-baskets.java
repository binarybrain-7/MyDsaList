class Solution {
    public int totalFruit(int[] fruits) {
        int l = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int r = 0; r < fruits.length; r++) {
            // Add current fruit to the basket
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            
            // If we have more than 2 types of fruit, shrink from the left
            while (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            
            // Update the maximum length found so far
            maxLen = Math.max(maxLen, r - l + 1);
        }
        
        return maxLen;
    }
}