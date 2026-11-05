class Solution {
    public static boolean check(int i, int arr[], int d){
        int n = arr.length;
        for(int j=0;j<n;j++){
if(Math.abs(i-arr[j])<=d){
return false;
}
        }
        return true;
    }
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int m = arr1.length;
        int count =0;
        for(int i=0;i<m;i++){
            if(check(arr1[i],arr2,d)){
                count++;
            }
        }
        return count;
    }
}