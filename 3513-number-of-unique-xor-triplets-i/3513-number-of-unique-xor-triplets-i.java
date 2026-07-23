class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // For n >= 3, all values from 0 to 2^bitLength - 1 are achievable,
        // where bitLength is the number of bits needed to represent n.
        int bitLength = 32 - Integer.numberOfLeadingZeros(n);
        return 1 << bitLength;
    }
}