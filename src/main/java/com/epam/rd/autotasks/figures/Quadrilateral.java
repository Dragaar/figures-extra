package com.epam.rd.autotasks.figures;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
class Quadrilateral extends Figure{

    private Point a1; private Point b1; private Point c1; private Point d1;

    protected double a; double b; double c; double d;// Відрізки
    double diag1; double diag2;

    double s; // Проща
    double h; // Висота
    double p; // Полу-периметр
    double Cent; // центр

    public Quadrilateral(Point a, Point b, Point c, Point d) {

        if(a == null || b == null || c == null) throw new IllegalArgumentException();
        if(a.equals(b) || a.equals(c) || b.equals(c))  throw new IllegalArgumentException();

        this.a1 = a;
        this.b1 = b;
        this.c1 = c;
        this.d1 = d;

        this.a = sideLenght(a, b);
        this.b = sideLenght(b, c);
        this.c = sideLenght(c, d);
        this.d = sideLenght(a, d);

        diag1 = sideLenght(a, c);
        diag2 = sideLenght(b, d);

        if(area() <=0) throw new IllegalArgumentException();
        //якщо трикутники проходять свої умови то квадрат не є дегеративним
        Point test = centroid();

    }

    public double area()
    {
        double temp = 0;
        temp += 4*(  pow(diag1, 2)  * pow(diag2, 2) )-(  pow(  (pow(a, 2)+pow(c, 2)-pow(b, 2)-pow(d, 2)), 2) );
        temp = sqrt(temp)/4;
        return temp;
        //неточний варіант
        //return (diag1*diag2)/2;
    }

    double sideLenght(Point a, Point b)
    {
        double temp = pow(b.getX() - a.getX(), 2);
        temp += pow(b.getY() - a.getY(), 2);
        double result = sqrt(temp);

        if(result >0 ) return result;
        else throw new IllegalArgumentException();

    }

    @Override
    public Point centroid() {
        // Знаходження двох трикутників, які утворюютрся при проведенні діагоналі з b1 до d1, та визначення їх центроїдів
        Triangle triangle1 = new Triangle(a1, b1, d1);
        Triangle triangle2 = new Triangle(b1, c1, d1);
        Point centroid1 = triangle1.centroid();
        Point centroid2 = triangle2.centroid();

        // Знаходження двох трикутників, які утворюютрся при проведенні діагоналі з a1 до c1, та визначення їх центроїдів
        Triangle triangle3 = new Triangle(a1, b1, c1);
        Triangle triangle4 = new Triangle(c1, d1, a1);
        Point centroid3 = triangle3.centroid();
        Point centroid4 = triangle4.centroid();

        //Проведення прямих між центроїдами
        Segment segment1 = new Segment(centroid1, centroid2);
        Segment segment2 = new Segment(centroid3, centroid4);
        //точка в якій пересікаются відрізки і є центроїд квадрату
        Point quadrilateralCentroid = segment1.intersection(segment2);

        return quadrilateralCentroid;
    }

    @Override
    public boolean isTheSame(Figure figure) {

        if(figure instanceof Quadrilateral) {
            double diag3 = ((Quadrilateral) figure).getDiag1();
            double diag4 = ((Quadrilateral) figure).getDiag1();
             /* Segment segAB = new Segment(((Quadrilateral) figure).getA1(), ((Quadrilateral) figure).getB1());
            Segment segBC = new Segment(((Quadrilateral) figure).getB1(), ((Quadrilateral) figure).getC1());
            Segment segCD = new Segment(((Quadrilateral) figure).getC1(), ((Quadrilateral) figure).getD1());
            Segment segAD = new Segment(((Quadrilateral) figure).getA1(), ((Quadrilateral) figure).getD1());
            */
            double area = figure.area();
            System.out.println("centroid 1 = " + ((Quadrilateral) figure).centroid() + "centroid 2 = " + this.centroid());
            if ((diag3 == this.diag1 || diag4 == this.diag2)
                    && area == this.area()
                    && ((Quadrilateral) figure).centroid().equals(this.centroid())
            )
            {
                return true;
               /* if (segAB.intersection(segBC) != null)
                segAB.intersection(segBC) != null*/
            }
        }
        return false;
    }

    @Override
    public String pointsToString()
    {
        return a1.toString() + b1.toString() + c1.toString() + d1.toString();
    }

    public double getDiag1() { return diag1; }
    public double getDiag2() {return diag2; }

    public Point getA1() {
        return a1;
    }

    public Point getB1() {
        return b1;
    }

    public Point getC1() {
        return c1;
    }

    public Point getD1() {
        return d1;
    }
}
