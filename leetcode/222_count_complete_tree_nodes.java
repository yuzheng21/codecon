/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // O(n) - ignore complete tree condition
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // O((lgn)^2) - binary search in binary search
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0
                : height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                        : (1 << h - 1) + countNodes(root.left);
    }

    private int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    // O((lgn)^2) - binary search in binary search
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        int hl = 0;
        int hr = 0;
        TreeNode left = root;
        TreeNode right = root;
        while (left != null) {
            hl++;
            left = left.left;
        }

        while (right != null) {
            hr++;
            right = right.right;
        }

        if (hl == hr)
            return (1 << hl) - 1;
        else
            return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
