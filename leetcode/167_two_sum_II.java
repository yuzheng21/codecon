class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return {i + 1, j + 1};
            }

            if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        throw new RuntimeException("not found");
    }
}
