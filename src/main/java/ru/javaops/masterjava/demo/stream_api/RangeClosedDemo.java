package ru.javaops.masterjava.demo.stream_api;

import java.util.stream.IntStream;

public class RangeClosedDemo {

    public static void main(String[] args) {

        // Создание IntStream
        //IntStream: последовательность примитивных int-значных элементов.
        //startInclusive: включающее начальное значение.
        //endInclusive: включающая верхняя граница
        IntStream stream = IntStream.rangeClosed(-4, 3);

        stream.forEach(System.out::println);
    }
}
