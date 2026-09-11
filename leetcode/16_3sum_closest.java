class Solution {
    // de-duplicate to accelerate
    // use diff as a sentinel
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target)
                    return sum;
                if (sum < target) {
                    // might overflow
                    if (target - sum < diff) {
                        diff = target - sum;
                        res = sum;
                    }
                    while (j < k && nums[j] == nums[j+1]) j++;
                    j++;
                } else {
                    // might overflow
                    if (sum - target < diff) {
                        diff = sum - target;
                        res = sum;
                    }
                    while (j < k && nums[k] == nums[k-1]) k--;
                    k--;
                }
            }
        }
        return res;
    }
}
