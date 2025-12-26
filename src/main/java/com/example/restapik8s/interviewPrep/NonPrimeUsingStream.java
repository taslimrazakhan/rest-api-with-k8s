package com.example.restapik8s.interviewPrep;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NonPrimeUsingStream {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //non prime numbers
         List<Integer> nonPrimeNumbers = numbers.stream().filter(n-> IntStream.rangeClosed(2, (int)Math.sqrt(n)).allMatch(i-> n % i !=0)).toList();
         System.out.println("Non Prime Numbers: " + nonPrimeNumbers);

         //Longest String in List
            List<String> words = List.of("apple", "banana", "cherry", "apricot", "blueberry", "coconut", "avocado");
            String longestWord = words.stream().max(Comparator.comparingInt(String::length)).get();
            System.out.println("Longest Word: " + longestWord);

        //moving average with window size 3
        int windowSize = 3;
        List<Double> movingAverages = IntStream.rangeClosed(0, numbers.size()-windowSize)
        .mapToDouble(n->numbers.subList(n, n+windowSize).stream().mapToInt(Integer::intValue).average().orElse(0))
        .boxed().toList();
        System.out.println("Moving Averages: " + movingAverages);

        //find all palindromic numbers 
        List<String> palindromes = words.stream().filter(n->n.equalsIgnoreCase(new StringBuffer(n).reverse().toString())).toList();
        //frequency of digits in a list of numbers
        List<Integer> digitList = List.of(121, 232, 345, 454, 565);
        List<Integer> digits = digitList.stream().flatMap(n->String.valueOf(n).chars().mapToObj(c->(char)c).map(c->Integer.parseInt(c.toString()))).toList();
  
        //

        //list of Employees
        List<Employee> employees = List.of(
            new Employee(5, List.of("Java", "Spring", "Docker")),
            new Employee(3, List.of("JavaScript", "React", "Node.js")),
            new Employee(4, List.of("Java", "Django", "Kubernetes")),
            new Employee(12, List.of("Python", "Java", "Kubernetes")),
            new Employee(7, List.of("Python", "Java", "Kubernetes")),
            new Employee(8, List.of("Python", "Java", "Kubernetes"))
        );
        // Group employees by experience level
        Map<String, List<Employee>> employeesByExperienceLevel = employees.stream()
            .collect(Collectors.groupingBy(e -> {
            if (e.experience() < 5) return "Junior";
            else if (e.experience() >= 5 && e.experience() < 10) return "Mid";
            else return "Senior";
            }));
        System.out.println("Employees grouped by experience level: " + employeesByExperienceLevel);
        Map<String, List<Employee>> employeeByLevel = employees.stream().filter(e->e.experience()>3 && e.skills.contains("Java")).collect(Collectors.groupingBy(e->e.experience()<5?"Junior":e.experience()>=5 && e.experience()<9?"Mid":"Senior"));
    System.out.println(employeeByLevel);
    }



  record Employee(int experience, List<String> skills) {
} 
}
