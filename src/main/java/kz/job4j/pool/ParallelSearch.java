package kz.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {
    private final Object[] array;
    private final Object val;
    private final int left;
    private final int right;

    public ParallelSearch(Object[] array, Object val, int left, int right) {
        this.array = array;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Integer compute() {
        if (right <= left) {
            return -1;
        }
        if (right - left <= 10) {
            for (int i = left; i < right; i++) {
                if (array[i].equals(val)) {
                    return i;
                }
            }
        }
        int mid = (right + left) / 2;
        ParallelSearch leftSearch = new ParallelSearch(array, val, left, mid);
        ParallelSearch rightSearch = new ParallelSearch(array, val, mid + 1, right);
        leftSearch.fork();
        rightSearch.fork();
        int leftVal = leftSearch.join();
        int rightVal = rightSearch.join();

        return getPositiveIfPresentOrGetMinusOne(leftVal, rightVal);
    }

    protected Integer getPositiveIfPresentOrGetMinusOne(int leftVal, int rightVal) {
        if (leftVal == -1) {
            return rightVal;
        } else {
            return leftVal;
        }
    }
}
