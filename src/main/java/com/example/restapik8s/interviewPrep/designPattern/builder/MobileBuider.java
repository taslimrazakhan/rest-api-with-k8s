package com.example.restapik8s.interviewPrep.designPattern.builder;

import java.util.List;

public class MobileBuider {
    public static void main(String[] args) {
        Mobile mobile = new Mobile.Builder().brand("Samsung").price(2000).features(List.of("Wide Angel")).build();
        System.out.println(mobile);
    }
}
