package kz.job4j.cas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CASCountTest {

    @Test
    public void whenIncrement() throws Exception {
        final CASCount casCount = new CASCount(1);
        Thread first = new Thread(
                () -> {
                    casCount.increment();
                }
        );
        first.start();

        Thread second = new Thread(
                () -> {
                    casCount.increment();
                }
        );
        second.start();

        first.join();
        second.interrupt();
        second.join();
        Assertions.assertThat(casCount.get()).isEqualTo(3);
    }

}
