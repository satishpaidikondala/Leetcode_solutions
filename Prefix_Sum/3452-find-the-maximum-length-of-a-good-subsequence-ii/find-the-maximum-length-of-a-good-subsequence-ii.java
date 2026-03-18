import java.util.*;

class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        // dp[val][j] stores the max length ending with 'val' and 'j' changes
        // Using a Map for values because nums[i] can be up to 10^9
        Map<Integer, int[]> dp = new HashMap<>();
        
        // maxL[j] stores the overall max length found so far with exactly 'j' changes
        int[] maxL = new int[k + 1];
        
        for (int x : nums) {
            if (!dp.containsKey(x)) {
                dp.put(x, new int[k + 1]);
            }
            int[] currentValDP = dp.get(x);
            
            // We iterate backwards from k to 0 to update states
            for (int j = k; j >= 0; j--) {
                // Option 1: Append to a subsequence ending with the SAME value
                currentValDP[j] = currentValDP[j] + 1;
                
                // Option 2: Append to a subsequence ending with a DIFFERENT value
                if (j > 0) {
                    currentValDP[j] = Math.max(currentValDP[j], maxL[j - 1] + 1);
                }
                
                // Update the global max for 'j' changes
                maxL[j] = Math.max(maxL[j], currentValDP[j]);
            }
        }
        
        int result = 0;
        for (int len : maxL) {
            result = Math.max(result, len);
        }
        return result;
    }
}