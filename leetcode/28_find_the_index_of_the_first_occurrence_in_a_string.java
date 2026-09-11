class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            int j = 0;
            while (j < needle.length() && i + j < haystack.length()) {
                if (haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
