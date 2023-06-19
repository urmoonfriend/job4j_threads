package kz.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void setCount (int val) {
        count.set(val);
    }

    public void increment() {
        int oldVal = get();
        do {
            count.incrementAndGet();
        } while (!count.compareAndSet(get(), oldVal+1));

    }

    public int get() {
        return count.get();
    }
}
