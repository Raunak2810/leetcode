class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] suf = new int[n + 1]; 
        for (int i = n - 1; i >= 0; i--){
            suf[i] = suf[i + 1];
            if (suf[i] < m && word1.charAt(i) == word2.charAt(m - 1 - suf[i])) {
                suf[i]++;
            }
        }
        int[] result = new int[m];
        int idx = 0, i = 0;
        boolean usedMismatch = false;
        while (i < n && idx < m) {
            if (word1.charAt(i) == word2.charAt(idx)){
                result[idx++] = i++;
            } else if (!usedMismatch && (m - idx - 1) <= suf[i + 1]){
                usedMismatch = true;
                result[idx++] = i++;
            } else{
                i++;
            }
        }
        return idx == m ? result : new int[0];
    }
}