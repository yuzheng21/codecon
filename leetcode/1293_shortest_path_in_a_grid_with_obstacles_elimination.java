class Solution {

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // k is large enough to eliminate all blocks on the shortest path
        if (k >= m + n - 2) {
            return m + n - 2;
        }

        // state array: row, col, k, steps
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[m][n][k + 1];

        // visited needs to capture the state which contains (row, col, k)
        // one same (row, col) can be visited with different k
        visited[0][0][k - 1] = true;
        queue.offer(new int[]{0, 0, k, 0});

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int row = state[0];
            int col = state[1];

            if (row == m - 1 && col == n - 1) {
                return state[3];
            }

            for (int[] dir : DIRS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                    continue;
                }

                int nextK = state[2] - grid[nextRow][nextCol];
                if (nextK < 0 || visited[nextRow][nextCol][nextK]) {
                    continue;
                }

                visited[nextRow][nextCol][nextK] = true;
                queue.offer(new int[]{nextRow, nextCol, nextK, state[3] + 1});
            }
        }

        return -1;
    }
}
