public class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                char ch = classroom[r].charAt(c);
                if (ch == 'S'){
                    startR = r;
                    startC = c;
                } else if (ch == 'L'){
                    litters.add(new int[]{r, c});
                }
            }
        }
        int numLitters = litters.size();
        int allCollected = (1 << numLitters) - 1;
        int[][] litterIndex = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(litterIndex[i], -1);
        for (int i = 0; i < numLitters; i++){
            litterIndex[litters.get(i)[0]][litters.get(i)[1]] = i;
        }
        int initialMask = 0;
        if (litterIndex[startR][startC] != -1){
            initialMask |= (1 << litterIndex[startR][startC]);
        }
        if (initialMask == allCollected){
            return 0;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, energy, initialMask, 0});
        int[][][] visited = new int[m][n][1 << numLitters];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                Arrays.fill(visited[i][j], -1);
            }
        }
        visited[startR][startC][initialMask] = energy;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], currEnergy = curr[2], mask = curr[3], moves = curr[4];
            if (currEnergy == 0) continue;
            for (int[] dir : directions){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X'){
                    int nextEnergy = currEnergy - 1;
                    char cellType = classroom[nr].charAt(nc);
                    if (cellType == 'R'){
                        nextEnergy = energy;
                    }
                    int nextMask = mask;
                    if (litterIndex[nr][nc] != -1){
                        nextMask |= (1 << litterIndex[nr][nc]);
                    }
                    if (nextMask == allCollected){
                        return moves + 1;
                    }           
                    if (nextEnergy > visited[nr][nc][nextMask]){
                        visited[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextEnergy, nextMask, moves + 1});
                    }
                }
            }
        }
        return -1;
    }
}