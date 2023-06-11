package kz.job4j.io;

import java.io.*;

public final class WriteFile {
    private final File file;

    public WriteFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(o)) {
            for (int i = 0; i < content.length(); i += 1) {
                bos.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
