package kz.job4j.io;

import kz.job4j.io.strategy.ContentStrategy;

import java.io.*;

public final class ParseFile {
    private final File file;
    private final ContentStrategy contentStrategy;

    public ParseFile(File file, ContentStrategy contentStrategy) {
        this.file = file;
        this.contentStrategy = contentStrategy;
    }

    public synchronized String getContent() throws IOException {
        return contentStrategy.getContent();
    }
}
