package com.example.restapik8s.interviewPrep.designPattern.visitor;

public class AreaShapeVisitor implements ShapeVisitor {

    @Override
    public void visit(Circile circile) {
        System.out.println("Circle area is ="+(3.41*3.41*circile.getRadius()));
    }

    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("Rectangle area is= "+(rectangle.getLength()*rectangle.getWidht()));
    }

}
