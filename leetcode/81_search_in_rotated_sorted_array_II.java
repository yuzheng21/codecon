class Solution {
    public boolean search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] == target)
                return true;
            // compare mid and e to check flip point locates at which side
            if (nums[mid] > nums[e]) {
                // flip point is at right part
                if (nums[s] <= target && target < nums[mid]) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else if (nums[mid] < nums[e]) {
                // flip point is at left part
                if (nums[mid] < target && target <= nums[e]) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            } else {
                // nums[mid] == nums[e]
                if (nums[s] != nums[e]) { // optimization - optional
                    e = mid - 1;
                } else {
                    e--;
                }
            }
        }
        return false;
    }
}
