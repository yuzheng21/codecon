class Solution {
    // product except nums[i] = (left product to i - 1) * (right product from i + 1)
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length + 1];
        int[] right = new int[nums.length + 1];
        left[0] = 1;
        right[nums.length] = 1;
        
        for (int i = 0; i < nums.length; i++) {
            left[i + 1] = left[i] * nums[i]
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i];
        }

        int[] ret = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ret[i] = left[i] * right[i + 1]
        }

        return ret;
    }

    // clean version
    public int[] productExceptSelf(int[] nums) {
        int[] ret = new int[nums.length];
        Arrays.fill(ret, 1);
        int forward = 1;
        for (int i = 1; i < nums.length; i++) {
            forward *= nums[i - 1];
            ret[i] = forward;
        }
        int backward = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            backward *= nums[i + 1];
            ret[i] *= backward;
        }
        return ret;
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums.length == 0)
            return nums;
        int[] ret = new int[nums.length];
        ret[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ret[i] = ret[i - 1] * nums[i - 1];
        }
        int sum = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ret[i] *= sum;
            sum *= nums[i];
        }
        return ret;
    }
}
