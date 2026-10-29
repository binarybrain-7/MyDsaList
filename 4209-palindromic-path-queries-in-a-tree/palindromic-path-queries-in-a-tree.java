import java.util.*;

class Solution {
    static class SegmentTree {
        int n;
        int[] tree;
        int[] lazy;

        public SegmentTree(int n, int[] initialVals) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            build(1, 0, n - 1, initialVals);
        }

        private void build(int node, int start, int end, int[] vals) {
            if (start == end) {
                tree[node] = vals[start];
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid, vals);
            build(2 * node + 1, mid + 1, end, vals);
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        private void push(int node) {
            if (lazy[node] != 0) {
                int leftChild = 2 * node;
                int rightChild = 2 * node + 1;
                
                tree[leftChild] ^= lazy[node];
                lazy[leftChild] ^= lazy[node];
                
                tree[rightChild] ^= lazy[node];
                lazy[rightChild] ^= lazy[node];
                
                lazy[node] = 0;
            }
        }

        public void updatePoint(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            push(node);
            int mid = (start + end) / 2;
            if (idx <= mid) {
                updatePoint(2 * node, start, mid, idx, val);
            } else {
                updatePoint(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        public int queryRange(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];
            push(node);
            int mid = (start + end) / 2;
            return queryRange(2 * node, start, mid, l, r) ^ queryRange(2 * node + 1, mid + 1, end, l, r);
        }
    }

    private List<List<Integer>> adj;
    private int[] parent, depth, heavy, head, pos;
    private int curPos;

    private int dfs1(int u, int p) {
        int size = 1;
        int maxSubtree = 0;
        parent[u] = p;
        for (int v : adj.get(u)) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                int subtree = dfs1(v, u);
                if (subtree > maxSubtree) {
                    maxSubtree = subtree;
                    heavy[u] = v;
                }
                size += subtree;
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        curPos = 0;

        dfs1(0, 0);
        dfs2(0, 0);

        int[] initialVals = new int[n];
        char[] charArr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            initialVals[pos[i]] = 1 << (charArr[i] - 'a');
        }

        SegmentTree st = new SegmentTree(n, initialVals);
        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                charArr[u] = newChar;
                st.updatePoint(1, 0, n - 1, pos[u], 1 << (newChar - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] > depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }
                    pathMask ^= st.queryRange(1, 0, n - 1, pos[head[v]], pos[v]);
                    v = parent[head[v]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }
                pathMask ^= st.queryRange(1, 0, n - 1, pos[u], pos[v]);

                if (Integer.bitCount(pathMask) <= 1) {
                    result.add(true);
                } else {
                    result.add(false);
                }
            }
        }

        return result;
    }
}