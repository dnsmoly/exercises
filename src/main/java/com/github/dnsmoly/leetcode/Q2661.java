package com.github.dnsmoly.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q2661 {
    public static void main(String[] args) {
        var arr1 = new int[]{1, 3, 4, 2};
        var mat1 = new int[][]{{1, 4}, {2, 3}};
//        System.out.println(firstCompleteIndex(arr1, mat1));

        var arr2 = new int[]{2, 8, 7, 4, 1, 3, 5, 6, 9};
        var mat2 = new int[][]{{3, 2, 5}, {1, 4, 6}, {8, 7, 9}};
//        System.out.println(firstCompleteIndex(arr2, mat2));

        // arr3 [1,4,5,2,6,3]
        var arr3 = new int[]{1, 4, 5, 2, 6, 3};
        //mat3 = [[4,3,5],[1,2,6]]
        var mat3 = new int[][]{{4, 3, 5},
                {1, 2, 6}};
//        System.out.println(firstCompleteIndex(arr3, mat3));

        // arr4 = [8,2,4,9,3,5,7,10,1,6]
        var arr4 = new int[]{8, 2, 4, 9, 3, 5, 7, 10, 1, 6};
        // mat4 = [[8,2,9,10,4],[1,7,6,3,5]]
        var mat4 = new int[][]{{8, 2, 9, 10, 4}, {1, 7, 6, 3, 5}};
//        System.out.println(firstCompleteIndex(arr4, mat4));
        // arr5 = [10,12,1,7,2,6,9,11,8,5,3,4]
        var arr5 = new int[]{10, 12, 1, 7, 2, 6, 9, 11, 8, 5, 3, 4};
        //mat5 = [[8,1,6,10],[5,9,2,4],[12,3,7,11]]
        var mat5 = new int[][]{{8, 1, 6, 10}, {5, 9, 2, 4}, {12, 3, 7, 11}};
        System.out.println(firstCompleteIndex(arr5, mat5));
        System.out.println("r2".hashCode() + " | " + "c2".hashCode());
    }
    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int numRows = mat.length;
        int numCols = mat[0].length;
        int[] rowCount = new int[numRows];
        int[] colCount = new int[numCols];
        Map<Integer, int[]> numToPos = new HashMap<>();

        // Create a map to store the position of each number in the matrix
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int value = mat[row][col];
                numToPos.put(value, new int[] { row, col });
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int[] pos = numToPos.get(num);
            int row = pos[0];
            int col = pos[1];

            // Increment the count for the corresponding row and column
            rowCount[row]++;
            colCount[col]++;

            // Check if the row or column is completely painted
            if (rowCount[row] == numCols || colCount[col] == numRows) {
                return i;
            }
        }

        return -1; // This line will never be reached as per the problem statement
    }

//    // SLOW
//    public static int firstCompleteIndex(int[] arr, int[][] mat) {
//        int rows = mat.length;
//        int cols = mat[0].length;
//        System.out.println("Rows: " + rows + " Cols: " + cols);
//        var frequency = new HashMap<String, Integer>();
//        var coordinates = new HashMap<Integer, Coordinates>();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                coordinates.put(mat[i][j], new Coordinates(i, j));
//            }
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            var coords = coordinates.get(arr[i]);
//            var rowCount = frequency.getOrDefault("r" + coords.row, 0);
//            var colCount = frequency.getOrDefault("c" + coords.col, 0);
//            System.out.println("i: " + i + " arr[i]: " + arr[i] + " Coords: " + coords + " Row r" + coords.row + ": " + rowCount + " Col c" + coords.col + ": " + colCount);
//            if (rowCount + 1 >= cols || colCount + 1 >= rows) {
//                System.out.println("Found. Rows: " + rowCount + " Cols: " + colCount);
//                return i;
//            }
//            System.out.println("Putting Row r" + coords.row + ": " + (rowCount + 1) + " Col c" + coords.col + ": " + (colCount + 1));
//            frequency.put("r" + coords.row, rowCount + 1);
//            frequency.put("c" + coords.col, rowCount + 1);
//        }
//        return 0;
//    }
//
//    public record Coordinates(int row, int col) {
//
//    }


}
