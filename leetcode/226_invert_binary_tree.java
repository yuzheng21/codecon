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
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode leftsub = invertTree(root.left);
        TreeNode rightsub = invertTree(root.right);
        root.left = rightsub;
        root.right = leftsub;

        return root;
    }
}
