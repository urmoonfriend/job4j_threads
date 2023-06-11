package kz.job4j.io.strategy.impl;

import kz.job4j.io.strategy.ContentStrategy;

import java.io.*;

public final class ContentWithoutUniCode implements ContentStrategy {
    private final File file;

    public ContentWithoutUniCode(File file) {
        this.file = file;
    }

    @Override
    public synchronized String getContent() {
        try (InputStream i = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(i)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = bis.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error while reading file: " + file;
    }
}
