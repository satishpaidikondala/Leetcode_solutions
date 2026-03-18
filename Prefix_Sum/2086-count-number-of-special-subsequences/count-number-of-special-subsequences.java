class Solution {
    public int countSpecialSubsequences(int[] nums) {
        long mod = 1_000_000_007;
        // count0: Subsequences with only 0s
        // count1: Subsequences with 0s then 1s
        // count2: Subsequences with 0s, 1s, then 2s
        long count0 = 0, count1 = 0, count2 = 0;

        for (int x : nums) {
            if (x == 0) {
                // Each existing '0' subsequence can either include this 0 or not (x2)
                // Plus, this 0 can start a new subsequence (+1)
                count0 = (2 * count0 + 1) % mod;
            } else if (x == 1) {
                // Each existing '01' subsequence can include this 1 or not (x2)
                // Plus, this 1 can be added to any existing '0' subsequence (+count0)
                count1 = (2 * count1 + count0) % mod;
            } else if (x == 2) {
                // Each existing '012' subsequence can include this 2 or not (x2)
                // Plus, this 2 can be added to any existing '01' subsequence (+count1)
                count2 = (2 * count2 + count1) % mod;
            }
        }

        return (int) count2;
    }
}