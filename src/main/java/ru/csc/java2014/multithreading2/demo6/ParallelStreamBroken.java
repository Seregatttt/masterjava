package ru.csc.java2014.multithreading2.demo6;

import java.util.Arrays;
import java.util.concurrent.atomic.DoubleAdder;

import static ru.csc.java2014.multithreading2.demo6.Commons1.*;

public class ParallelStreamBroken {

    public static void main(String[] args) {
        int[] array = prepareArray();


        long startTime = System.currentTimeMillis();

       // double[] sum = new double[1];
        DoubleAdder sum = new DoubleAdder();
        Arrays.stream(array)
                .parallel()
                .mapToDouble(Commons1::function)
               // .forEach(x -> sum[0] += x);
                .forEach(x -> sum.add(x));

        long endTime = System.currentTimeMillis();

        System.out.println("sum = " + sum);
        System.out.println("time = " + (endTime - startTime) + "ms");
    }
}
