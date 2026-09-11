class Solution {
    // O(logN)
    public boolean isHappy(int n) {
        int p = n;
        int q = next(n);
        while (p != q) {
            if (p == 1 || q == 1) {
                return true;
            }
            p = next(p);
            q = next(q);
            q = next(q);
        }

        return p == 1;
    }

    private int next(int n) {
        int ret = 0;
        while (n != 0) {
            int m = n % 10;
            n = n / 10;
            ret += m * m;
        }
        return ret;
    }
}
