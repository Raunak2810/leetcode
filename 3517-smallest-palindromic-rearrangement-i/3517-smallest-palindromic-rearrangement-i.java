class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()){
            count[c - 'a']++;
        }
        int n = s.length();
        StringBuilder half = new StringBuilder();
        char mid = ' ';
        for (int i = 0; i < 26; i++){
            char c = (char) ('a' + i);
            int freq = count[i];
            if (freq % 2 == 1){
                mid = c;
            }
            for (int j = 0; j < freq / 2; j++){
                half.append(c);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(half);
        if (mid != ' '){
            result.append(mid);
        }
        result.append(half.reverse());
        return result.toString();
    }
}