class Solution {
    static final int MOD = 1_000_000_007;

    // Segment tree nodes store:
    // sum: sum of squares of distinct counts in the range
    // lazy: lazy propagation value for linear count increments
    long[] sumTree;
    long[] lazy;
    long[] countTree; // stores the sum of linear counts in the range

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumTree = new long[4 * n];
        lazy = new long[4 * n];
        countTree = new long[4 * n];

        java.util.Map<Integer, Integer> lastOccurrence = new java.util.HashMap<>();
        long totalSum = 0;
        long currentTotalSquares = 0;

        for (int j = 0; j < n; j++) {
            int val = nums[j];
            int k = lastOccurrence.getOrDefault(val, -1);
            lastOccurrence.put(val, j);

            // Range update: add 1 to counts for starting indices from k+1 to j
            if (k + 1 <= j) {
                update(1, 0, n - 1, k + 1, j);
            }

            // Query the sum of squares of all subarrays ending at j (which spans from index 0 to j)
            currentTotalSquares = querySum(1, 0, n - 1, 0, j);
            totalSum = (totalSum + currentTotalSquares) % MOD;
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            applyLazy(2 * node, l, mid, lazy[node]);
            applyLazy(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void applyLazy(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // Using identity: (C + val)^2 = C^2 + 2*C*val + val^2 * len
        sumTree[node] = (sumTree[node] + 2 * val * countTree[node] + val * val * len) % MOD;
        countTree[node] = (countTree[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            applyLazy(node, l, r, 1);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) update(2 * node, l, mid, ql, qr);
        if (qr > mid) update(2 * node + 1, mid + 1, r, ql, qr);
        
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        countTree[node] = (countTree[2 * node] + countTree[2 * node + 1]) % MOD;
    }

    private long querySum(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return sumTree[node];
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        long res = 0;
        if (ql <= mid) res = (res + querySum(2 * node, l, mid, ql, qr)) % MOD;
        if (qr > mid) res = (res + querySum(2 * node + 1, mid + 1, r, ql, qr)) % MOD;
        return res;
    }
}