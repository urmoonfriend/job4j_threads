package kz.job4j.io;

import kz.job4j.io.strategy.impl.ContentWithoutUniCode;
import kz.job4j.io.strategy.impl.SimpleContentStrategy;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/file.txt");

            System.out.println("file writer test 1");
            WriteFile fw = new WriteFile(file);
            fw.saveContent("Hello, my name is Aidos!");

            System.out.println("simple test 1");
            ParseFile simpleParseFile1 = new ParseFile(file, new SimpleContentStrategy(file));
            System.out.println(simpleParseFile1.getContent());

            System.out.println("without unicode test 1");
            ParseFile noUniCodeParseFile1 = new ParseFile(file, new ContentWithoutUniCode(file));
            System.out.println(noUniCodeParseFile1.getContent());

            System.out.println();
            System.out.println("file writer test 2");
            fw.saveContent("Hello, unicode symbols : {é, ñ, ç}");

            System.out.println("simple test 2");
            ParseFile simpleParseFile2 = new ParseFile(file, new SimpleContentStrategy(file));
            System.out.println(simpleParseFile2.getContent());

            System.out.println("without unicode test 2");
            ParseFile noUniCodeParseFile2 = new ParseFile(file, new ContentWithoutUniCode(file));
            System.out.println(noUniCodeParseFile2.getContent());

            fw.saveContent("end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
