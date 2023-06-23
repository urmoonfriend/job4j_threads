package kz.job4j.pool;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        //testThreadPool();
        testParallelMergeSort();
    }

    public static void testThreadPool() {
        ThreadPool threadPool = new ThreadPool();
        try {
            threadPool.work(new Thread(
                    () -> System.out.println("work: " + 1)
            ));
            threadPool.work(new Thread(
                    () -> System.out.println("work: " + 2)
            ));
            threadPool.work(new Thread(
                    () -> System.out.println("work: " + 3)
            ));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        threadPool.shutdown();
    }

    public static void testParallelMergeSort() {
        int[] arr = {1, 6, 5, 7, 9, 5, 2, 3, 5, 7, 9, 0};
        System.out.println("before");
        Arrays.stream(arr).forEach(
                value -> System.out.printf(value + " | ")
        );
        ParallelMergeSort parallelMergeSort =
                new ParallelMergeSort(arr, 0, arr.length - 1);
        System.out.println();
        System.out.println("after");
        Arrays.stream(parallelMergeSort.compute()).forEach(
                value -> System.out.printf(value + " | ")
        );
    }
}
