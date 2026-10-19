class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int count =0;
        int lessThen=0;
        
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target ){
         count++;
            }else if(nums[i]<target){
                lessThen++;
            }
        }
        for(int i=lessThen;i<count+lessThen;i++){
            ans.add(i);
        }

        return ans;
    }
}