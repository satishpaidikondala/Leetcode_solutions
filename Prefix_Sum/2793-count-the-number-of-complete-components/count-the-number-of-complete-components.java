import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] stats = new int[2]; // [nodeCount, edgeCount]
                dfs(i, adj, visited, stats);
                
                int v = stats[0];
                int e = stats[1];
                
                // In a complete graph, every node has degree (v - 1)
                // Total edges in a complete component should be v * (v - 1)
                // Since our DFS counts each undirected edge twice, we compare e with v*(v-1)
                if (e == v * (v - 1)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] visited, int[] stats) {
        visited[u] = true;
        stats[0]++; // Increment node count
        stats[1] += adj.get(u).size(); // Add the degree of this node to edge count

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, adj, visited, stats);
            }
        }
    }
}