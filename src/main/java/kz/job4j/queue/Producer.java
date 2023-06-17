package kz.job4j.queue;

import java.util.Random;

public class Producer implements Runnable {
    private final SimpleBlockingQueue queue;

    public Producer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Integer i = new Random().nextInt(100);
            try {
                queue.offer(i);
                System.out.println("produce: " + i);
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
}
