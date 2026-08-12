class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i+1< n && nums[i+1] == nums[i] +1){
            i++;
        }
        int sum = 0;
        for (int j=0; j<=i; j++){
            sum += nums[j];
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        while (set.contains(sum)){
            sum++;
        }
        return sum;  
    }
}