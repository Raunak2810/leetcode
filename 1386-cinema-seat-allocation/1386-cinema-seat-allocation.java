class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        for (int[] rs : reservedSeats){
            int row = rs[0];
            int seat = rs[1];
            if (seat < 2 || seat > 9) continue; // seats 1 and 10 never block a block
            int bit = seat - 2; // seats 2..9 -> bits 0..7
            rowMask.merge(row, 1 << bit, (a, b) -> a | b);
        }
        final int LEFT  = 0b00001111; // seats 2,3,4,5
        final int MID   = 0b00111100; // seats 4,5,6,7
        final int RIGHT = 0b11110000; // seats 6,7,8,9
        int result = 0;
        int rowsWithReservations = rowMask.size();
        for (int mask : rowMask.values()){
            boolean leftFree = (mask & LEFT) == 0;
            boolean midFree = (mask & MID) == 0;
            boolean rightFree = (mask & RIGHT) == 0;
            if (leftFree && rightFree){
                result += 2;
            } else if (leftFree || midFree || rightFree){
                result += 1;
            }
        }
        long emptyRows = (long) n - rowsWithReservations;
        result += emptyRows * 2;
        return result;
    }
}