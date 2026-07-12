import java.util.*;
class Solution {
    public int longestBalanced(String s) {
        char[] cs = s.toCharArray();
        int one = calc1(cs);
        int two = Math.max(
                calc2(cs, 'a', 'b'),
                Math.max(calc2(cs, 'a', 'c'), calc2(cs, 'b', 'c'))
        );
        int three = calc3(cs);
        return Math.max(one, Math.max(two, three));
    }
    // Case 1: Only one distinct character
    private int calc1(char[] s) {
        int ans = 0;
        int i = 0;
        int n = s.length;
        while (i < n) {
            int j = i;
            while (j < n && s[j] == s[i]) {
                j++;
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }
    // Case 2: Exactly two distinct characters
    private int calc2(char[] s, char a, char b) {
        int ans = 0;
        int i = 0;
        int n = s.length;
        while (i < n) {
            while (i < n && s[i] != a && s[i] != b) {
                i++;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, i - 1);
            int diff = 0;
            while (i < n && (s[i] == a || s[i] == b)) {
                if (s[i] == a)
                    diff++;
                else
                    diff--;
                if (map.containsKey(diff)) {
                    ans = Math.max(ans, i - map.get(diff));
                } else {
                    map.put(diff, i);
                }
                i++;
            }
        }
        return ans;
    }
    // Case 3: All three characters
    private int calc3(char[] s) {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(hash(0, 0), -1);
        int[] cnt = new int[3];
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            cnt[s[i] - 'a']++;
            int x = cnt[0] - cnt[1];
            int y = cnt[1] - cnt[2];
            long key = hash(x, y);
            if (map.containsKey(key)) {
                ans = Math.max(ans, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        return ans;
    }
    private long hash(int x, int y) {
        return (((long) (x + 100000)) << 20) | (y + 100000);
    }
}