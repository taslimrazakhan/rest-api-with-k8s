package com.example.restapik8s.interviewPrep.designPattern.visitor;

public class Rectangle implements Shape {

    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }

    public int getLength() {
        return length;
    }

    public int getWidht() {
        return width;
    }

}
