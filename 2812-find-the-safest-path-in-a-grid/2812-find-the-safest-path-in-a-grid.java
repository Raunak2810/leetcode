class Solution {
    int n;
    int[][] dist;
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    public int maximumSafenessFactor(List<List<Integer>> grid){
        n = grid.size();
        dist = new int[n][n];
        for (int i = 0; i < n; i++){
            Arrays.fill(dist[i], -1);
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (grid.get(i).get(j) == 1){
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }
        while (!q.isEmpty()){
            int[] curr = q.poll();
            for (int[] d : directions){
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];
                if (x >= 0 && y >= 0 && x < n && y < n 
                    && dist[x][y] == -1){
                    dist[x][y] = dist[curr[0]][curr[1]] + 1;
                    q.offer(new int[]{x, y});
                }
            }
        }
        int low = 0;
        int high = 2 * n;
        int ans = 0;
        while (low <= high){
            int mid = (low + high) / 2;
            if (isPossible(mid)){
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean isPossible(int safe){
        if (dist[0][0] < safe)
            return false;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0] = true;
        while (!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            if (r == n-1 && c == n-1)
                return true;
            for (int[] d : directions){
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nc >= 0 
                    && nr < n && nc < n
                    && !visited[nr][nc]
                    && dist[nr][nc] >= safe){
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr,nc});
                }
            }
        }
        return false;
    }
}