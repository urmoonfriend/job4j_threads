package kz.job4j.io;

import kz.job4j.io.strategy.impl.ContentStrategyImpl;

import java.io.File;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/file.txt");

            Predicate<Character> noUniCode = c -> c < 0x80;
            Predicate<Character> simple = c -> true;

            System.out.println("file writer test 1");
            WriteFile fw = new WriteFile(file);
            fw.saveContent("Hello, my name is Aidos!");

            System.out.println("simple test 1");
            ParseFile simpleParseFile1 = new ParseFile(file, new ContentStrategyImpl(file));
            System.out.println(simpleParseFile1.getContent(simple));

            System.out.println("without unicode test 1");
            ParseFile noUniCodeParseFile1 = new ParseFile(file, new ContentStrategyImpl(file));
            System.out.println(noUniCodeParseFile1.getContent(noUniCode));

            System.out.println();
            System.out.println("file writer test 2");
            fw.saveContent("Hello, unicode symbols : {é, ñ, ç}");

            System.out.println("simple test 2");
            ParseFile simpleParseFile2 = new ParseFile(file, new ContentStrategyImpl(file));
            System.out.println(simpleParseFile2.getContent(simple));

            System.out.println("without unicode test 2");
            ParseFile noUniCodeParseFile2 = new ParseFile(file, new ContentStrategyImpl(file));
            System.out.println(noUniCodeParseFile2.getContent(noUniCode));

            fw.saveContent("end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
