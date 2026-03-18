/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        // Base case: if the range is invalid, return null
        if (left > right) return null;

        // Find the index of the maximum value in the current range
        int maxIdx = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Create the root with the max value
        TreeNode root = new TreeNode(nums[maxIdx]);

        // Recursively build the subtrees
        root.left = build(nums, left, maxIdx - 1);
        root.right = build(nums, maxIdx + 1, right);

        return root;
    }
}