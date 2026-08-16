class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean hasNonZero = false;
        for (int x : nums){
            totalXor ^= x;
            if (x != 0) hasNonZero = true;
        }
        if (totalXor != 0){
            return n;
        }
        if (hasNonZero){
            return n - 1;
        }
        return 0;       
    }
}