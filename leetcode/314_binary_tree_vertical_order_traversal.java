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
    // bfs - one queue to store both node and column number
    // use a hash table for row level traversal
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Map<Integer, List<Integer>> colTable = new HashMap<>();

        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();

        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Pair<TreeNode, Integer> curPair = queue.poll();
                TreeNode node = curPair.getKey();
                int col = curPair.getValue();

                colTable.putIfAbsent(col, new ArrayList<>());
                colTable.get(col).add(node.val);

                minCol = Math.min(minCol, col);
                maxCol = Math.max(maxCol, col);

                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, col - 1));
                }

                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, col + 1));
                }
            }
        }

        for (int i = minCol; i <= maxCol; i++) {
            if (!colTable.containsKey(i)) {
                continue;
            }
            ret.add(colTable.get(i));
        }

        return ret;
    }

    // bfs, traverse tree level by level
    // put column number into queue
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;

        Map<Integer, List<Integer>> row = new HashMap<>();

        Deque<Integer> cols = new ArrayDeque<>();
        cols.offer(0);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int min = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int k = 0; k < len; k++) {
                TreeNode cur = queue.poll();
                int col = cols.poll();
                row.putIfAbsent(col, new ArrayList<>());
                row.get(col).add(cur.val);

                if (cur.left != null) {
                    queue.offer(cur.left);
                    cols.offer(col - 1);
                    min = Math.min(min, col - 1);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                    cols.offer(col + 1);
                    max = Math.max(max, col + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            if (!row.containsKey(i)) continue;
            ret.add(row.get(i));
        }
        return ret;
    }
}
