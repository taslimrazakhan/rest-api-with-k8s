package com.example.restapik8s.interviewPrep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SecondHighesSalaryByStream {
  private static Iterator<String> iterator;

  public static void main(String[] args) {

    List<Employee> empList = new ArrayList<>();
    empList.add(new Employee(1, "Akash", 28, 123, "F", "HR", "Blore", 2020));
    empList.add(new Employee(2, "Sonu", 29, 120, "F", "HR", "Hyderabad", 2015));
    empList.add(new Employee(3, "Nigam", 30, 115, "M", "HR", "Chennai", 2014));
    empList.add(new Employee(4, "Azra", 32, 125, "F", "HR", "Chennai", 2013));
    empList.add(new Employee(5, "Sonu", 22, 150, "F", "IT", "Noida", 2013));
    empList.add(new Employee(6, "Akash", 27, 140, "M", "IT", "Gurugram", 2017));
    empList.add(new Employee(7, "Taleem", 26, 130, "F", "IT", "Pune", 2016));
    empList.add(new Employee(8, "John", 23, 145, "M", "IT", "Trivandam", 2015));
    empList.add(new Employee(9, "Jenny", 25, 160, "M", "IT", "Blore", 2010));

    String word="Mississyppi";
    Character character = word.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(entry->entry.getKey()).get();
    System.out.println(character);
  }
}

record Employee(int id, String name, int age, long salary, String gender,
    String deptName, String city, int yearOfJoining) {

  // equals and hashcode methods
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Employee employee = (Employee) o;
    if (employee.name == this.name)
      return true;
    else
      return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

}