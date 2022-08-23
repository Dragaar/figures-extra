package com.epam.rd.autotasks.figures;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.cos;
import static java.lang.Math.acos;
class Triangle extends Figure{

    private Point a1; private Point b1; private Point c1;

    double x11; double x12; double x13;
    double y11; double y12; double y13;
    double a; double b; double c; // Відрізки

    double s; // Проща
    double h; // Висота
    double p; // Полу-периметр
    double Cent; // центр
    public Triangle(Point a, Point b, Point c) {
        this.a1 = a; this.b1 = b; this.c1 = c;

        if(a == null || b == null || c == null) throw new IllegalArgumentException();
        if(a.equals(b) || a.equals(c) || b.equals(c))  throw new IllegalArgumentException();



        this.a = sideLenght(a, b);
        this.b = sideLenght(b, c);
        this.c = sideLenght(a, c);

        x11 = a.getX(); x12 = b.getX(); x13 = c.getX();
        y11 = a.getY(); y12 = b.getY(); y13 = c.getY();

        if(area() <=0) throw new IllegalArgumentException();

       // System.out.println("Side lenght = " + this.a + " / "+ this.b + " / "+ this.c);
        if(this.a+this.b < this.c || this.a+this.c < this.b || this.c+this.b < this.a) throw new IllegalArgumentException();
        test180Angls();
    }

    public double area()
    {
        p = (a+b+c)/2;
        s = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        h = ( Math.sqrt(p*(p-a)*(p-b)*(p-c))   )/a;

        return s;
    }

    double sideLenght(Point a, Point b)
    {
        double temp = Math.pow(b.getX() - a.getX(), 2);
        temp += Math.pow(b.getY() - a.getY(), 2);
        double result = Math.sqrt(temp);

        if(result >0 ) return result;
        else throw new IllegalArgumentException();

    }

    void test180Angls(){
        double angleAtA = Math.toDegrees(acos((pow(b, 2) + pow(c, 2) - pow(a, 2))/(2*b*c)));
        double angleAtB = Math.toDegrees(acos((pow(a, 2) + pow(c, 2) - pow(b, 2))/(2*a*c)));
        double angleAtC = Math.toDegrees(acos((pow(a, 2) + pow(b, 2) - pow(c, 2))/(2*a*b)));

        if(angleAtA == 0 || angleAtB == 0 || angleAtC == 0) throw new IllegalArgumentException();

        //System.out.println("Side angle = " + angleAtA + " / "+ angleAtB + " / "+ angleAtC);
        if(Math.abs(angleAtA+angleAtB+angleAtC) > 180.0000005) {
           // System.out.println("Wrong angle = " + (angleAtA + angleAtB + angleAtC) + " / ");
            throw new IllegalArgumentException();
        }
        else if (angleAtA >= 180 || angleAtB >= 180 || angleAtC >= 180) {
            throw new IllegalArgumentException();
        }

    }

    public Point centroid(){
        double resX = (x11 +x12+x13)/3;
        double resY = (y11+y12+y13)/3;
        return new Point(resX, resY);
    }

    public boolean isTheSame(Figure figure){
        if(figure instanceof Triangle
                && ((Triangle) figure).a1.equals(this.a1)
                && ((Triangle) figure).b1.equals(this.b1)
                && ((Triangle) figure).c1.equals(this.c1)
        ) return true;
        else return false;
    }

    @Override
    public String pointsToString()
    {
        return a1.toString() + b1.toString() + c1.toString();
    }
}
