package kz.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count;

    public CASCount(int val) {
        this.count = new AtomicInteger(val);
    }

    public void increment() {
        count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }
}
