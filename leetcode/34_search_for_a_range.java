public class Solution {
    // solution4: binary search w/ lower & upper middle
    public int[] searchRange(int[] nums, int target) {
        int lower = findLowerBound(nums, target);
        if (lower == -1) {
            return new int[]{-1, -1};
        }
        int upper = findUpperBound(nums, target);
        return new int[]{lower, upper};
    }

    private int findLowerBound(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            // lower middle
            int m = s + (e - s) / 2;
            if (nums[m] > target) {
                e = m - 1;
            } else if (nums[m] < target) {
                s = m + 1;
            } else {
                e = m;
            }
        }

        return s == e && nums[s] == target ? s : -1;
    }

    private int findUpperBound(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            // upper middle
            int m = s + (e - s + 1) / 2;
            if (nums[m] < target) {
                s = m + 1;
            } else if (nums[m] > target) {
                e = m - 1;
            } else {
                s = m;
            }
        }

        return s == e && nums[s] == target ? s : -1;
    }
    
    // solution3: combine common logic into finding ceiling
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1, -1};
        int lower = ceiling(nums, target - 1);
        if (lower < 0 || nums[lower] != target) return ret;
        ret[0] = lower;
        int upper = ceiling(nums, target);
        ret[1] = upper < 0 ? nums.length - 1 : upper - 1;
        return ret;
    }

    private int ceiling(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] <= target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return (s < nums.length && nums[s] > target) ? s : -1;
    }

    // solution2: find lower and next to the upper
    public int[] searchRange(int[] nums, int target) {
        int[] ret = {-1, -1};
        // find the lower bound
        int lower = findLowerBound(nums, target, 0, nums.length-1);
        if (lower == -1)
            return ret;
        // find the next to the upper
        int upper = findUpperBound(nums, target, lower, nums.length);
        ret[0] = lower;
        ret[1] = upper - 1;
        return ret;
    }

    private int findLowerBound(int[] nums, int target, int s, int e) {
        while (s < e) {
            int mid = s + ((e - s) >> 1);
            if (nums[mid] < target)
                s = mid + 1;
            else
                e = mid;
        }

        if (s == e && nums[s] == target)
            return s;
        else
            return -1;
    }

    private int findUpperBound(int[] nums, int target, int s, int e) {
        while (s < e) {
            int mid = s + ((e - s) >> 1);
            if (nums[mid] > target)
                e = mid;
            else
                s = mid + 1;
        }

        return s;
    }

    // solution1: find lower and upper
    public int[] searchRange(int[] nums, int target) {
        int lower = findLower(nums, 0, nums.length-1, target);
        if (lower == -1) return new int[]{-1, -1};
        int upper = findUpper(nums, lower, nums.length-1, target);
        return new int[]{lower, upper};
    }

    private int findLower(int[] nums, int s, int e, int target) {
        while (s < e) {
            int m = s + (e - s) / 2;
            if (nums[m] < target) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        // use s point, nums[e] may OutOfBound, for example nums.length = 0
        return s == e && nums[s] == target ? s : -1;
    }

    private int findUpper(int[] nums, int s, int e, int target) {
        while (s < e - 1) {
            int m = s + (e - s) / 2;
            if (nums[m] != target) {
                e = m -1;
            } else {
                s = m;
            }
        }
        return s == e - 1 && nums[e] == target ? e : s;
    }
}
