class Solution {
    // solution
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'X') {
                    continue;
                }

                // current X belongs to existing battleship above
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }

                // current X belongs to existing battleship left
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }

                count++;
            }
        }

        return count;
    }

    // solution
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'X') {
                    continue;
                }
                visit(board, i, j);
                count++;
            }
        }

        return count;
    }

    private void visit(char[][] board, int row, int col) {
        if (board[row][col] != 'X') {
            return;
        }
        board[row][col] = 'Y';

        int m = board.length;
        int n = board[0].length;

        for (int i = row + 1; i < m; i++) {
            if (board[i][col] != 'X') {
                break;
            }
            board[i][col] = 'Y';
        }

        for (int i = col + 1; i < n; i++) {
            if (board[row][i] != 'X') {
                break;
            }
            board[row][i] = 'Y';
        }
    }
}
