import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: 0: North, 1: East, 2: South, 3: West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // Store obstacles in a HashSet for O(1) lookup
        // We use a string "x,y" as the key
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        int x = 0, y = 0;
        int direction = 0; // Starting North
        int maxDistSq = 0;
        
        for (int cmd : commands) {
            if (cmd == -1) {
                // Turn Right
                direction = (direction + 1) % 4;
            } else if (cmd == -2) {
                // Turn Left
                direction = (direction + 3) % 4;
            } else {
                // Move forward 'cmd' units
                for (int i = 0; i < cmd; i++) {
                    int nextX = x + dirs[direction][0];
                    int nextY = y + dirs[direction][1];
                    
                    // Check if the next step is an obstacle
                    if (!obstacleSet.contains(nextX + "," + nextY)) {
                        x = nextX;
                        y = nextY;
                        // Update max distance squared
                        maxDistSq = Math.max(maxDistSq, x * x + y * y);
                    } else {
                        // Hit an obstacle, stop moving for this command
                        break;
                    }
                }
            }
        }
        
        return maxDistSq;
    }
}