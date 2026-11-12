class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inOrder(root, ans);
        return ans;
    }
    
    private void inOrder(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        // 1. Traverse left subtree
        inOrder(node.left, ans);
        // 2. Visit root
        ans.add(node.val);
        // 3. Traverse right subtree
        inOrder(node.right, ans);
    }
}