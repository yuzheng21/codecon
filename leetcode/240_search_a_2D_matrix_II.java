class Solution {
    // O(m + n) - Search Space Reduction
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        final int M = matrix.length;
        final int N = matrix[0].length;
        int i = 0;
        int j = N - 1;
        while (i < M && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    // O(mlgn) -  binary search row, reduce the rage of rows to search by iterating the columns
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        final int M = matrix.length;
        final int N = matrix[0].length;

        // upper row
        int upper = 0;
        while (upper < M) {
            if (matrix[upper][N - 1] < target) {
                upper++;
            } else {
                break;
            }
        }

        // bottom row
        int bottom = M - 1;
        while (bottom >= upper) {
            if (matrix[bottom][0] > target) {
                bottom--;
            } else {
                break;
            }
        }

        for (int i = upper; i <= bottom; i++) {
            if (search(matrix[i], 0, N - 1, target)) {
                return true;
            }
        }

        return false;
    }

    private boolean search(int[] array, int s, int e, int target) {
        while (s <= e) {
            int m = s + (e - s) / 2;
            if (array[m] == target) {
                return true;
            }
            if (array[m] < target) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return false;
    }

    // O(log(n!)) - binary search all rows and columns, iterate along the diagonal
    // note that all diagonals are sorted
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int shortDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shortDim; i++) {
            boolean foundByRow = search(matrix, target, i, false);
            boolean foundByColumn = search(matrix, target, i, true);
            if (foundByRow || foundByColumn) {
                return true;
            }
        }
        
        return false;
    }

    private boolean search(int[][] matrix, int target, int start, boolean vertical) {
        int s = start;
        int e = vertical ? matrix.length - 1 : matrix[0].length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (vertical) {
                // search column
                if (matrix[mid][start] == target) {
                    return true;
                }
                if (matrix[mid][start] < target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            } else {
                // search row
                if (matrix[start][mid] == target) {
                    return true;
                }
                if (matrix[start][mid] < target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return false;
    }
}
