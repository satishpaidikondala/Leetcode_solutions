import java.util.*;

class Solution {
    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) {
            list.add((double) card);
        }
        return solve(list);
    }

    private boolean solve(List<Double> nums) {
        // Base Case: If only one number is left, check if it's 24
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < 1e-6;
        }

        // Pick two numbers from the list
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                // Create a new list for the next recursive step
                List<Double> nextList = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) {
                        nextList.add(nums.get(k));
                    }
                }

                // Try all 4 operations between nums.get(i) and nums.get(j)
                for (double result : getPossibleResults(nums.get(i), nums.get(j))) {
                    nextList.add(result);
                    if (solve(nextList)) return true;
                    // Backtrack
                    nextList.remove(nextList.size() - 1);
                }
            }
        }
        return false;
    }

    private List<Double> getPossibleResults(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        if (Math.abs(b) > 1e-6) res.add(a / b);
        if (Math.abs(a) > 1e-6) res.add(b / a);
        return res;
    }
}