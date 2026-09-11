public class Solution {
    // sliding window + count
    public int lengthOfLongestSubstring(String s) {
        int[] count = new int[256];
        int i = 0;
        int ret = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            count[c]++;
            while (count[c] > 1) {
                count[s.charAt(i)]--;
                i++;
            }
            ret = Math.max(ret, j - i + 1);
        }
        return ret;
    }

    // sliding window + position
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } 

        int max = 0;
        Map<Character, Integer> map = new HashMap<>();

        int p = 0;
        int q = 0;
        while (q < s.length()) {
            if (map.containsKey(s.charAt(q))) {
                int index = map.get(s.charAt(q)) + 1;
                while (p < index) {
                    map.remove(s.charAt(p));
                    p++;
                }
            }
            map.put(s.charAt(q), q);
            max = Math.max(max, q - p + 1);
            q++;
        }

        return max;
    }

    // greedy: if repeating appears in substring, then the parent string must contains repeating
    // when find repeating character, start from the next char to the previous repeating character
    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        int[] last = new int[256];
        Arrays.fill(last, -1);
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - ' ';
            if (last[pos] >= start) {
                len = Math.max(len, i-start);
                start = last[pos] + 1;
            }
            last[pos] = i;
        }
        len = Math.max(len, s.length()-start);
        return len;
    }
}
