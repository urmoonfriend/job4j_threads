package kz.job4j.cas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CASCountTest {

    @Test
    public void whenIncrement() throws Exception {
        final CASCount casCount = new CASCount();
        casCount.setCount(1);

        Thread first = new Thread(
                () -> {
                    System.out.println("first increment: " + casCount.get() + " + 1 = " + (casCount.get() + 1));
                    casCount.increment();
                }
        );
        first.start();
        first.join();
        Thread second = new Thread(
                () -> {
                    System.out.println("second increment: " + casCount.get() + " + 1 = " + (casCount.get() + 1));
                    casCount.increment();
                }
        );
        second.start();
        second.join();

        Assertions.assertThat(casCount.get()).isEqualTo(3);
    }

}
