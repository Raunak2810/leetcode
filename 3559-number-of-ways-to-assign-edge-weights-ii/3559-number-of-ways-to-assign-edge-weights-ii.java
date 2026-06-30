import java.util.*;
class Solution {
    static final long MOD = 1000000007L;
    static int LOG = 17;
    ArrayList<Integer>[] graph;
    int[][] up;
    int[] depth;
    long[] pow2;
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        up = new int[n + 1][LOG];
        depth = new int[n + 1];
        dfs(1, 0);
        // Binary lifting table
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        // powers of 2
        pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int lca = getLCA(u, v);
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            if (dist == 0)
                ans[i] = 0;
            else
                ans[i] = (int) pow2[dist - 1];
        }
        return ans;
    }
    void dfs(int node, int parent) {
        up[node][0] = parent;
        for (int child : graph[node]) {
            if (child != parent) {
                depth[child] = depth[node] + 1;
                dfs(child, node);
            }
        }
    }
    int getLCA(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        int diff = depth[a] - depth[b];
        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0)
                a = up[a][i];
        }
        if (a == b)
            return a;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[a][i] != up[b][i]) {
                a = up[a][i];
                b = up[b][i];
            }
        }
        return up[a][0];
    }
}