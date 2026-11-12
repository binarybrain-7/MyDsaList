class Solution {
    public int maxScore(int[] cardPoints, int k) {
     int n = cardPoints.length;
 int curr =0;
 int ans =0;
 int left =k-1;
 int right =n-1;
 for(int i=0;i<k;i++){
    curr+=cardPoints[i];

 }
 ans=curr;
 while(k!=0){
 curr-=cardPoints[left];
 curr+=cardPoints[right];
 left--;
 right--;
 k--;
ans=Math.max(ans,curr);
 }
return ans;
    }
}