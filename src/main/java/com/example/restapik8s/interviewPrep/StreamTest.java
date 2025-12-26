package com.example.restapik8s.interviewPrep;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamTest {
    
    public static void main(String[] args) {
        System.out.println("Service layer test class");

        List<Car> cars = new ArrayList<>(List.of(
            new Car("Toyota", "Camry", 2020),
            new Car("Honda", "Civic", 2019),
            new Car("Ford", "Mustang", 2021),
            new Car("Honda", "Hugus", 2021)
        ));
//String List of list
        List<List<String>> listOfLists = List.of(
            List.of("Apple", "Banana"),
            List.of(),
            List.of("Eggplant", "Fig", "Grape")
        );

        List<String> flattenedList = listOfLists.stream().flatMap(List::stream).toList();

        System.out.println("Flattened List: " + flattenedList);
//List of Optional 
        List<Optional<String>> optionalList = List.of(
            Optional.of("Hello"),
            Optional.empty(),
            Optional.of("World")
        );

        List<String> presentValues = optionalList.stream()
            .flatMap(n->n.stream())
            .toList();

        System.out.println("Present Values: " + presentValues);
    }

}
record Car(String make, String model, int year) {
    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}