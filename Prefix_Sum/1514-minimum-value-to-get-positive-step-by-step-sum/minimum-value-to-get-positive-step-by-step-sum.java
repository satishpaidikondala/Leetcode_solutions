class Solution {
    public int minStartValue(int[] nums) {
        int minPrefixSum = 0; // Initialize to track the minimum prefix sum
        int sum = 0; // This variable will keep track of the cumulative sum

        // Iterate through the array to compute the cumulative sum
        // and keep track of the minimum value reached by this sum
        for (int num : nums) {
            sum += num;
            minPrefixSum = Math.min(minPrefixSum, sum);
        }

        // The minimum start value should ensure that the cumulative sum
        // never drops below 1. If minPrefixSum is negative or zero,
        // the start value must be 1 - minPrefixSum to offset this deficit.
        // Otherwise, if minPrefixSum is already positive, start with 1.
        return 1 - minPrefixSum;
    }
}