package kz.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(1000);
                System.out.print("\rLoading : " + i  + "%");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
