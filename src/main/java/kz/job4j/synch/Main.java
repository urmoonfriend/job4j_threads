package kz.job4j.synch;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        list.add(1);
    }
}
