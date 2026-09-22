class Solution {
    static class Node {
        int prod;
        int[] cnt; // cnt[r] = number of prefixes in this segment with product % k == r

        Node(int k) {
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    private Node[] tree;
    private int n;
    private int k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int targetX = queries[i][3];

            // 1. Update nums[index] = val
            update(1, 0, n - 1, index, val);

            // 2. Query range [start, n - 1]
            Node resNode = query(1, 0, n - 1, start, n - 1);

            // 3. Extract count of prefix products equal to targetX modulo k
            result[i] = resNode.cnt[targetX];
        }

        return result;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node(k);
        parent.prod = (left.prod * right.prod) % k;

        // Prefixes ending inside the left segment
        for (int r = 0; r < k; r++) {
            parent.cnt[r] += left.cnt[r];
        }

        // Prefixes extending into the right segment
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            parent.cnt[newRem] += right.cnt[r];
        }

        return parent;
    }

    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            tree[node] = new Node(k);
            int rem = nums[start] % k;
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int index, int val) {
        if (start == end) {
            tree[node] = new Node(k);
            int rem = val % k;
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (index <= mid) {
            update(2 * node, start, mid, index, val);
        } else {
            update(2 * node + 1, mid + 1, end, index, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        Node leftNode = query(2 * node, start, mid, l, r);
        Node rightNode = query(2 * node + 1, mid + 1, end, l, r);

        return merge(leftNode, rightNode);
    }
}