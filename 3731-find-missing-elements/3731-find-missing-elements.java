class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> present = new HashSet<>();
        for (int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
            present.add(num);
        }
        List<Integer> missing = new ArrayList<>();
        for (int i = min; i <= max; i++){
            if (!present.contains(i)){
                missing.add(i);
            }
        }
        return missing;
    }
}