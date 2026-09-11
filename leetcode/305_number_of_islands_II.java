class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}}; 
        UnionFind uf = new UnionFind(m * n);
        Set<Integer> lands = new HashSet<>();
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            int cur = i * n + j;
            lands.add(cur);
            uf.count++;
            for (int[] dir : dirs) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;
                int next = ii * n + jj;
                if (lands.contains(next)) {
                    uf.union(cur, next);
                }
            }
            res.add(uf.count);
        }
        return res;
    }

    class UnionFind {
        HashMap<Integer, Integer> father;
        int count;

        public UnionFind(int size) {
            father = new HashMap<Integer, Integer>();
            for (int i = 0; i < size; i++) {
                father.put(i, i);
            }
        }

        // compressed find, time: worst O({size}), average O(lg{size})
        public int find(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }
            // compress here
            int temp = -1;
            int fa = x;
            while (fa != father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            return parent;
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            if (xRoot != yRoot) {
                father.put(xRoot, yRoot);
                count--;
            }
        }
    }


    // union find with rank
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ret = new ArrayList<>();

        UnionFind<Integer> uf = new UnionFind<>();

        Set<Integer> lands = new HashSet<>();

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] pos : positions) {
            int x = pos[0];
            int y = pos[1];

            int land = x * n + y;

            uf.add(land);

            lands.add(land);

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                int newLand = nx * n + ny;

                if (!lands.contains(newLand)) {
                    continue;
                }

                uf.union(land, newLand);
            }

            ret.add(uf.count);
        }

        return ret;
    }

    public static class UnionFind<T> {
        private Map<T, T> parent;
        private Map<T, Integer> rank;
        int count;

        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
            count = 0;
        }

        public boolean add(T element) {
            if (!parent.containsKey(element)) {
                parent.put(element, element);
                rank.put(element, 0);
                count++;
                return true;
            }
            return false;
        }

        public T find(T element) {
            // either add or return null
            if (!parent.containsKey(element)) {
                add(element);
                return element;
            }

            T root = parent.get(element);
            if (root.equals(element)) {
                return element;
            }

            T absoluteRoot = find(root);

            // compress path
            parent.put(element, absoluteRoot);

            return absoluteRoot;
        }

        public boolean union(T a, T b) {
            T rootA = find(a);
            T rootB = find(b);

            if (rootA.equals(rootB)) {
                return false;
            }

            int rankA = rank.get(a);
            int rankB = rank.get(b);

            if (rankA > rankB) {
                // merge rootB to rootA
                parent.put(rootB, rootA);
            } else if (rankA > rankB) {
                // merge rootA to rootB
                parent.put(rootA, rootB);
            } else {
                // merge rootB to rootA
                parent.put(rootB, rootA);
                rank.put(rootA, rankA + 1);
            }

            count--;
            return true;
        }
    }
}
