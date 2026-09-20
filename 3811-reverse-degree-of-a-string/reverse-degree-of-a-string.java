class Solution {
    public int reverseDegree(String s) {
        int n = s.length();
        int sum=0;
        int j =1;
        int arr[]=new int [1000];
        for(int i=0;i<n;i++){
            arr[i]='z'-s.charAt(i);
            sum+=(arr[i]+1)*j;
            j++;
        }
return sum;
    }
}