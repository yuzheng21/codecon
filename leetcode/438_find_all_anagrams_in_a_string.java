class Solution {
    // sliding window and array
    // O(26*N), N is length of s
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        int n = p.length();

        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // i is the start pointer of sliding window
        for (int i = 0; i <= s.length() - n; i++) {
            if (i == 0) {
                for (int j = i; j < n; j++) {
                    sCount[s.charAt(j) - 'a']++;
                }
            } else {
                sCount[s.charAt(i - 1) - 'a']--;
                sCount[s.charAt(i + n - 1) - 'a']++;
            }

            if (Arrays.equals(sCount, pCount)) {
                ret.add(i);
            }
        }

        return ret;
    }

    // sliding window and array
    // O(26*N), N is length of s
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        int n = p.length();

        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // i is the end pointer of sliding window
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;

            if (i >= n) {
                sCount[s.charAt(i - n) - 'a']--;
            }

            if (Arrays.equals(pCount, sCount)) {
                ret.add(i - n + 1);
            }
        }

        return ret;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int[] pFreq = getFreq(p);
        int n = p.length();

        for (int i = 0; i <= s.length() - n; i++) {
            if (pFreq[s.charAt(i)] > 0) {
                // re-use one anagram frequency as much as possible
                // to avoid function calls of getFreq()
                int[] sFreq = getFreq(s.substring(i, i + n));
                if (Arrays.equals(sFreq, pFreq)) {
                    ret.add(i);

                    int j = i + n;
                    while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                        i++;
                        ret.add(i);
                        j++;
                    }

                    if (j < s.length()) {
                        while (i < j && s.charAt(i) != s.charAt(j)) {
                            i++;
                        }
                    }

                    // note, at this moment, s.charAt(i) is already checked
                    // i needs to be moved forward
                    // which i increments in the for loop
                }
            }
        }

        return ret;
    }

    private int[] getFreq(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        return freq;
    }
}
