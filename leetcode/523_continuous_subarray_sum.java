class Solution {

    // Using prefix sums modulo k and a hash map to detect if a previous identical remainder exists at a valid distance.
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }

        // key - modulized previous sum, value - position
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // next accumulated sum 0 must be or after i == 1;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum %= k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) >= 2) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
