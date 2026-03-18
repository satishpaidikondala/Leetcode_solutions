import java.util.*;

class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[] prefix = new int[n + 1];
        
        // Build prefix bitmask
        for (int i = 0; i < n; i++) {
            int bit = 1 << (s.charAt(i) - 'a');
            prefix[i + 1] = prefix[i] ^ bit;
        }
        
        List<Boolean> result = new ArrayList<>();
        
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2];
            
            int mask = prefix[r + 1] ^ prefix[l];
            int oddCount = Integer.bitCount(mask);
            
            result.add((oddCount / 2) <= k);
        }
        
        return result;
    }
}