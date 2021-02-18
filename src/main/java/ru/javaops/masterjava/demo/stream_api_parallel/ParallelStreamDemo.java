package ru.javaops.masterjava.demo.stream_api_parallel;

import ru.javaops.masterjava.demo.example_model.Employee;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamDemo {

    public static void main(String[] args) {

        long t1, t2;
        List<Employee> eList = new ArrayList<Employee>();
        for(int i=0; i<100; i++) {
            eList.add(new Employee("A", 20000));
            eList.add(new Employee("B", 3000));
            eList.add(new Employee("C", 15002));
            eList.add(new Employee("D", 7856));
            eList.add(new Employee("E", 200));
            eList.add(new Employee("F", 50000));
        }

        /***** Here We Are Creating A 'Sequential Stream' & Displaying The Result *****/
        t1 = System.currentTimeMillis();
        System.out.println("1Sequential Stream Count?= " + eList.stream().filter(e -> e.getSalary() > 15000).count());

        t2 = System.currentTimeMillis();
        System.out.println("2Sequential Stream Time Taken?= " + (t2-t1) + "\n");

        /***** Here We Are Creating A 'Parallel Stream' & Displaying The Result *****/
        t1 = System.currentTimeMillis();
        System.out.println("3Parallel Stream Count?= " + eList.parallelStream().filter(e -> e.getSalary() > 15000).count());

        t2 = System.currentTimeMillis();
        System.out.println("4Parallel Stream Time Taken?= " + (t2-t1));
    }
}