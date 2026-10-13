class Solution {
    public boolean lemonadeChange(int[] bills) {
        int c1 = 0; // count of $5 bills
        int c2 = 0; // count of $10 bills
        
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                c1++;
            } else if (bills[i] == 10) {
                if (c1 > 0) {
                    c1--;
                    c2++;
                } else {
                    return false;
                }
            } else { // bill is $20
                // Greedily prefer giving one $10 and one $5
                if (c2 > 0 && c1 > 0) {
                    c2--;
                    c1--;
                } else if (c1 >= 3) { // Otherwise, give three $5s
                    c1 -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}