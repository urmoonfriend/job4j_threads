package kz.job4j.buffer;

import kz.job4j.queue.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            System.out.println("stop consumer");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println("stop producer");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        ).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        consumer.interrupt();
    }
}
