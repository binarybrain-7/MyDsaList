class Solution {
    public int maxDepth(String s) {
    int count =0;
    int max =0;
    int n =s.length();
    for(int i =0;i<n;i++){
        if(s.charAt(i)=='('){
            count++;
        }
if (s.charAt(i)==')'){
    max = Math.max(count,max);
    count--;
}
    }
    return max;
    }
}