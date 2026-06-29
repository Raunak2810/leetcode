import java.util.*;
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int max = 1;
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max + 1) {
                max = max + 1;
            } else {
                max = arr[i];
            }
        }
        return max;
    }
}
