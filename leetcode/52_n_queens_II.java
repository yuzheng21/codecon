class Solution {
    public int totalNQueens(int n) {
        // use array as an pointer to a single integer
        int[] res = new int[]{0};
        // store valid queens in a map (array implementation)
        // key - row index, value - column index
        dfs(n, 0, new int[n], res);
        return res[0];
    }

    private void dfs(int n, int row, int[] queens, int[] res) {
        if (row == n) {
            res[0]++;
            return;
        }
        for (int i = 0; i < n; i++) {
            queens[row] = i;
            if (isValid(row, queens)) {
                dfs(n, row+1, queens, res);
            }
            // no need to restore, because it is always override
            // queens[row] = -1;
        }
    }

    private boolean isValid(int row, int[] queens) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == queens[row] || Math.abs(queens[row] - queens[i]) == row - i) {
                return false;
            }
        }
        return true;
    }
}
