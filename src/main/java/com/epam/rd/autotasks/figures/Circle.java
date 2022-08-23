package com.epam.rd.autotasks.figures;

class Circle extends Figure{

    private Point centerPoint;
    private double radius;
    Circle(Point centerPoint, double radius){
        this.centerPoint = centerPoint;
        this.radius = radius;

        if(area() <=0) throw new IllegalArgumentException();
    }

    @Override
    public double area()
    {
        return Math.PI*radius*radius;
    }

    @Override
    public Point centroid() {
        return null;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return false;
    }

    @Override
    public String pointsToString()
    {
        return centerPoint.toString();
    }

    @Override
    public String toString() {
        return "Circle["
                + centerPoint +
                + radius +
                ']';
    }
}
