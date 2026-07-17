class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;
        long[] exact = new long[max + 1];
        int[] divisible = new int[max + 1];
        // Count numbers divisible by each g
        for (int g = 1; g <= max; g++) {
            for (int j = g; j <= max; j += g) {
                divisible[g] += freq[j];
            }
        }
        // Inclusion-Exclusion
        for (int g = max; g >= 1; g--) {
            long cnt = divisible[g];
            exact[g] = cnt * (cnt - 1) / 2;

            for (int j = g + g; j <= max; j += g) {
                exact[g] -= exact[j];
            }
        }
        // Prefix counts
        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exact[g];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1;
            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) / 2;
                if (prefix[mid] >= target)
                    r = mid;
                else
                    l = mid + 1;
            }
            ans[i] = l;
        }
        return ans;
    }
}