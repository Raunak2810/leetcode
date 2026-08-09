class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        Integer[][] memo = new Integer[n][n + 1];
        return dp(0, 1, suffixSum, memo, n);
    }
    private int dp(int i, int m, int[] suffixSum, Integer[][] memo,int n){
        if (i >= n) return 0;
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        if (memo[i][m] != null) return memo[i][m];
        int best = 0;
        for (int x = 1; x <= 2 * m; x++) {
            int nextM = Math.min(Math.max(m, x), n);
            int opponent = dp(i + x, Math.max(m, x), suffixSum, memo, n);
            int mine = suffixSum[i] - opponent;
            best = Math.max(best, mine);
        }
        memo[i][m] = best;
        return best;
    }
}