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
    public String getDirections(TreeNode root, int startValue, int destValue) {
        List<TreeNode> pathToStart = dfs(root, startValue, new ArrayList<>(), new boolean[1]);
        List<TreeNode> pathToDest = dfs(root, destValue, new ArrayList<>(), new boolean[1]);

        int lca = indexLCA(pathToStart, pathToDest);
        if (lca < 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = pathToStart.size() - 1; i > lca; i--) {
            char dir = getDir(pathToStart.get(i), pathToStart.get(i - 1));
            sb.append(dir);
        }

        for (int i = lca; i < pathToDest.size() - 1; i++) {
            char dir = getDir(pathToDest.get(i), pathToDest.get(i + 1));
            sb.append(dir);
        }

        return sb.toString();
    }

    private List<TreeNode> dfs(TreeNode node, int target, List<TreeNode> path, boolean[] found) {
        if (node == null || found[0]) {
            return null;
        }

        List<TreeNode> result;

        path.add(node);

        if (node.val == target) {
            result = new ArrayList<>(path);
            found[0] = true;
        } else {
            List<TreeNode> left = dfs(node.left, target, path, found);
            if (left != null) {
                result = left;
            } else {
                result = dfs(node.right, target, path, found);
            }
        }

        path.remove(path.size() - 1);

        return result;
    }

    private int indexLCA(List<TreeNode> a, List<TreeNode> b) {
        int i = 0;
        int j = 0;
        int len = Math.min(a.size(), b.size());
        while (i < len && j < len && a.get(i) == b.get(j)) {
            i++;
            j++;
        }
        return i - 1;
    }

    // direction of nodes from -> to
    private char getDir(TreeNode from, TreeNode to) {
        if (from.left == to) {
            return 'L';
        }
        if (from.right == to) {
            return 'R';
        }
        return 'U';
    }
}
