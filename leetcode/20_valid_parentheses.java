class Solution {
    // solution1
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        String left = "({[";
        String right = ")}]";
        for (char c : s.toCharArray()) {
            if (left.indexOf(c) >= 0) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if (left.indexOf(stack.pop()) != right.indexOf(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    // another solution
    // stack store expected right part, instead of left

    // solution2
    // optimization:
    //   1. use array as a stack, instead of linked list
    //   2. the array size only needs to be s.length() / 2
    //   3. store index, instead of character itself
    public boolean isValid(String s) {
        int[] stack = new int[s.length()/2];
        int pos = -1;
        String left = "({[";
        String right = ")}]";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int leftIndex = left.indexOf(c);
            if (leftIndex >= 0) {
                if (++pos >= stack.length) return false;
                stack[pos] = leftIndex;
            } else {
                if (pos < 0 || stack[pos] != right.indexOf(c)) return false;
                pos--;
            }
        }
        return pos < 0;
    }
}
