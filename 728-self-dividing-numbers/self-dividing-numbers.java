class Solution {
    public static boolean check(int n) {
        int dup = n;
        if (n == 0) {
            return false;
        }
        while (n > 0) {
            int last = n % 10;
            if (last == 0) {
                return false;
            }
            if (dup % last != 0) {
                return false;
            }
            n = n / 10;
        }
        return true; // <-- Added this missing return statement!
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (check(i)) {
                ans.add(i);
            }
        }
        return ans;
    }
}