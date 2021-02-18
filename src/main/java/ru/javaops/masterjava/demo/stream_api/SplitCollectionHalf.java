package ru.javaops.masterjava.demo.stream_api;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitCollectionHalf {
    public static void main(String[] args) {
        Collection<String> animals = Arrays.asList(
                "Dog", "Cat", "Cow", "Bird", "Moose", "Pig");
        Collection<String> result1 = new ArrayList<>();
        Collection<String> result2 = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        int midpoint = Math.round(animals.size() / 2);

        animals.forEach(next -> {
            int index = count.getAndIncrement();
            if (index < midpoint) {
                result1.add(next);
            } else {
                result2.add(next);
            }
        });
        result1.forEach(System.out::println);
        result2.forEach(System.out::println);
       // assertTrue(result1.equals(Arrays.asList("Dog", "Cat", "Cow")));
       // assertTrue(result2.equals(Arrays.asList("Bird", "Moose", "Pig")));
    }
}
