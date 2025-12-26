package com.example.restapik8s.interviewPrep.designPattern.builder;

import com.example.restapik8s.interviewPrep.designPattern.builder.Car.CarBuilder;

public class CarClient {

    public static void main(String[] args) {
        CarBuilder carBuilder= new Car.CarBuilder ();
        Car car = carBuilder.colour("Black").company("Toyota").wheels(4).build();

        System.out.println(car.getColour()+" "+car.getCompany()+" "+car.getWheels());
    }
}
