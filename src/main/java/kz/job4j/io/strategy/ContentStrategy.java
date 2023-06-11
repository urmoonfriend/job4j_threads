package kz.job4j.io.strategy;

import java.io.IOException;

public interface ContentStrategy {
    String getContent() throws IOException;
}
