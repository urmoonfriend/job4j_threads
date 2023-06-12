package kz.job4j.io.strategy.impl;

import kz.job4j.io.strategy.ContentStrategy;

import java.io.*;
import java.util.function.Predicate;

public class ContentStrategyImpl implements ContentStrategy {
    private File file;

    public ContentStrategyImpl(File file) {
        this.file = file;
    }

    @Override
    public String getContent(Predicate<Character> filter) {
        try (InputStream i = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(i)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = bis.read()) != -1) {
                char character = (char) data;
                if (filter.test(character)) {
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
