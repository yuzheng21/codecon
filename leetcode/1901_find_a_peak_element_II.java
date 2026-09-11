class Solution {
    // O(NlgM) - binary search over columns w/ finding the max row
    public int[] findPeakGrid(int[][] mat) {
        int startCol = 0;
        int endCol = mat[0].length - 1;
        // O(lgM) binary search over columns
        while (startCol <= endCol) {
            int midCol = startCol + (endCol - startCol) / 2;

            // O(N) - find max row
            int maxRow = 0;
            for (int row = 0; row < mat.length; row++) {
                if (mat[row][midCol] >= mat[maxRow][midCol]) {
                    maxRow = row;
                }
            }

            // check if we find the peak
            boolean leftIsBig = midCol - 1 >= startCol && mat[maxRow][midCol - 1] > mat[maxRow][midCol];
            boolean rightIsBig = midCol + 1 <= endCol && mat[maxRow][midCol + 1] > mat[maxRow][midCol];

            if (!leftIsBig && !rightIsBig) {
                return new int[]{maxRow, midCol};
            }

            if (leftIsBig) {
                endCol = midCol - 1;
            } else {
                startCol = midCol + 1;
            }
        }

        // not found
        return null;
    }
}
