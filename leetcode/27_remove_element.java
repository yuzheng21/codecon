class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            if (nums[i] != val) {
                i++;
            } else {
                // swap
                nums[i] = nums[j];
                nums[j] = val;
                j--;
            }
        }
        return i;
    }
}
