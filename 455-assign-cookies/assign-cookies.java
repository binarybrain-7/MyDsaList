class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int n = g.length;
        int m = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
int count =0;
        int low1 =0;
        int low2 = 0;
        while(low1<n && low2<m){
            if(g[low1]<=s[low2]){
                low1++;
                low2++;
                count++;
            }else if(g[low1]>s[low2]){
                low2++;
            }
        }
return count;
    }
}