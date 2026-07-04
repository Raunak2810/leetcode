class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads){
            int u = road[0];
            int v = road[1];
            int d = road[2];
            graph[u].add(new int[]{v, d});
            graph[v].add(new int[]{u, d});
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int u = queue.poll();
            for (int[] edge : graph[u]){
                int v = edge[0];
                int d = edge[1];
                ans = Math.min(ans, d);
                if (!visited[v]){
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        return ans;
    }
}