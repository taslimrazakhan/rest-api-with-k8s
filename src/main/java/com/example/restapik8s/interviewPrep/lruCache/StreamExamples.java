package com.example.restapik8s.interviewPrep.lruCache;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExamples {
    private static Map<Character, Long> mapForChars;

    public static void main(String[] args) {
        String letteString = "AcBAcBDEaeF";
        String duplicateWords="Java is a programming language. Java is also an island of Indonesia. Java and JavaScript are different.";
       

        //mapForChars = letteString.chars().mapToObj(c->Character.toUpperCase(((char)c))).collect(Collectors.groupingBy(c->c, Collectors.counting()));

        //mapForChars.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).forEach(System.out::println);
        HashSet<String> set=new HashSet<>();
        HashSet<String> duplicates=new HashSet<>();
        String[] array = duplicateWords.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");

        List<String> duplicateWordsStrings = Arrays.stream(array).filter(w->!set.add(w) && duplicates.add(w)).toList();
        
        List<Integer> numbers = List.of(1, 2, 3);

        Integer sum = numbers.stream().sorted(Comparator.reverseOrder()).skip(2).collect(Collectors.summingInt(a->a));
        System.out.println(sum);
    }
}
