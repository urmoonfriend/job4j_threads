package kz.job4j.pool;

import kz.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(3);

    public ThreadPool() {
        synchronized (this) {
            int size = Runtime.getRuntime().availableProcessors();
            System.out.println("size: " + size);
            for (int i = 0; i < size; i++) {
                int finalI = i;
                threads.add(
                        new Thread(
                                () -> {
                                    System.out.println("Thread: " + finalI);
                                    try {
                                        tasks.poll().run();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        Thread.currentThread().interrupt();
                                    }
                                }
                        )
                );
            }
        }
    }

    public void work(Runnable work) {
        synchronized (this) {
            try {
                tasks.offer(work);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown() {
        synchronized (this) {
            threads.stream().forEach(
                    thread -> {
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
            );
        }
    }
}
