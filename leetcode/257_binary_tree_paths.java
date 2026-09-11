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

    // solution 1
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root != null) helper(ret, "", root);
        return ret;
    }

    private void helper(List<String> ret, String path, TreeNode node) {
        if (node.left == null && node.right == null) ret.add(path + node.val); // leaf node
        if (node.left != null) helper(ret, path + node.val + "->", node.left);
        if (node.right != null) helper(ret, path + node.val + "->", node.right);
    }

    // solution 2
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root == null) return ret;
        StringBuilder sb = new StringBuilder();
        helper(ret, sb, root);
        return ret;
    }

    private void helper(List<String> ret, StringBuilder sb, TreeNode node) {
        if (node == null) return;
        int origin = sb.length();
        sb.append("->" + node.val);
        if (node.left == null && node.right == null) {
            ret.add(sb.substring(2, sb.length())); // remove first "->"
        } else {
            helper(ret, sb, node.left);
            helper(ret, sb, node.right);
        }
        sb.delete(origin, sb.length());
    }
}
