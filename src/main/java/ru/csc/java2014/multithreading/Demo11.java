package ru.csc.java2014.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Demo11 {
    static int count = 0;

    public static void increment() {
        System.out.println("count =" + count + " Thread" + Thread.currentThread().getName());
        count = count + 1;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream.range(0, 10)
                .forEach(i -> {
                    executor.submit(() -> increment());
                });

        stop(executor);

        System.out.println(count);  // 9965
    }

    static void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

}
