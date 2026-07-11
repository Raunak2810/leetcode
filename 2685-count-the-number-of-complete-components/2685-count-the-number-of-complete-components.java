class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    int nodes;
    int edgeCount;
    public int countCompleteComponents(int n, int[][] edges) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n;i++){
            if (!visited[i]){
                nodes = 0;
                edgeCount = 0;
                dfs(i);
                int actualEdges = edgeCount / 2;
                int requiredEdges = nodes * (nodes - 1) / 2;
                if (actualEdges == requiredEdges){
                    ans++;
                }
            }
        }
        return ans;
    }
    private void dfs(int node){
        visited[node] = true;
        nodes++;
        edgeCount += graph[node].size();
        for (int nei : graph[node]){
            if (!visited[nei]){
                dfs(nei);
            }
        }
    }
}