package kz.job4j.concurrent;

import java.io.*;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String file = this.url;
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            long start = System.currentTimeMillis();

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            int timeToWait = 1024 / this.speed;
            Thread.sleep(timeToWait);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("finished : " +  timeElapsed + "ms");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int speed = 1;
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

}
