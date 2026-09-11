class Solution {
    // time: O(lgn) - binary search a local maximum, clean version
    public int findPeakElement(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int m = s + (e - s) / 2;
            if (nums[m] < nums[m + 1]) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return s;
    }

    // binary search
    public int findPeakElement(int[] nums) {
        return findPeak(nums, 0, nums.length - 1);
    }

    private int findPeak(int[] nums, int s, int e) {
        if (s > e) {
            return -1;
        }

        int mid = s + (e - s) / 2;

        boolean left = mid == 0 || nums[mid] > nums[mid - 1];
        boolean right = mid == nums.length - 1 || nums[mid] > nums[mid + 1];

        if (left && right) {
            return mid;
        }

        // O(n)
        // Current recursion can explore both halves, leading to linear time.
        // Since a peak always exists on the side of an increasing slope, only visit one half.
        int leftPeak = findPeak(nums, s, mid - 1);
        return leftPeak != -1 ? leftPeak : findPeak(nums, mid + 1, e);

        // O(logn)
        if (mid + 1 < nums.length && nums[mid] < nums[mid + 1]) {
            return findPeak(nums, mid + 1, e);
        } else {
            return findPeak(nums, s, mid); // note: cannot use mid - 1 since nums[mid] >= nums[mid + 1]
        }
    }
}
