class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int count =0;
        if(n==1){
            return true;
        }
        if(n==2 && bits[0]==1){
            return false;
        }
        for(int i=0;i<n-1;i++){
            if(bits[i]==1 && count==0){
                count++;
            }else if(count!=0){
            count--;
           }
        }
        if(count==0){
            return true;
        }
       return false;
       
    }
}