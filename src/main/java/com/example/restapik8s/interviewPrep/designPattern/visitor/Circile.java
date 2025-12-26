package com.example.restapik8s.interviewPrep.designPattern.visitor;

public class Circile implements Shape {

    private int radius;

    public Circile(int radius) {
        this.radius = radius;
    }

    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }

    public int getRadius(){
        return radius;
    }
}
