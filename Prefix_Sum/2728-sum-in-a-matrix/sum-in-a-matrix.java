import java.util.Arrays;

class Solution {
    public int matrixSum(int[][] nums) {
        int score = 0;
        int rows = nums.length;
        int cols = nums[0].length;

        // Step 1: Sort each row individually
        for (int i = 0; i < rows; i++) {
            Arrays.sort(nums[i]);
        }

        // Step 2: Iterate through columns from right to left (largest to smallest)
        for (int j = 0; j < cols; j++) {
            int maxInColumn = 0;
            for (int i = 0; i < rows; i++) {
                // Find the maximum value in the current "cut" across all rows
                maxInColumn = Math.max(maxInColumn, nums[i][j]);
            }
            // Step 3: Add the highest removed number to the score
            score += maxInColumn;
        }

        return score;
    }
}