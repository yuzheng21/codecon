class Solution {
    // solution2: bfs from start and end node simultaneously
    // at each level, pick the smaller one to traverse
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList); // unvisied
        if (!words.contains(endWord)) {
            return 0;
        }

        // mark beginWord and endWord as visited
        words.remove(beginWord);
        words.remove(endWord);

        // BFS from two ends
        // because it needs to judge if two bfs cross, so it needs to use hashmap
        Set<String> beginSide = new HashSet<>();
        Set<String> endSide = new HashSet<>();
        beginSide.add(beginWord); // begin
        endSide.add(endWord); // end

        int dist = 1;
        while (!beginSide.isEmpty() && !endSide.isEmpty()) {
            dist++;
            if (beginSide.size() < endSide.size()) {
                if (convert(beginSide, endSide, words)) {
                    return dist;
                }
            } else {
                if (convert(endSide, beginSide, words)) {
                    return dist;
                }
            }
        }
        return 0;
    }

    private boolean convert(Set<String> from, Set<String> to, Set<String> words) {
        Set<String> next = new HashSet<>();
        for (String word : from) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (to.contains(newWord)) {
                        return true;
                    }
                    if (words.contains(newWord)) {
                        words.remove(newWord); // mark as visited
                        next.add(newWord);
                    }
                }
                chars[i] = word.charAt(i);
            }
        }
        from.clear();
        from.addAll(next);
        return false;
    }

    // BFS from start word
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(beginWord);
        visited.add(beginWord);

        int step = 0;

        while (!queue.isEmpty()) {
            step++; // one step used to move to current words layer

            // need to BFS layer by layer
            int len = queue.size();
            while (len > 0) {
                len--;

                String cur = queue.poll();

                if (cur.equals(endWord)) {
                    return step;
                }

                char[] word = cur.toCharArray();
                for (int i = 0; i < word.length; i++) {
                    char temp = word[i];
                    for (int j = 'a'; j <= 'z'; j++) {
                        if (temp == (char) j) {
                            continue;
                        }
                        word[i] = (char) j;

                        String nextWord = new String(word);
                        if (!wordSet.contains(nextWord) || visited.contains(nextWord)) {
                            continue;
                        }
                        if (nextWord.equals(endWord)) {
                            return step + 1; // one step used to move to next word layer
                        }

                        visited.add(nextWord);
                        queue.offer(nextWord);
                    }
                    word[i] = temp;
                }
            }
        }

        // no path
        return 0;
    }

    // solution1: bfs from start point
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int step = 1;
        int count = 1;
        // note: put the node to visited set when add it to the queue, not take it out from queue
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            while (count-- > 0) {
                String word = queue.poll();
                if (word.equals(endWord)) return step;
                char[] wordChars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char origin = wordChars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == wordChars[i]) continue;
                        wordChars[i] = c;
                        String tmp = new String(wordChars);
                        if (wordList.contains(tmp)) {
                            if (tmp.equals(endWord)) return step + 1;
                            if (!visited.contains(tmp)) {
                                queue.offer(tmp);
                                visited.add(tmp);
                            }
                        }
                    }
                    wordChars[i] = origin;
                }
            }
            count = queue.size();
            step++;
        }
        return 0;
    }
}
