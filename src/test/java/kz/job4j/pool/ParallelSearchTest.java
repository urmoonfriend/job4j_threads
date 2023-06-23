package kz.job4j.pool;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelSearchTest {
    @Test
    void whenRecursiveSearchIntegerArrayThenOk() {
        Integer[] arr = {1, 6, 5, 7, 9, 5, 2, 3, 5, 7, 9, 0, 18, 4, 8, 11, 13};
        ParallelSearch parallelSearch = new ParallelSearch(arr, 8, 0, arr.length);
        int result = parallelSearch.compute();
        assertThat(result).isEqualTo(14);
    }

    @Test
    void whenLinearSearchIntegerArrayThenOk() {
        Integer[] arr = {1, 6, 5, 7, 9, 5, 2};
        ParallelSearch parallelSearch = new ParallelSearch(arr, 7, 0, arr.length);
        int result = parallelSearch.compute();
        assertThat(result).isEqualTo(3);
    }

    @Test
    void whenRecursiveSearchStringArrayThenOk() {
        String[] arr = {"qwe", "ert", "tyu", "u", "hjk", "f", "a", "b", "l", "iop", "l;'", "xc", "sz", "tg", "yh", "uj", "ik"};
        ParallelSearch parallelSearch = new ParallelSearch(arr, "uj", 0, arr.length);
        int result = parallelSearch.compute();
        assertThat(result).isEqualTo(15);
    }

    @Test
    void whenLinearSearchStringArrayThenOk() {
        String[] arr = {"qwe", "ert", "tyu", "u", "hjk", "f", "a", "b", "l"};
        ParallelSearch parallelSearch = new ParallelSearch(arr, "tyu", 0, arr.length);
        int result = parallelSearch.compute();
        assertThat(result).isEqualTo(2);
    }

    @Test
    void whenRecursiveSearchDoubleArrayThenElementNotFound() {
        Double[] arr = {1.01, 1.4, 1.6, 7.0, 8.9, 7.5, 45.0, 5002.1, 45.8, 0.7, 7.8, 4.2};
        ParallelSearch parallelSearch = new ParallelSearch(arr, 111.9, 0, arr.length);
        int result = parallelSearch.compute();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenLinearSearchDoubleArrayThenElementNotFound() {
        Double[] arr = {1.01, 1.4, 1.6, 7.0, 8.9, 7.5};
        ParallelSearch parallelSearch = new ParallelSearch(arr, 111.9, 0, arr.length);
        int result = parallelSearch.compute();
        assertThat(result).isEqualTo(-1);
    }

}
