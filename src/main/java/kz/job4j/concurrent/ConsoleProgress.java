package kz.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    private int i = 0;
    private char[] process = new char[] {'-', '\\', '|', '/'};

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.printf("\r load: " + this.process[i]);
                this.i++;
                if (this.i == process.length) {
                    this.i = 0;
                }
            } catch (InterruptedException e) {
                ///e.printStackTrace();
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
