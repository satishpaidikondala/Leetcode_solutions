import java.util.*;

class Solution {
    // Memoization map to store the minimum days for a specific number of oranges
    Map<Integer, Integer> memo = new HashMap<>();

    public int minDays(int n) {
        // Base cases
        if (n <= 1) return n;
        
        // If we've already calculated this n, return it
        if (memo.containsKey(n)) return memo.get(n);

        /* Option 1: Eat n%2 oranges one by one, then use the n/2 rule.
           Option 2: Eat n%3 oranges one by one, then use the n/3 rule.
           We take the minimum of these two paths.
        */
        int res = 1 + Math.min(n % 2 + minDays(n / 2), 
                               n % 3 + minDays(n / 3));

        memo.put(n, res);
        return res;
    }
}