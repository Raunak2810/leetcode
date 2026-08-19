class Solution {
    private long[] prefix;
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n == 1) return 0;
        prefix = new long[n + 1];
        for (int i = 0; i < n; i++){
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        long[][] dp = new long[n][n];
        int[] pB = new int[n];
        long[] runningMaxB = new long[n];
        final long NEG = Long.MIN_VALUE;
        for (int j = 0; j < n; j++){
            pB[j] = j;
            runningMaxB[j] = NEG;
        }
        for (int i = n - 1; i >= 0; i--){
            int kA = i;
            long runningMaxA = NEG;
            for (int j = i + 1; j < n; j++){
                long total = S(i, j);

                // Advance left pointer (left-anchored splits: left <= right)
                while (kA < j && 2 * S(i, kA) <= total){
                    long cand = S(i, kA) + dp[i][kA];
                    if (cand > runningMaxA) runningMaxA = cand;
                    kA++;
                }
                // Advance right pointer (right-anchored splits: right <= left)
                while (pB[j] >= i + 1 && 2 * S(pB[j], j) <= total){
                    int m = pB[j];
                    long cand = S(m, j) + dp[m][j];
                    if (cand > runningMaxB[j]) runningMaxB[j] = cand;
                    pB[j]--;
                }
                dp[i][j] = Math.max(runningMaxA, runningMaxB[j]);
            }
        }
        return (int) dp[0][n - 1];
    }
    private long S(int a, int b) {
        return prefix[b + 1] - prefix[a];
    }
}