package com.linsizhe;


// Key Point: Sum = Right - up - left + leftup
// Attn: 1. use cumSum for calculation of cumSum array. So we have dynamics
// 2. for cumSum need to substract leftUup since it is calculated twice when do cur = left + up, leftup is calculated
// twice
public class RangeSumQuery2D {
    int[][] cumSum;

    public RangeSumQuery2D(int[][] matrix) {
        int[][] cumSum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                cumSum[i][j] += matrix[i][j];
                if (i - 1 >= 0) {
                    cumSum[i][j] += cumSum[i - 1][j];
                }
                if (j - 1 >= 0) {
                    cumSum[i][j] += cumSum[i][j - 1];
                }
                //  when do cur = left + up, leftup is calculated twice.
                if (i - 1 >= 0 && j - 1 >= 0) {
                    cumSum[i][j] -= cumSum[i - 1][j - 1];
                }
            }
        }
        this.cumSum = cumSum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int rowU = row1 - 1;
        int colU = col2;
        int rowL = row2;
        int colL = col1 - 1;

        int rowS = row1 - 1;
        int colS = col1 - 1;

        int sU = rowU < 0 ? 0 : cumSum[rowU][colU];
        int sL = colL < 0 ? 0 : cumSum[rowL][colL];
        int SS = rowS < 0 || colS < 0 ? 0 : cumSum[rowS][colS];

        return cumSum[row2][col2] - sU - sL + SS;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};
        int[][] matrix2 = {{-4, -5}};
        RangeSumQuery2D rq = new RangeSumQuery2D(matrix);
        System.out.println(rq.sumRegion(2, 1, 4, 3));
        System.out.println(rq.sumRegion(1, 1, 2, 2));
        System.out.println(rq.sumRegion(1, 2, 2, 4));

        RangeSumQuery2D rq2 = new RangeSumQuery2D(matrix2);
        System.out.println(rq2.sumRegion(0, 0, 0, 1));
        System.out.println(rq2.sumRegion(0, 0, 0, 0));
        System.out.println(rq2.sumRegion(0, 1, 0, 1));
    }

}
