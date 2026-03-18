import java.util.*;

class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        // Filter special roads that are actually useful
        List<int[]> filteredRoads = new ArrayList<>();
        for (int[] r : specialRoads) {
            int manhattan = Math.abs(r[2] - r[0]) + Math.abs(r[3] - r[1]);
            if (r[4] < manhattan) {
                filteredRoads.add(r);
            }
        }

        // Distance map to store min cost to reach a specific point "x,y"
        Map<String, Integer> dists = new HashMap<>();
        // Priority Queue: {cost, x, y}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{0, start[0], start[1]});
        dists.put(start[0] + "," + start[1], 0);

        int minFinalCost = Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]);

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int x = curr[1];
            int y = curr[2];

            if (d > dists.getOrDefault(x + "," + y, Integer.MAX_VALUE)) continue;

            // Option 1: Go directly to target from here
            minFinalCost = Math.min(minFinalCost, d + Math.abs(target[0] - x) + Math.abs(target[1] - y));

            // Option 2: Take a special road
            for (int[] road : filteredRoads) {
                int x1 = road[0], y1 = road[1], x2 = road[2], y2 = road[3], cost = road[4];
                
                // Cost to reach the START of this special road + road's cost
                int costToReachEnd = d + Math.abs(x1 - x) + Math.abs(y1 - y) + cost;
                String endKey = x2 + "," + y2;

                if (costToReachEnd < dists.getOrDefault(endKey, Integer.MAX_VALUE)) {
                    dists.put(endKey, costToReachEnd);
                    pq.offer(new int[]{costToReachEnd, x2, y2});
                }
            }
        }

        return minFinalCost;
    }
}