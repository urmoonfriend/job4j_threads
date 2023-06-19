package kz.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int limit;

    public SimpleBlockingQueue(int size) {
        this.limit = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == this.limit) {
                this.wait();
            }
            this.queue.offer(value);
            this.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == 0) {
                this.wait();
            }
            T result = this.queue.poll();
            this.notifyAll();
            return result;
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return this.queue.isEmpty();
        }
    }
}
