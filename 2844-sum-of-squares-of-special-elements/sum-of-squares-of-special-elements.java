class Solution {
    public int sumOfSquares(int[] nums) {
        int sum =0;
        int n =nums.length;
        int arr[]= new int[n+1];
        int k=0;
        for(int j=1;j<arr.length;j++){
            arr[j]=nums[k];
            k++;
        }
        for(int i =1;i<arr.length;i++){
if(n%i==0){
    int sq=arr[i]*arr[i];
    sum+=sq;
}
        }
    return sum;
    }
}