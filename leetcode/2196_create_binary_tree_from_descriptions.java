/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] description : descriptions) {
            map.putIfAbsent(description[0], new TreeNode(description[0]));
            map.putIfAbsent(description[1], new TreeNode(description[1]));

            TreeNode parent = map.get(description[0]);
            TreeNode child = map.get(description[1]);

            if (description[2] == 1) {
                parent.left = child;
            } else if (description[2] == 0) {
                parent.right = child;
            }

            children.add(description[1]);
        }
        
        TreeNode root = null;
        for (int id : map.keySet()) {
            if (!children.contains(id)) {
                root = map.get(id);
                break;
            }
        }

        return root;
    }
}
