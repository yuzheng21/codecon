/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {

    // solution
    public int[][] merge(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        List<int[]> ret = new ArrayList<>();
        for (int i = 0, j = 0; j < intervals.length; j++) {
            if (j == intervals.length - 1 || start[j + 1] > end[j]) {
                ret.add(new int[]{start[i], end[j]});
                i = j + 1;
            }
        }

        return ret.toArray(new int[ret.size()][2]);
    }

    // solution
    public int[][] merge(int[][] intervals) {
        List<int[]> ret = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        if (intervals.length == 0) {
            return new int[0][2];
        }

        ret.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] a = ret.get(ret.size() - 1);
            int[] b = intervals[i];
            if (b[0] > a[1]) {
                ret.add(b);
            } else {
                a[1] = Math.max(a[1], b[1]);
            }
        }

        return ret.toArray(new int[ret.size()][2]);
    }

    // solution3, time: O(nlgn)
    // based on solution2, improved by skiping some interations
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) return intervals;

        final int N = intervals.size();
        int[] starts = new int[N];
        int[] ends = new int[N];
        for (int i = 0; i < N; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        List<Interval> ret = new ArrayList<>();
        for (int i = 0, j = 0; i < N; i++) {
            if (i == N-1 || starts[i+1] > ends[i]) {
                ret.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }

        return ret;
    }

    // solution2, time: O(nlgn)
    // java arrays.sort is Dual-Pivot Quicksort, O(nlgn)
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        Interval cur = new Interval(start[0], end[0]);
        res.add(cur);
        for (int i = 1; i < intervals.size(); i++) {
            if (start[i] > cur.end) {
                cur = new Interval(start[i], end[i]);
                res.add(cur);
            } else {
                cur.end = Math.max(cur.end, end[i]);
            }
        }
        return res;
    }

    // solution1, time: O(nlgn)
    // java list sort - performance similar to merge sort
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;
        intervals.sort((a, b) -> a.start - b.start);
        int p = 0;
        res.add(intervals.get(0));
        for (int q = 1; q < intervals.size(); q++) {
            if (res.get(p).end < intervals.get(q).start) {
                res.add(intervals.get(q));
                p++;
            } else {
                res.get(p).end = Math.max(res.get(p).end, intervals.get(q).end);
            }
        }
        return res;
    }
}
