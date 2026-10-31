class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        // go[i][j][0] = target cell when arriving at or reflecting from (i, j) moving right/down?
        // Let's define go[i][j][dir] directly for simulating the entire chain from cell (i, j).
        // dir = 0: entering while moving right -> next state / final destination
        // dir = 1: entering while moving down -> next state / final destination
        
        // Instead, let's precompute the jump directly:
        // From any cell (i, j) moving in a direction, where do we land?
        // Actually, it's easier to write a helper function to simulate the path given a starting position and direction.
        
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        // Since we can just simulate the reflection chain on-the-fly or precompute it properly:
        // Let's precompute transition(i, j, dir): 
        // dir = 0 (moving right), dir = 1 (moving down)
        int[][][] nextPos = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 2; d++) {
                    int r = i, c = j, curDir = d;
                    // If the starting cell itself is a mirror or we step into one:
                    // The problem says: "If the robot attempts to move into a mirror cell, it is reflected before entering that cell"
                    // Let's simulate step by step:
                    while (true) {
                        int nr = r + (curDir == 1 ? 1 : 0);
                        int nc = c + (curDir == 0 ? 1 : 0);

                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                            nextPos[i][j][d] = -1; // Out of bounds
                            break;
                        }

                        if (grid[nr][nc] == 0) {
                            nextPos[i][j][d] = nr * n + nc;
                            break;
                        } else {
                            // Hit a mirror at (nr, nc). Reflect:
                            // If moving right (curDir == 0), turn down (curDir = 1)
                            // If moving down (curDir == 1), turn right (curDir = 0)
                            r = nr;
                            c = nc;
                            curDir = 1 - curDir;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // Try moving Right (dir = 0)
                if (j + 1 < n) {
                    int target = nextPos[i][j][0];
                    if (target != -1) {
                        int nr = target / n;
                        int nc = target % n;
                        dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD;
                    }
                }

                // Try moving Down (dir = 1)
                if (i + 1 < m) {
                    int target = nextPos[i][j][1];
                    if (target != -1) {
                        int nr = target / n;
                        int nc = target % n;
                        dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}