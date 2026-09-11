class Solution {
    // bucket sort - bucket size: valueDiff + 1
    //               map size: indexDiff - 1
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        Map<Long, Long> map = new HashMap<>();
        long bucketSize = valueDiff + 1L;

        for (int i = 0; i < nums.length; i++) {
            long bucketId = getId((long) nums[i], bucketSize);
            if (map.containsKey(bucketId)) {
                return true;
            }

            if (map.containsKey(bucketId - 1) && Math.abs(nums[i] - map.get(bucketId - 1)) <= valueDiff) {
                return true;
            }

            if (map.containsKey(bucketId + 1) && Math.abs(nums[i] - map.get(bucketId + 1)) <= valueDiff) {
                return true;
            }

            map.put(bucketId, (long) nums[i]);

            if (i >= indexDiff) {
                map.remove(getId(nums[i - indexDiff], bucketSize));
            }
        }

        return false;
    }

    private long getId(long a, long b) {
        return Math.floorDiv(a, b);
    }
}
