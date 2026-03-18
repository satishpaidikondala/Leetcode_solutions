import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') initialZeros++;
        }

        if (initialZeros == 0) return 0;

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        queue.offer(initialZeros);
        dist[initialZeros] = 0;

        // Optimization: Use two sets/lists to keep track of unvisited even/odd indices
        // to avoid O(N*K) complexity.
        TreeSet<Integer> unvisitedEven = new TreeSet<>();
        TreeSet<Integer> unvisitedOdd = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            if (i == initialZeros) continue;
            if (i % 2 == 0) unvisitedEven.add(i);
            else unvisitedOdd.add(i);
        }

        while (!queue.isEmpty()) {
            int z = queue.poll();
            int d = dist[z];

            // Calculate the range of possible new zeros [minZ, maxZ]
            // We want to maximize i to get minZ: i = min(z, k)
            // But we must also ensure k - i <= n - z => i >= k - n + z
            int minI = Math.max(0, k - (n - z));
            int maxI = Math.min(z, k);

            // z' = z + k - 2*i
            // When i is max, z' is min. When i is min, z' is max.
            int minZ = z + k - 2 * maxI;
            int maxZ = z + k - 2 * minI;

            TreeSet<Integer> targetSet = (minZ % 2 == 0) ? unvisitedEven : unvisitedOdd;
            
            // Get all unvisited values in [minZ, maxZ]
            Integer nextZ = targetSet.ceiling(minZ);
            while (nextZ != null && nextZ <= maxZ) {
                if (nextZ == 0) return d + 1;
                
                dist[nextZ] = d + 1;
                queue.offer(nextZ);
                
                // Remove from unvisited to ensure O(N) total transitions
                targetSet.remove(nextZ);
                nextZ = targetSet.ceiling(minZ);
            }
        }

        return -1;
    }
}