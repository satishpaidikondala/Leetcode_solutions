import java.util.*;

class Solution {
    private TreeSet<int[]> left = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    private TreeSet<int[]> right = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    private long currentSum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int m = k - 1; // We need to pick m more elements
        
        // Initial window from index 1 to dist + 1
        for (int i = 1; i <= dist + 1; i++) {
            add(new int[]{nums[i], i}, m);
        }
        
        long minCost = (long) nums[0] + currentSum;
        
        // Slide the window
        for (int i = dist + 2; i < n; i++) {
            remove(new int[]{nums[i - dist - 1], i - dist - 1}, m);
            add(new int[]{nums[i], i}, m);
            minCost = Math.min(minCost, (long) nums[0] + currentSum);
        }
        
        return minCost;
    }

    private void add(int[] val, int m) {
        left.add(val);
        currentSum += val[0];
        if (left.size() > m) {
            int[] largest = left.pollLast();
            currentSum -= largest[0];
            right.add(largest);
        }
    }

    private void remove(int[] val, int m) {
        if (left.contains(val)) {
            left.remove(val);
            currentSum -= val[0];
            if (!right.isEmpty()) {
                int[] smallest = right.pollFirst();
                currentSum += smallest[0];
                left.add(smallest);
            }
        } else {
            right.remove(val);
        }
    }
}