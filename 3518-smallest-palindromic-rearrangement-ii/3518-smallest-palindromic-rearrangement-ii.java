class Solution {
    public String smallestPalindrome(String s, int k) {
        final long CAP = 2_000_000L; 
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        int half = n / 2;
        int oddChar = -1;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) oddChar = i;
            halfCount[i] = count[i] / 2;
        }

        long totalPerms = countPermutations(halfCount, half, CAP);
        if (totalPerms < k) return "";

        StringBuilder halfBuilder = new StringBuilder();
        int remaining = half;
        long kk = k;

        for (int pos = 0; pos < half; pos++) {
            for (int c = 0; c < 26; c++) {
                if (halfCount[c] == 0) continue;
                halfCount[c]--;
                long perms = countPermutations(halfCount, remaining - 1, CAP);
                if (perms >= kk) {
                    halfBuilder.append((char) ('a' + c));
                    remaining--;
                    break;
                } else {
                    kk -= perms;
                    halfCount[c]++;
                }
            }
        }
        String halfStr = halfBuilder.toString();
        StringBuilder result = new StringBuilder();
        result.append(halfStr);
        if (oddChar != -1) result.append((char) ('a' + oddChar));
        result.append(new StringBuilder(halfStr).reverse());

        return result.toString();
    }
    private long countPermutations(int[] cnt, int totalLen, long cap){
        long result = 1;
        int remaining = totalLen;

        for (int i = 0; i < 26; i++) {
            int c = cnt[i];
            if (c == 0) continue;

            for (int j = 1; j <= c; j++) {
                result = result * (remaining - c + j) / j;
                if (result > cap) return cap + 1; 
            }
            remaining -= c;
        }
        return result;
    }
}