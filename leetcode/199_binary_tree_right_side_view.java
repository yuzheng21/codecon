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
    // dfs
    // Identifying the first node encountered at each depth level during a right-to-left tree traversal.
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, 0, ret);
        return ret;
    }

    private void dfs(TreeNode node, int level, List<Integer> ret) {
        if (node == null)
            return;
        if (level >= ret.size()) {
            ret.add(node.val);
        }
        dfs(node.right, level + 1, ret);
        dfs(node.left, level + 1, ret);
    }

    // bfs
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode cur = queue.poll();
                if (i == len - 1)
                    ret.add(cur.val); // add the right most node's value
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
        }
        return ret;
    }
}
