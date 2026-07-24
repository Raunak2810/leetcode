class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;
        boolean[] one = new boolean[MAX];
        boolean[] two = new boolean[MAX];
        boolean[] three = new boolean[MAX];
        for (int x : nums) {
            one[x] = true;
        }
        for (int a = 0; a < MAX; a++) {
            if (!one[a]) continue;
            for (int x : nums) {
                two[a ^ x] = true;
            }
        }
        for (int a = 0; a < MAX; a++) {
            if (!two[a]) continue;
            for (int x : nums) {
                three[a ^ x] = true;
            }
        }
        int ans = 0;
        for (boolean b : three) {
            if (b) ans++;
        }
        return ans;
    }
}