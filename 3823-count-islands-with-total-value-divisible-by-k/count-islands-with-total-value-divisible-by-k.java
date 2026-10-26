class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    // Use long to avoid overflow when summing up island values
                    long islandSum = dfs(grid, i, j);
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        // Base case: out of bounds or water (0)
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0) {
            return 0;
        }

        long currentVal = grid[r][c];
        // Mark the cell as visited by setting it to 0
        grid[r][c] = 0;

        // Traverse 4-directionally
        long sum = currentVal
                 + dfs(grid, r + 1, c)
                 + dfs(grid, r - 1, c)
                 + dfs(grid, r, c + 1)
                 + dfs(grid, r, c - 1);

        return sum;
    }
}