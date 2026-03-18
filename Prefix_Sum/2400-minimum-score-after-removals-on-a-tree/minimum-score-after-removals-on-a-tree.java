import java.util.*;

class Solution {
    int[] subXor;
    int[] tin, tout;
    int timer;
    List<List<Integer>> adj;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        subXor = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;

        dfs(0, -1, nums);

        int totalXor = subXor[0];
        int minScore = Integer.MAX_VALUE;

        // Iterate through all pairs of edges (represented by the child node of the edge)
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1, x2, x3;
                
                if (isAncestor(i, j)) { // i is ancestor of j
                    x1 = subXor[j];
                    x2 = subXor[i] ^ subXor[j];
                    x3 = totalXor ^ subXor[i];
                } else if (isAncestor(j, i)) { // j is ancestor of i
                    x1 = subXor[i];
                    x2 = subXor[j] ^ subXor[i];
                    x3 = totalXor ^ subXor[j];
                } else { // separate branches
                    x1 = subXor[i];
                    x2 = subXor[j];
                    x3 = totalXor ^ subXor[i] ^ subXor[j];
                }

                int max = Math.max(x1, Math.max(x2, x3));
                int min = Math.min(x1, Math.min(x2, x3));
                minScore = Math.min(minScore, max - min);
                
                if (minScore == 0) return 0; // Optimization
            }
        }

        return minScore;
    }

    private void dfs(int u, int p, int[] nums) {
        tin[u] = ++timer;
        subXor[u] = nums[u];
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, nums);
                subXor[u] ^= subXor[v];
            }
        }
        tout[u] = timer;
    }

    private boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }
}