package com.linsizhe;

class UniquePathIII {

    private class AInt {
        public int total = 0;
    }

    public int uniquePathsIII(int[][] grid) {
        AInt total = new AInt();
        int totalEmpty = 0;
        int i = 0, j = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 1) {
                    i = x;
                    j = y;
                }
                if (grid[x][y] == 0) {
                    totalEmpty++;
                }
            }
        }
        System.out.println(totalEmpty);
        bt(grid, i, j, total, totalEmpty, 0);
        return total.total;
    }

    private void bt(int[][] grid, int x, int y, AInt total, int totalEmpty, int curStep) {
        if (grid[x][y] == 2) {
            System.out.println("haha!");
            System.out.println(curStep);
            if (curStep == totalEmpty + 1) {
                total.total += 1;
            }
            return;
        }
        if (grid[x][y] == -1) {
            return;
        }
        // Lesson: Do set & backtrack whenever before & after to recursion call!!!
        if (x - 1 >= 0) {
            int buf = grid[x][y];
            grid[x][y] = -1;
            bt(grid, x - 1, y, total, totalEmpty, curStep + 1);
            grid[x][y] = buf;
        }
        if (x + 1 < grid.length) {
            int buf = grid[x][y];
            grid[x][y] = -1;
            bt(grid, x + 1, y, total, totalEmpty, curStep + 1);
            grid[x][y] = buf;
        }
        if (y - 1 >= 0) {
            int buf = grid[x][y];
            grid[x][y] = -1;
            bt(grid, x, y - 1, total, totalEmpty, curStep  + 1);
            grid[x][y] = buf;
        }
        if (y + 1 < grid[0].length) {
            int buf = grid[x][y];
            grid[x][y] = -1;
            bt(grid, x, y + 1, total, totalEmpty, curStep + 1);
            grid[x][y] = buf;
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        UniquePathIII up = new UniquePathIII();
        System.out.println( up.uniquePathsIII(input));
    }
}