/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    // iterative
    public int closestValue(TreeNode root, double target) {
        TreeNode upper = null;
        TreeNode lower = null;
        while (root != null) {
            if (root.val == target) {
                return target;
            }
            if (root.val < target) {
                lower = root;
                root = root.right;
            } else {
                // root.val > target
                upper = root;
                root = root.left;
            }
        }
        if (lower == null || upper == null) {
            return lower != null ? lower.val : upper.val;
        }
        return target - lower.val < upper.val - target ? lower.val : upper.val;
    }

    // recursive
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target);
        return Math.abs(a-target) < Math.abs(b-target) ? a : b;
    }

    // iterative
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }

        int ret = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(ret - target)) {
                ret = root.val;
            } else if (Math.abs(root.val - target) == Math.abs(ret - target)) {
                ret = Math.min(ret, root.val);
            }
            if (root.val == target) {
                return root.val;
            }
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return ret;
    }
}
