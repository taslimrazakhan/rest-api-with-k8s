package com.example.restapik8s.interviewPrep.designPattern.visitor;

public interface Shape {
    void accept(ShapeVisitor visitor);
}
