package com.example.restapik8s.interviewPrep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static List<Integer> mergeSort(List<Integer> list) {

        if (list.size() <= 1)
            return list;

        int mid = list.size() / 2;

        List<Integer> left = mergeSort(list.subList(0, mid));
        List<Integer> right = mergeSort(list.subList(mid, list.size()));
        

        return merge(left, right);
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        int i = 0, j = 0;
        ArrayList<Integer> sorted = new ArrayList<>();
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                sorted.add(left.get(i++));
            } else {
                sorted.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            sorted.add(left.get(i++));
        }

        while (j < right.size()) {
            sorted.add(right.get(j++));
        }

        return sorted;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(9, 3, 1, 5, 13, 12);
        List<Integer> sorted = mergeSort(numbers);
        System.out.println(sorted);
    }
}
