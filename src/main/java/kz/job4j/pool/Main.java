package kz.job4j.pool;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();

        threadPool.work(new Thread(
                () -> System.out.println("work: " + 1)
        ));

        threadPool.work(new Thread(
                () -> System.out.println("work: " + 2)
        ));

        threadPool.work(new Thread(
                () -> System.out.println("work: " + 3)
        ));

        threadPool.shutdown();
    }
}
