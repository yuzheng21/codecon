public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        backtrace(ret, new ArrayList<>(), nums, 0);
        return ret;
    }

    // solution 1
    private void backtrace(List<List<Integer>> ret, List<Integer> entry, int[] nums, int cur) {
        if (cur == nums.length) {
            ret.add(new ArrayList<>(entry));
            return;
        }
        // skip current number
        backtrace(ret, entry, nums, cur+1);
        // add current number
        entry.add(nums[cur]);
        backtrace(ret, entry, nums, cur+1);
        entry.remove(entry.size()-1);
    }

    // solution 2 - collecting all subsets starting from s, i.e. {x >= s, x -> {1,2,3,s,...,n}}
    private void backtrace(List<List<Integer>> ret, List<Integer> entry, int[] nums, int start) {
        ret.add(new ArrayList<>(entry));
        for (int i = start; i < nums.length; i++) {
            entry.add(nums[i]);
            backtrace(ret, entry, nums, i+1);
            entry.remove(entry.size()-1);
        }
    }
}
