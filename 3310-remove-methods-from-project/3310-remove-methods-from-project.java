class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }
        boolean[] suspicious = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        suspicious[k] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }
        for (int[] inv : invocations) {
            int a = inv[0], b = inv[1];
            if (suspicious[b] && !suspicious[a]) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) result.add(i);
        }
        return result;
    }
}