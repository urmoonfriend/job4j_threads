package kz.job4j.pool;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {

        }

        public Sums(int rowSum, int colSum) {
            this.colSum = colSum;
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public String toString() {
            return "{" + rowSum + ", " + colSum + "}";
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            sums[i] = new Sums(getSumByRow(matrix, i), getSumByCol(matrix, i));
        }
        return sums;
    }

    public static int getSumByRow(int[][] arr, int row) {
        int result = 0;
        for (int j = 0; j < arr.length; j++) {
            result += arr[row][j];
        }
        return result;
    }

    public static int getSumByCol(int[][] arr, int col) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i][col];
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < n; i++) {
            futures.put(i, getTaskByColAndRowNum(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTaskByColAndRowNum(int[][] data, int num) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int rowSum = getSumByRow(data, num);
                    int colSum = getSumByCol(data, num);
                    return new Sums(rowSum, colSum);
                }
        );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Arrays.stream(matrix).forEach(
                row -> {
                    System.out.println(Arrays.toString(row));
                }
        );
        System.out.println();
        Sums[] arr = asyncSum(matrix);
        System.out.println(Arrays.toString(arr));
        long endTime = System.currentTimeMillis();
        long neededTime = endTime - startTime;
        System.out.println("done at: " + neededTime + " ms.");
    }
}
