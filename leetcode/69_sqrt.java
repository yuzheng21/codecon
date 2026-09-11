class Solution {
    // binary search
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int s = 1;
        int e = x / 2;

        while (s <= e) {
            int m = s + (e - s) / 2;
            // to avoid overflow
            if (m == x / m) {
                return m;
            }

            if (m > x / m) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        return s - 1;
    }

    // binary search
    public int mySqrt(int x) {
        if (x < 2)
            return x;
        int s = 1;
        int e = x / 2;
        while (s < e) {
            int m = s + (e - s) / 2;
            if (m > x / m) {
                e = m - 1;
            } else {
                if ((m + 1) > x / (m + 1)) {
                    return m;
                }
                s = m + 1;
            }
        }
        // unreachable arbitray value
        return s;
    }

    // newton's method
    public int mySqrt(int x) {
        if (x < 2)
            return x;
        // x >= 2
        int guess = x / 2;
        while (!closeEnough(x / guess, guess)) {
            guess = betterGuess(x, guess);
        }
        return guess;
    }

    private boolean closeEnough(int a, int b) {
        return a >= b;
    }

    private int betterGuess(int x, int g) {
        return (g + x / g) / 2;
    }
}
