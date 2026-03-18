import java.util.*;

class Solution {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        
        // 1. Build Graph and count degrees
        for (int[] pair : pairs) {
            int u = pair[0];
            int v = pair[1];
            adj.computeIfAbsent(u, k -> new ArrayDeque<>()).push(v);
            outDegree.put(u, outDegree.getOrDefault(u, 0) + 1);
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }
        
        // 2. Find the starting node
        int startNode = pairs[0][0];
        for (int node : outDegree.keySet()) {
            if (outDegree.get(node) - inDegree.getOrDefault(node, 0) == 1) {
                startNode = node;
                break;
            }
        }
        
        // 3. Hierholzer's DFS
        List<Integer> path = new ArrayList<>();
        dfs(startNode, adj, path);
        
        // 4. Convert nodes to edge pairs (path is reversed)
        int[][] result = new int[pairs.length][2];
        for (int i = path.size() - 1, j = 0; i > 0; i--, j++) {
            result[j][0] = path.get(i);
            result[j][1] = path.get(i - 1);
        }
        
        return result;
    }
    
    private void dfs(int u, Map<Integer, Deque<Integer>> adj, List<Integer> path) {
        Deque<Integer> neighbors = adj.get(u);
        while (neighbors != null && !neighbors.isEmpty()) {
            int v = neighbors.pop();
            dfs(v, adj, path);
        }
        path.add(u);
    }
}