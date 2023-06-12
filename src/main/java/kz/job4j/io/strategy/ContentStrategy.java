package kz.job4j.io.strategy;

import java.io.IOException;
import java.util.function.Predicate;

public interface ContentStrategy {
    String getContent(Predicate<Character> filter) throws IOException;
}
