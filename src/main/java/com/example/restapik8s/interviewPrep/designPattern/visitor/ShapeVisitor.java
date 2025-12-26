package com.example.restapik8s.interviewPrep.designPattern.visitor;

public interface ShapeVisitor {
    void visit(Circile circile);

    void visit(Rectangle rectangle);
}
