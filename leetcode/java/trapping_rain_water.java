class Solution {
    // time: O(n)  space: O(1)
    // two pointer
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int p = 0;
        int q = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int sum = 0;
        while (p <= q) {
            if (height[p] <= height[q]) {
                // right bar is higher
                if (height[p] >= maxLeft)
                    maxLeft = height[p];
                else
                    sum += maxLeft - height[p];
                p++;

            } else {
                // left bar is higher
                if (height[q] >= maxRight)
                    maxRight = height[q];
                else
                    sum += maxRight - height[q];
                q--;
            }
        }
        return sum;
    }

    // time: O(n)  space: O(n)
    // stack: store index of non-increasing height
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;

        Deque<Integer> stack = new LinkedList<>();
        int p = 0;
        int sum = 0;
        while (p < height.length) {
            if (stack.isEmpty() || height[p] <= height[stack.peek()]) {
                stack.push(p++);
            } else {
                // do move pointer p
                int cur = stack.pop();
                sum += stack.isEmpty() ?
                    0 :
                    (Math.min(height[stack.peek()], height[p]) - height[cur]) * (p - stack.peek() - 1);
            }
        }
        return sum;
    }

    // time: O(n)  space: O(n)
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int[] left = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i-1], height[i-1]);
        }
        int sum = 0;
        int right = height[height.length-1];
        for (int i = height.length - 2; i > 0; i--) {
            sum += Math.max(Math.min(left[i], right) - height[i], 0);
            right = Math.max(right, height[i]);
        }
        return sum;
    }
}
