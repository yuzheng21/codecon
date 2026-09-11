class Solution {
    // solution: dfs + memo
    // Time: O(2^M * N * 26) where M is the length of target, N is the number of stickers
    // Space: O(2^M * M)
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickerCounts = new int[n][26];

        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickerCounts[i][c - 'a']++;
            }
        }
        
        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);

        return dfs(stickerCounts, target, memo);
    }

    private int dfs(int[][] stickerCounts, String target, Map<String, Integer> memo) {
        // covered by  memo.put("", 0)
        // if (target.isEmpty()) {
        //     return 0;
        // }

        if (memo.containsKey(target)) {
            return memo.get(target);
        }

        int[] targetCount = new int[26];
        for (char c : target.toCharArray()) {
            targetCount[c - 'a']++;
        }

        // -1 means not found
        int minStickers = -1;

        // try each sticker by decision: use it or skip it
        for (int[] sticker : stickerCounts) {
            // optimization: only try stickers that contain the first char of current target
            if (sticker[target.charAt(0) - 'a'] == 0) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            // greedily reduce the target chars using the current sticker
            for (int i = 0; i < 26; i++) {
                if (targetCount[i] > sticker[i]) {
                    int remaining = targetCount[i] - sticker[i];
                    // build next target
                    for (int k = 0; k < remaining; k++) {
                        sb.append((char) (i + 'a'));
                    }
                }
            }

            String nextTarget = sb.toString();
            int result = dfs(stickerCounts, nextTarget, memo);
            if (result != -1) {
                minStickers = minStickers == -1 ? 1 + result : Math.min(minStickers, 1 + result);
            }
        }

        memo.put(target, minStickers);
        return minStickers;
    }
}
