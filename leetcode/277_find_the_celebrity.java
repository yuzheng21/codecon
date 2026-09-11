/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    // 2 pass, call knows() 3(n-1) times
    public int findCelebrity(int n) {
        if (n < 2) return n - 1;
        int candidate = 0;
        // find candidate
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) candidate = i;
        }
        // check if candidate is real
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            if (!knows(i, candidate)) return -1;
            // already checked knows(candidate, i) in the first loop for i >= candidate
            if (i < candidate && knows(candidate, i)) return -1;
        }
        return candidate;
    }
}
