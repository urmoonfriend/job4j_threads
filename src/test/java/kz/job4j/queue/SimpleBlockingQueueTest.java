package kz.job4j.queue;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    void when() throws InterruptedException {
        SimpleBlockingQueue queue = new SimpleBlockingQueue<Integer>(3);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join(5000);
        producerThread.join(5000);
    }

}
