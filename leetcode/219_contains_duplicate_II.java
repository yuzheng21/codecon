class Solution {
    // sliding window
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        k = Math.min(k, nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            if (i > k)
                set.remove(nums[i-k-1]);
            if (!set.add(nums[i]))
                return true;
        }
        return false;
    }

    // hash table
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (!map.containsKey(val)) {
                map.put(val, new ArrayList());
            }
            for (int pos : map.get(val)) {
                if (Math.abs(i - pos) <= k) {
                    return true;
                }
            }
            map.get(val).add(i);
        }
        return false;
    }
}
