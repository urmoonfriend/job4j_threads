package kz.job4j.pool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MatrixDiagonalSum {

    public static int[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] sums = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        futures.put(0, getMainDiagonalTask(matrix, 0, n - 1, 0));
        for (int k = 1; k <= n; k++) {
            futures.put(k, getTask(matrix, 0, k - 1, k - 1));
            if (k < n) {
                futures.put(2 * n - k, getTask(matrix, n - k, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int startRow, int endRow, int startCol) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int sum = 0;
                    int col = startCol;
                    for (int i = startRow; i <= endRow; i++) {
                        sum += data[i][col];
                        col--;
                    }
                    return sum;
                }
        );
    }

    public static CompletableFuture<Integer> getMainDiagonalTask(int[][] data, int startRow, int endRow, int startCol) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int sum = 0;
                    int col = startCol;
                    for (int i = startRow; i <= endRow; i++) {
                        sum += data[i][col];
                        col++;
                    }
                    return sum;
                }
        );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 9, 3}, {4, 2, 6}, {8, 7, 5}};
        Arrays.stream(arr).forEach(
                row -> {
                    System.out.println(Arrays.toString(row));
                }
        );
        System.out.println();
        int[] result = asyncSum(arr);
        System.out.println(Arrays.toString(result));
    }
}
