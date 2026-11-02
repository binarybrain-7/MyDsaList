class Solution {
    public static boolean checkPrime(int n) {
        if (n <= 1) return false; // 1 and numbers <= 1 are not prime
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; 
            } 
        } 
        return true; 
    } 

    public boolean checkPrimeFrequency(int[] nums) {
        HashMap<Integer, Integer> temp = new HashMap<>(); 
        for (int i : nums) {
            temp.put(i, temp.getOrDefault(i, 0) + 1); 
        } 
        
        // Iterate through the frequencies directly
        for (int freq : temp.values()) {
            if (checkPrime(freq)) {
                return true; 
            } 
        } 
        return false; 
    } 
}