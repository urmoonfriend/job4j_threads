package kz.job4j.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        parallelStreamOrder();
    }

     public static void oneStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> multiplication = list.stream()
                .reduce((a, b) -> a * b);
        multiplication.ifPresentOrElse(
                result -> {
                    System.out.println(result);
                },
                () -> {
                    System.out.println("Multiplication error");
                }
        );
    }

    public static void parallelStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());
        Optional<Integer> multiplication = stream.reduce((a, b) -> a * b);
        System.out.println(multiplication.get());
    }

    public static void parallelStreamInOrder() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().parallel().peek(System.out::println).toList();
    }

    public static void parallelStreamOrder() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().parallel().forEachOrdered(System.out::println);
    }

}
