class Solution {
    // solution2: O(n), O(n)
    // arraylist + hashmap: value stores index in arraylist
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String s : strs) {
            char[] sorted = s.toCharArray();
            Arrays.sort(sorted);
            String key = String.valueOf(sorted);
            if (map.containsKey(key)) {
                res.get(map.get(key)).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                res.add(list);
                map.put(key, res.size()-1);
            }
        }
        return res;
    }

    // solution1: O(n), O(n)
    // hashmap: value stores list of string
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sorted = s.toCharArray();
            Arrays.sort(sorted);
            String key = String.valueOf(sorted);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
