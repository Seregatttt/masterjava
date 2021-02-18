package ru.javaops.masterjava.demo.stream_api_parallel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ParallelGroupingDemo1 {

    public static void main(String[] args) {
        ParallelGroupingDemo1 pd = new ParallelGroupingDemo1();
        // getting list of employee
        List<Employee> empList = pd.createList();

        ConcurrentMap<Character, List<Employee>> bySalary = empList.parallelStream()
                .collect(Collectors.groupingByConcurrent(e -> e.sex));

        bySalary.forEach((K, V)->{
            System.out.println("Key- " + K + " Value :");
            V.forEach(v->System.out.println(v.name));
        });
    }

    // Stub method to create list of employee objects
    private List<Employee> createList(){
        List<Employee> empList = Arrays.asList(
                new Employee("E001", 40, "Ram", 'M', 5000),
                new Employee("E002", 35, "Sheila", 'F', 7000),
                new Employee("E003", 24, "Mukesh", 'M', 9000),
                new Employee("E004", 37, "Rani", 'F', 10000));
        return empList;
    }

    class Employee {
        private String empId;
        private int age;
        private String name;
        private char sex;
        private int salary;
        Employee(String empId, int age, String name, char sex, int salary){
            this.empId = empId;
            this.age = age;
            this.name = name;
            this.sex = sex;
            this.salary = salary;
        }
    }
}
