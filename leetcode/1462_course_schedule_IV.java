class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // build graph
        int n = numCourses;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];

        for (int[] req : prerequisites) {
            adj[req[0]].add(req[1]);
            indegree[req[1]]++;
        }

        Map<Integer, Set<Integer>> requirements = new HashMap<>();

        // bfs
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // accumulated requriements for cur's neighbors: cur + cur's requirements
            Set<Integer> accumulatedRequirements = new HashSet<>();
            // add cur itself
            accumulatedRequirements.add(cur);
            // add all cur's requirements
            if (requirements.containsKey(cur)) {
                accumulatedRequirements.addAll(requirements.get(cur));
            }

            for (int next : adj[cur]) {
                requirements.putIfAbsent(next, new HashSet<>());
                requirements.get(next).addAll(accumulatedRequirements);

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        List<Boolean> ret = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            if (!requirements.containsKey(v)) {
                ret.add(false);
            } else {
                ret.add(requirements.get(v).contains(u));
            }
        }

        return ret;
    }

}
