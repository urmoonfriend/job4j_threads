package kz.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T val;
    private final int left;
    private final int right;

    public ParallelSearch(T[] array, T val, int left, int right) {
        this.array = array;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Integer compute() {
        if (right - left <= 10) {
            return searchWhenLinear();
        }
        int mid = (right + left) / 2;
        ParallelSearch leftSearch = new ParallelSearch(array, val, left, mid);
        ParallelSearch rightSearch = new ParallelSearch(array, val, mid + 1, right);
        leftSearch.fork();
        rightSearch.fork();
        int leftVal = (int) leftSearch.join();
        int rightVal = (int) rightSearch.join();

        return Math.max(leftVal, rightVal);
    }

    protected int searchWhenLinear() {
        for (int i = left; i < right; i++) {
            if (array[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

    public static int search(Object[] array, Object val) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (int) forkJoinPool.invoke(new ParallelSearch(array, val, 0, array.length));
    }
}
