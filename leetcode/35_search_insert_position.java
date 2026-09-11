public class Solution {
    // O(logn)
    public int searchInsert(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        int m = 0;
        while (s <= e) {
            m = s + (e - s) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] > target) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        // If not found the loop will be stopped at the moment when e < s and nums[e] < target < nums[s]
        // Hence, the proper position to insert the target is at the index s.
        return s;
        
        // If not found, another solution is to use index m
        // if (nums[m] < target) {
        //     return m + 1;
        // } else {
        //     return m;
        // }
    }
}
