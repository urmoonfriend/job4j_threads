package kz.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int i = 0;
        char[] process = new char[] {'-', '\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.printf("\r load: " + process[i]);
                i++;
                if (i == process.length) {
                    i = 0;
                }
            } catch (InterruptedException e) {
                System.out.printf("\r loaded !");
                Thread.currentThread().interrupt(); 
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
