class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ret = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {
            int[] first = firstList[i];
            int[] second = secondList[j];
            if ((first[0] <= second[0] && second[0] <= first[1]) || (second[0] <= first[0] && first[0] <= second[1])) {
                int[] entry = new int[2];
                entry[0] = Math.max(first[0], second[0]);
                entry[1] = Math.min(first[1], second[1]);
                ret.add(entry);
            }

            if (first[1] <= second[1]) {
                i++;
            } else {
                j++;
            }
        }
        return ret.toArray(new int[ret.size()][2]);
    }
}
