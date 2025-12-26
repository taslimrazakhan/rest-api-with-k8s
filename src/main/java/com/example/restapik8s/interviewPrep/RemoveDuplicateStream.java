package com.example.restapik8s.interviewPrep;

import java.util.List;
import java.util.Objects;

public class RemoveDuplicateStream {
    public static void main(String[] args) {
        List<String> items = List.of("apple", "banana", "apple", "orange", "banana", "kiwi");

        List<String> list = items.stream().distinct().sorted().toList();
        System.out.println(list);
        List<Vehicle> cars = List.of(
                new Vehicle("Toyota", "Camry", 2020),
                new Vehicle("Honda", "Civic", 2020),
                new Vehicle("Toyota", "Mustang", 2020), // Same make as first Toyota - should be removed
                new Vehicle("Chevrolet", "Malibu", 2020)
        );

        List<Vehicle> distinctCarsList = cars.stream().distinct().toList();
        System.out.println(distinctCarsList);
    }
}
record Vehicle(String make, String model, int year) {
    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
    //override equals and hashcode for distinct by object property (by make)
    @Override
    public boolean equals(Object o) {   
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(year, vehicle.year());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(year);
    }
}