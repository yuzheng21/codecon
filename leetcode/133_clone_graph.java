/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // bfs
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        Set<Node> visited = new HashSet<>();
        Map<Integer, Node> map = new HashMap<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // check visited before node processing / cloning
            // always make sure the node in the queue is not visited
            // otherwise, the node neighbors could be added multiple times
            if (visited.contains(cur)) {
                continue;
            }

            // clone node
            map.putIfAbsent(cur.val, new Node(cur.val));
            Node clone = map.get(cur.val);

            // clone neighbors
            for (Node neighbor : cur.neighbors) {
                map.putIfAbsent(neighbor.val, new Node(neighbor.val));
                Node cloneNeighbor = map.get(neighbor.val);
                // need to make sure the node (being cloned) is not visited previously
                clone.neighbors.add(cloneNeighbor);

                queue.offer(neighbor);
            }

            visited.add(cur);
        }

        return map.get(node.val);
    }

    // dfs
    // key -> original node, value -> clone node
    private Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // clone node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // clone neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}
