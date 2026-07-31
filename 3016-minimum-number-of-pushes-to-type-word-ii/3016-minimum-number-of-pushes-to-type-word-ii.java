class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()){
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        int total = 0;
        int idx = 25;
        int rank = 0;
        while (idx >= 0 && freq[idx] > 0){
            int cost = (rank / 8) + 1;
            total += cost * freq[idx];
            rank++;
            idx--;
        }
        return total;
    }
}