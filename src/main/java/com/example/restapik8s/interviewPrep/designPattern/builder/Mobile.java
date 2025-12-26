package com.example.restapik8s.interviewPrep.designPattern.builder;

import java.util.List;

public record Mobile(String brand, int price, List<String> features) {

    public Mobile {
        features = List.copyOf(features);
    }
    

    public static class Builder {
        private String brand;
        private int price;
        private List<String> features;

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder features(List<String> features) {
            this.features = List.copyOf(features);
            return this;
        }

        public Mobile build() {

            return new Mobile(this.brand, this.price, this.features);
        }

    }
}
