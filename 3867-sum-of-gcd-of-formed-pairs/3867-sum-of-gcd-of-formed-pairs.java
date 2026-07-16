class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        int mx = 0;
        for (int i = 0; i < n;i++){
            mx = Math.max(mx, nums[i]);
            arr[i] = gcd(nums[i], mx);
        }
        Arrays.sort(arr);
        long ans = 0;
        int l = 0, r = n - 1;
        while (l < r){
            ans += gcd(arr[l], arr[r]);
            l++;
            r--;
        }
        return ans;
    }
    private int gcd(int a, int b){
        while (b != 0){
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}