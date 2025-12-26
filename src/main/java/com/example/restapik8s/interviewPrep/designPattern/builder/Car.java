package com.example.restapik8s.interviewPrep.designPattern.builder;

public class Car {

    private String colour;
    private int wheels;
    private String company;

    private Car(CarBuilder carBuilder) {
        this.colour = carBuilder.colour;
        this.company = carBuilder.company;
        this.wheels = carBuilder.wheels;

    }

    public String getColour() {
        return this.colour;
    }

    public String getCompany() {
        return this.company;
    }

    public int getWheels() {
        return this.wheels;
    }

    public static class CarBuilder {
        private String colour;
        private int wheels;
        private String company;

        public CarBuilder colour(String color) {
            this.colour = color;
            return this;
        }

        public CarBuilder wheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder company(String company) {
            this.company = company;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

}
