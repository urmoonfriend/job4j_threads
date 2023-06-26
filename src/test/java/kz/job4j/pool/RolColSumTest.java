package kz.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

public class RolColSumTest {

    @Test
    public void whenSyncSumThenOk() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum calcSum = new RolColSum();
        Sums[] sums = calcSum.sum(matrix);
        assertThat(sums.length).isEqualTo(3);
        Sums[] correctSums = {new Sums(6, 12), new Sums(15, 15), new Sums(24, 18)};
        for (int i = 0; i < correctSums.length; i++) {
            assertThat(sums[i]).isEqualTo(correctSums[i]);
        }
    }

    @Test
    public void whenAsyncSumThenOk() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum calcSum = new RolColSum();
        Sums[] sums = calcSum.asyncSum(matrix);
        assertThat(sums.length).isEqualTo(3);
        Sums[] correctSums = {new Sums(6, 12), new Sums(15, 15), new Sums(24, 18)};
        for (int i = 0; i < correctSums.length; i++) {
            assertThat(sums[i]).isEqualTo(correctSums[i]);
        }
    }
}
