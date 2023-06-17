package kz.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int limit;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.limit = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == this.limit) {
                this.wait();
            }
            if (this.queue.size() == 0) {
                this.notifyAll();
            }
            this.queue.offer(value);
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == 0) {
                this.wait();
            }
            if (this.queue.size() == this.limit) {
                this.notifyAll();
            }
            return this.queue.poll();
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            while (this.queue.size() == 0) {
                return true;
            }
            return false;
        }
    }
}
