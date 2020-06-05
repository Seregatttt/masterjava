package ru.csc.java2014.multithreading2.demo6;

import java.util.Arrays;

public class ParallelStream {

    public static void main(String[] args) {
        int[] array = Commons1.prepareArray();


        long startTime = System.currentTimeMillis();

        double sum = Arrays.stream(array)
                .parallel()
                .mapToDouble(Commons1::function)
                .sum();

        long endTime = System.currentTimeMillis();

        System.out.println("sum = " + sum);
        System.out.println("time = " + (endTime - startTime) + "ms");
    }
}
