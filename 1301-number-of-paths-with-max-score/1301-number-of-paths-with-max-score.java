class Solution {
    public int[] pathsWithMaxScore(List<String> board){
                int n = board.size();
        int MOD = 1_000_000_007;
        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];
        for (int i = 0; i < n;i++){
            Arrays.fill(score[i], -1);
        }
        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0;i--){
            for (int j = n - 1; j >= 0;j--){
                if (board.get(i).charAt(j) == 'X') continue;
                if (i == n - 1 && j == n - 1) continue;
                int best = -1;
                int cnt = 0;
                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];

                    if (ni >= n || nj >= n) continue;
                    if (score[ni][nj] == -1) continue;

                    if (score[ni][nj] > best) {
                        best = score[ni][nj];
                        cnt = ways[ni][nj];
                    } else if (score[ni][nj] == best) {
                        cnt = (cnt + ways[ni][nj]) % MOD;
                    }
                }
                if (best == -1) continue;
                char c = board.get(i).charAt(j);
                int val = (c == 'E' || c == 'S') ? 0 : c - '0';
                score[i][j] = best + val;
                ways[i][j] = cnt;
            }
        }
        if (ways[0][0] == 0) return new int[]{0, 0};
        return new int[]{score[0][0], ways[0][0]};
    }
}