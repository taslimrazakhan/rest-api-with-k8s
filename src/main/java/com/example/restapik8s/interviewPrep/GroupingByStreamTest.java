package com.example.restapik8s.interviewPrep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupingByStreamTest {

    public static void main(String[] args) {
        // Sample data for grouping demonstration
        List<String> words = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry", "coconut", "avocado");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 1, 5, 3, 6, 1, 7, 8, 8, 9, 10);
        words.stream().collect(Collectors.groupingBy(w -> w.charAt(0)))
                .forEach((key, value) -> System.out.println("Key: " + key + " Value: " + value));

        List<Dog> dogs = new ArrayList<>(List.of(
                new Dog("Labrador", "Bella", 600),
                new Dog("Labrador", "Buddy", 500),
                new Dog("Beagle", "Charlie", 300),
                new Dog("Beagle", "Max", 700)));

        // k most repeated numbers
        int k = 2;
        numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()).limit(k).forEach(System.out::println);

        // Custom collector to print statistics of String
        IntSummaryStatistics summary = words.stream().collect(Collectors.summarizingInt(String::length));

        // Batch processing of elements
        AtomicInteger batchSize = new AtomicInteger(2);
        words.stream().collect(Collectors.groupingBy(i -> batchSize.getAndIncrement() / 2, LinkedHashMap::new,
                Collectors.toList())).values();
        // group by multiple fields
        dogs.stream().collect(Collectors.groupingBy(e -> Arrays.asList(e.breed(), e.price())))
                .forEach((key, value) -> System.out.println("Key: " + key + " Value: " + value));
        // common elements in two lists
        List<String> list1 = List.of("apple", "banana", "cherry", "date");
        List<String> list2 = List.of("banana", "date", "fig", "grape");
        list1.stream().filter(list2::contains).toList();

        // calculate the product of all integers in a list
        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        int product = intList.stream().reduce(1, (a, b) -> a * b);

        // partitioning by even and odd numbers
        Map<Boolean, List<Dog>> partitioned = dogs.stream()
                .collect(Collectors.partitioningBy(d -> d.breed().equals("Labrador")));

        // Group books by Auther and then by Genre
        List<Book> books = List.of(
            new Book("J.K. Rowling", "Fantasy", "Harry Potter and the Philosopher's Stone", "29.99"),
            new Book("J.K. Rowling", "Fantasy", "Harry Potter and the Chamber of Secrets", "29.99"),
            new Book("George Orwell", "Dystopian", "1984", "19.99"),
            new Book("George Orwell", "Political Fiction", "Animal Farm", "15.99"),
            new Book("J.R.R. Tolkien", "Fantasy", "The Hobbit", "24.99"),
            new Book("J.R.R. Tolkien", "Fantasy", "The Lord of the Rings", "39.99"),
            new Book("Harper Lee", "Classic", "To Kill a Mockingbird", "18.99"),
            new Book("F. Scott Fitzgerald", "Classic", "The Great Gatsby", "16.99")
        );
        
        // Group books by Author and then by Genre
        Map<String, Map<String, List<Book>>> booksByAuthorAndGenre = books.stream()
            .collect(Collectors.groupingBy(Book::author, 
                Collectors.groupingBy(Book::genre)));
        System.out.println(booksByAuthorAndGenre);
       
        booksByAuthorAndGenre.forEach((author, genreMap) -> {

            System.out.println(author);
            genreMap.forEach((genre,bookMap)->{
                System.out.println("  " + genre);
                bookMap.forEach(book->System.out.println("    " + book));   
            });
    }); 

    //non prime numbers
     List<Integer> nonPrimeNumbers =
    numbers.stream().filter(n-> IntStream.rangeClosed(2, (int)Math.sqrt(n)).allMatch(i-> n % i !=0)).toList();

    IntStream.rangeClosed(2,    10).forEach(System.out::println);
}        
}

record Dog(String breed, String name, int price) {
    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
record Book(String author, String genre,String name, String price) {
}
