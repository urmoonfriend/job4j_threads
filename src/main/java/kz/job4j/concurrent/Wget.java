package kz.job4j.concurrent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final double speed;
    private final String fileName;

    public Wget(String url, double speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        String file = this.url;
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(this.fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int dowloadData = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                dowloadData += bytesRead;
                if (dowloadData >= speed) {
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    if (timeElapsed < 1000) {
                        Thread.sleep(1000 - timeElapsed);
                        start = System.currentTimeMillis();
                        dowloadData = 0;
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkURLExists(String urlToCheck) {
        try {
            URL url = new URL(urlToCheck);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkValidSpeed(String speed) {
        try {
            Double.parseDouble(speed);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean canCreateFile(String filename) {
        File file = new File(filename);
        try {
            boolean created = file.createNewFile();
            file.delete();
            return created;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean areValidArgs(String[] args) {
        boolean result = false;
        if (args.length == 3) {
            if (checkURLExists(args[0]) && checkValidSpeed(args[1]) && canCreateFile(args[2])) {
                result = true;
            } else {
                System.out.println("Incorrect Params");
            }
        } else {
            System.out.println("Give me 3 params: (url speed fileName)");
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        if (areValidArgs(args)) {
            long start = System.currentTimeMillis();
            String url = args[0];
            double speed = Double.parseDouble(args[1]);
            String fileName = args[2];
            Thread wget = new Thread(new Wget(url, speed, fileName));
            wget.start();
            wget.join();
            long finish = System.currentTimeMillis();
            System.out.println("finished : " + (finish - start) + " ms");
        }
    }

}
