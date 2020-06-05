package ru.csc.java2014.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo10 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "task1";
                    }
                },
                () -> "task2",
                () -> "task3");

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(x -> System.out.println("x="+x));
    }

}
