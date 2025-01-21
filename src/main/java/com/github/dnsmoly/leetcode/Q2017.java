package com.github.dnsmoly.leetcode;

public class Q2017 {
    public static void main(String[] args) {
        // grid = [[2,5,4],[1,5,1]]
        var grid1 = new int[][]{{2, 5, 4}, {1, 5, 1}};
//        gridGame(grid1);
        // grid = [[1,3,1,15],[1,3,3,1]]
        var grid2 = new int[][]{{1, 3, 1, 15}, {1, 3, 3, 1}};
//        gridGame(grid2);
        // grid = [[20,3,20,17,2,12,15,17,4,15],[20,10,13,14,15,5,2,3,14,3]]
        var grid3 = new int[][]{{20, 3, 20, 17, 2, 12, 15, 17, 4, 15}, {20, 10, 13, 14, 15, 5, 2, 3, 14, 3}};
        System.out.println(gridGame(grid3));
    }

    public static long gridGame(int[][] grid) {
        long firstRowSum = 0;
        for(int i = 0; i < grid[0].length;i++) {
            firstRowSum += grid[0][i];
        }
        long secondRowSum = 0;
        long result = Long.MAX_VALUE;
        for (int i = 0; i < grid[0].length; i++) {
            firstRowSum -= grid[0][i];
            result = Math.min(result, Math.max(firstRowSum, secondRowSum));
            secondRowSum += grid[1][i];
        }
        return result;
    }
}
