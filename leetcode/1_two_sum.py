class Solution:
    # two pointers
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # sort nums
        sorted_nums = sorted(nums)

        p = 0
        q = len(nums) - 1
 
        while p < q:
            current_sum = sorted_nums[p] + sorted_nums[q]
            if current_sum == target:
                break;
 
            if current_sum > target:
                q -= 1
            else:
                p += 1
 
        # no solution
        if p >= q:
            return [-1, -1]

        ret = [-1, -1]
        for i, num in enumerate(nums):
            if num == sorted_nums[p] and ret[0] == -1:
                ret[0] = i

            if num == copy[q]:
                ret[1] = i

        return ret


    # hash map
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # hash map: {num: index}
        dict = {}

        for i, num in enumerate(nums):
            complement = target - num

            if complement in dict:
                return [i, dict[complement]]
                         
            dict[num] = i

        # no solution
        return []
