package kz.job4j.pool;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            sums[i] = getSums(matrix, i);
        }
        return sums;
    }

    private static Sums getSums(int[][] arr, int num) {
        int resultRow = 0, resultCol = 0;
        for (int j = 0; j < arr.length; j++) {
            resultRow += arr[num][j];
            resultCol += arr[j][num];
        }
        Sums newSums = new Sums(resultRow, resultCol);
        return newSums;
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

    private static CompletableFuture<Sums> getTaskByColAndRowNum(int[][] data, int num) {
        return CompletableFuture.supplyAsync(
                () -> getSums(data, num)
        );
    }

}
