import java.util.*;

class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Step 1: Initialize the reachability matrix
        boolean[][] isPrereq = new boolean[numCourses][numCourses];
        
        // Step 2: Fill direct prerequisites
        for (int[] pre : prerequisites) {
            isPrereq[pre[0]][pre[1]] = true;
        }
        
        // Step 3: Floyd-Warshall for transitive closure
        // Check if course i is a prereq of course j through an intermediate course k
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    if (isPrereq[i][k] && isPrereq[k][j]) {
                        isPrereq[i][j] = true;
                    }
                }
            }
        }
        
        // Step 4: Answer the queries
        List<Boolean> result = new ArrayList<>();
        for (int[] q : queries) {
            result.add(isPrereq[q[0]][q[1]]);
        }
        
        return result;
    }
}