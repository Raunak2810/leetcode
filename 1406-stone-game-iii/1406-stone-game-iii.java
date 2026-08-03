class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1]; // dp[i] = best score difference achievable from stoneValue[i..n-1]
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--){
            int sum = 0;
            dp[i] = Integer.MIN_VALUE;
            for (int k = 1; k <= 3 && i + k <= n; k++){
                sum += stoneValue[i + k - 1];
                dp[i] = Math.max(dp[i], sum - dp[i + k]);
            }
        }
    
        if (dp[0] > 0) return "Alice";
        else if (dp[0] < 0) return "Bob";
        else return "Tie";
    }
}