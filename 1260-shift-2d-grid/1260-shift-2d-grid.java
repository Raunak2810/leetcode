class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k = k % total;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                int newIdx = (idx + k) % total;
                int newRow = newIdx / n;
                int newCol = newIdx % n;
                ans[newRow][newCol] = grid[i][j];
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++){
                row.add(ans[i][j]);
            }
            result.add(row);
        }
        return result;
    }
}