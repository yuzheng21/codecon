class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i];
            String b = words[i + 1];
            // check if word b is a prefix of word a
            if (a.length() > b.length() && a.startsWith(b)) {
                return "";
            }

            for (int j = 0; j < Math.min(a.length(), b.length()); j++) {
                // check the first unequal char
                char ac = a.charAt(j);
                char bc = b.charAt(j);
                if (ac != bc) {
                    // increase indegree when process the edge at the first time
                    if (adj.get(ac).add(bc)) {
                        indegree.put(bc, indegree.get(bc) + 1);
                    }
                    break;
                }
            }
        }

        Deque<Character> queue = new ArrayDeque<>();

        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);

            for (char next : adj.get(c)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        if (sb.length() != indegree.size()) {
            return "";
        }

        return sb.toString();
    }

}
