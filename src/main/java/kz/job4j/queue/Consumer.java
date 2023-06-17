package kz.job4j.queue;

public class Consumer implements Runnable {
    private final SimpleBlockingQueue queue;

    public Consumer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Integer i = (Integer) queue.poll();
                System.out.println("consume: " + i);
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
}
