import java.util.*;

class Solution {
    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        
        int maxFreq = 0;
        int ans = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            int oldFreq = freq.getOrDefault(num, 0);
            int newFreq = oldFreq + 1;
            freq.put(num, newFreq);
            
            // update count map
            if (oldFreq > 0) {
                count.put(oldFreq, count.get(oldFreq) - 1);
                if (count.get(oldFreq) == 0) {
                    count.remove(oldFreq);
                }
            }
            
            count.put(newFreq, count.getOrDefault(newFreq, 0) + 1);
            
            maxFreq = Math.max(maxFreq, newFreq);
            
            int len = i + 1;
            
            // Case 1
            if (maxFreq == 1) {
                ans = len;
            }
            // Case 2
            else if (count.getOrDefault(1, 0) == 1 &&
                     count.get(maxFreq) * maxFreq + 1 == len) {
                ans = len;
            }
            // Case 3
            else if (count.getOrDefault(maxFreq, 0) == 1 &&
                     count.getOrDefault(maxFreq - 1, 0) * (maxFreq - 1) + maxFreq == len) {
                ans = len;
            }
        }
        
        return ans;
    }
}