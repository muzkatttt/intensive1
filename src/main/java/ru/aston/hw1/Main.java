package ru.aston.hw1;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        var kate = new KateRodionova<Number>();

        kate.add(11);
        kate.add(0, 2);
        kate.add(13);
        kate.add(14);
        kate.add(52);
        kate.add(63);
        kate.add(71);
        kate.add(18);
        kate.add(19);
        kate.add(10);
        kate.add(11);


        System.out.println(kate.size());
        System.out.println(kate);

        System.out.println(kate.get(0));
        System.out.println(kate.isSorted());
        kate.set(3, 3);
        System.out.println(kate.get(3));
        System.out.println(kate);

        kate.remove(4);
        System.out.println(kate);
//
//        kate.clear();
//        System.out.println(kate);

        kate.quickSort(Comparator.comparingInt(Number::intValue));
        System.out.println(kate);
        System.out.println(kate.isSorted());


        kate.split(5);
        System.out.println(kate);
    }
}
