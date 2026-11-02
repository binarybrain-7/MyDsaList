class Solution {
    public int pivotIndex(int[] nums) {
        int left =0;
        int pivot=0;
        int n = nums.length;
        int preSum=0;
        for(int i=1;i<n;i++){
            preSum+=nums[i];
        }
        for(int i=1;i<n;i++){
if(left==preSum){
    return pivot;
}else if(preSum>left){
   left+=nums[pivot];
    pivot++;
   preSum= preSum-nums[pivot];
}else{
    left+=nums[pivot];
    pivot++;
   preSum= preSum-nums[pivot];
}
        }
        if(pivot==n-1 && left != preSum){
            return -1;
        }
        return pivot;
    }
}