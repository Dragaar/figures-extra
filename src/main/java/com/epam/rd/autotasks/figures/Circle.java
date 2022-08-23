package com.epam.rd.autotasks.figures;

class Circle extends Figure{

    private Point centerPoint;
    private double radius;
    Circle(Point centerPoint, double radius){
        this.centerPoint = centerPoint;
        this.radius = radius;

        if(centerPoint == null || radius <=0) throw new IllegalArgumentException();
        if(area() <=0) throw new IllegalArgumentException();
    }

    @Override
    public double area()
    {
        return Math.PI*radius*radius;
    }

    @Override
    public Point centroid() {
        return centerPoint;
    }

    @Override
    public boolean isTheSame(Figure figure) {
       if(figure instanceof Circle)
       {
           Point centr2 = ((Circle) figure).centerPoint;
           double radius2 = ((Circle) figure).radius;
           // System.out.println("Cent1 = " + this.centerPoint + "Cent2 = " + centr2 + "R1 = " + this.radius + " R2 = " + radius2);
           if((centr2.equals(this.centerPoint)) && (deltaCompare(radius2, this.radius, 0.001)))
           {
               return true;
           }
       }
       return false;
    }

    private static boolean deltaCompare(double v1, double v2, double delta) {
        //https://www.baeldung.com/java-comparing-doubles
        return Math.abs(v1 - v2) < delta;
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
