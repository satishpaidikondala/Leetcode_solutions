class Solution {
    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers to get A ^ B
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }

        // Step 2: Find the rightmost set bit in diff
        // This bit represents a position where A and B differ
        // We use (diff & -diff) to get the lowest set bit
        int bit = diff & -diff;

        int[] result = new int[2];
        // Step 3: Partition the numbers into two groups based on the bit
        for (int num : nums) {
            if ((num & bit) == 0) {
                // Group where the bit is not set
                result[0] ^= num;
            } else {
                // Group where the bit is set
                result[1] ^= num;
            }
        }

        return result;
    }
}