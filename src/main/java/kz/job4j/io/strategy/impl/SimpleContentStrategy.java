package kz.job4j.io.strategy.impl;

import kz.job4j.io.strategy.ContentStrategy;

import java.io.*;

public final class SimpleContentStrategy implements ContentStrategy {
    private final File file;

    public SimpleContentStrategy(File file) {
        this.file = file;
    }

    @Override
    public synchronized String getContent() {
        try (InputStream i = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(i)) {
            String output = "";
            int data;
            while ((data = bis.read()) > 0) {
                output += (char) data;
            }
            i.close();
            bis.close();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error while reading file: " + file;
    }
}
