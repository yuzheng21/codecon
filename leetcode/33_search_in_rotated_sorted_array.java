// no duplicate exists
public class Solution {
    // iteration
    public int search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + ((e - s) >> 1);

            if (nums[mid] == target)
                return mid;

            // left has order
            if (nums[s] <= nums[mid]) {
                if (nums[s] <= target && target < nums[mid])
                    e = mid - 1;
                else
                    s = mid + 1;
            } else {
                // right has order
                if (nums[mid] < target && target <= nums[e])
                    s = mid + 1;
                else
                    e = mid - 1;
            }
        }

        return -1;
    }

    // recurssive
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int s, int e, int target) {
        if (s > e) {
            return -1;
        }

        int mid = s + (e - s) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[s] <= nums[mid]) {
            if (nums[s] <= target && target < nums[mid]) {
                return search(nums, s, mid - 1, target);
            } else {
                return search(nums, mid + 1, e, target);
            }
        } else {
            if (nums[mid] < target && target <= nums[e]) {
                return search(nums, mid + 1, e, target);
            } else {
                return search(nums, s, mid - 1, target);
            }
        }
    }
}
