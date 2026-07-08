class Solution {

    static final int MOD = 1_000_000_007;

    class Node {
        long val;
        int sum;
        int len;

        Node() {}

        Node(long val, int sum, int len) {
            this.val = val;
            this.sum = sum;
            this.len = len;
        }
    }

    Node[] tree;
    long[] pow10;
    String s;

    public int[] sumAndMultiply(String s, int[][] queries) {
        this.s = s;
        int n = s.length();

        pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            Node res = query(1, 0, n - 1, queries[i][0], queries[i][1]);
            ans[i] = (int) ((res.val * res.sum) % MOD);
        }

        return ans;
    }

    private void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node();
            int d = s.charAt(l) - '0';
            if (d == 0) {
                tree[idx].val = 0;
                tree[idx].sum = 0;
                tree[idx].len = 0;
            } else {
                tree[idx].val = d;
                tree[idx].sum = d;
                tree[idx].len = 1;
            }
            return;
        }

        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private Node merge(Node a, Node b) {
        Node res = new Node();
        res.len = a.len + b.len;
        res.sum = a.sum + b.sum;
        res.val = (a.val * pow10[b.len] + b.val) % MOD;
        return res;
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) {
            return new Node(0, 0, 0);
        }

        if (ql <= l && r <= qr) {
            return tree[idx];
        }

        int mid = (l + r) / 2;

        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}