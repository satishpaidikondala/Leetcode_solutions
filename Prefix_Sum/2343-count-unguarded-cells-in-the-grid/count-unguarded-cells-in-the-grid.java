import java.util.*;

class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        // mark guards
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 1;
        }

        // mark walls
        for (int[] w : walls) {
            grid[w[0]][w[1]] = 2;
        }

        // directions: up, down, left, right
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        // simulate each guard
        for (int[] g : guards) {
            int r = g[0], c = g[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                while (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (grid[nr][nc] == 2 || grid[nr][nc] == 1) break;

                    if (grid[nr][nc] == 0) {
                        grid[nr][nc] = 3; // mark guarded
                    }

                    nr += d[0];
                    nc += d[1];
                }
            }
        }

        // count unguarded
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) count++;
            }
        }

        return count;
    }
}