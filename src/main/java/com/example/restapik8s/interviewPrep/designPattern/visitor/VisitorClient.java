package com.example.restapik8s.interviewPrep.designPattern.visitor;

import java.util.List;

public class VisitorClient {

    public static void main(String[] args) {
        List<Shape> list = List.of(new Circile(2), new Rectangle(2, 3));
        AreaShapeVisitor visitor = new AreaShapeVisitor();
        for (Shape shape : list) {
            shape.accept(visitor);
        }
    }
}
