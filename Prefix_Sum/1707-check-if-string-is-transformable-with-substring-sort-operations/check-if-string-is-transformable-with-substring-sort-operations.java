import java.util.*;

class Solution {
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        
        // Step 1: Store the positions of each digit (0-9) in s using queues
        Queue<Integer>[] posQueues = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            posQueues[i] = new LinkedList<>();
        }
        
        for (int i = 0; i < n; i++) {
            posQueues[s.charAt(i) - '0'].add(i);
        }
        
        // Step 2: Iterate through t and check if we can "pull" the required digit from s
        for (int i = 0; i < n; i++) {
            int targetDigit = t.charAt(i) - '0';
            
            // If s doesn't have this digit anymore, transformation is impossible
            if (posQueues[targetDigit].isEmpty()) {
                return false;
            }
            
            // Look at the index of the first available 'targetDigit' in s
            int currentPosInS = posQueues[targetDigit].peek();
            
            // KEY LOGIC: 
            // A digit can move left past any digit GREATER than itself via sorting.
            // A digit CANNOT move left past any digit SMALLER than itself.
            for (int smallerDigit = 0; smallerDigit < targetDigit; smallerDigit++) {
                if (!posQueues[smallerDigit].isEmpty() && 
                    posQueues[smallerDigit].peek() < currentPosInS) {
                    // There's a smaller digit to the left of our targetDigit in s.
                    // It acts as a "blocker."
                    return false;
                }
            }
            
            // If no smaller digit was blocking it, "pull" it to the current position
            posQueues[targetDigit].poll();
        }
        
        return true;
    }
}