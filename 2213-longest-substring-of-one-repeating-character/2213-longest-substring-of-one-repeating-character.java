class Solution {
    int[] length, prefLen, sufLen, maxLen;
    char[] leftChar, rightChar;
    char[] arr;
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices){
        int n = s.length();
        arr = s.toCharArray();
        int size = 4 * n;
        length = new int[size];
        prefLen = new int[size];
        sufLen = new int[size];
        maxLen = new int[size];
        leftChar = new char[size];
        rightChar = new char[size];
        build(1, 0, n - 1);
        int k = queryCharacters.length();
        int[] res = new int[k];
        for (int i = 0; i < k; i++){
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            res[i] = maxLen[1];
        }
        return res;
    }
    private void build(int node, int l, int r){
        if (l == r) {
            length[node] = prefLen[node] = sufLen[node] = maxLen[node] = 1;
            leftChar[node] = rightChar[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        pull(node);
    }
    private void update(int node, int l, int r, int idx, char ch){
        if (l == r){
            arr[idx] = ch;
            leftChar[node] = rightChar[node] = ch;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx, ch);
        else update(2 * node + 1, mid + 1, r, idx, ch);
        pull(node);
    }
    private void pull(int node){
        int lc = 2 * node, rc = 2 * node + 1;
        length[node] = length[lc] + length[rc];
        leftChar[node] = leftChar[lc];
        rightChar[node] = rightChar[rc];
        if (prefLen[lc] == length[lc] && leftChar[rc] == leftChar[lc]){
            prefLen[node] = length[lc] + prefLen[rc];
        } else{
            prefLen[node] = prefLen[lc];
        }
        if (sufLen[rc] == length[rc] && rightChar[lc] == rightChar[rc]){
            sufLen[node] = length[rc] + sufLen[lc];
        } else{
            sufLen[node] = sufLen[rc];
        }
        int best = Math.max(maxLen[lc], maxLen[rc]);
        if (rightChar[lc] == leftChar[rc]) {
            best = Math.max(best, sufLen[lc] + prefLen[rc]);
        }
        maxLen[node] = best;
    }
}