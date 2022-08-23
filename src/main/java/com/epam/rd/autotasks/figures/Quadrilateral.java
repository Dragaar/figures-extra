package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;

class Quadrilateral extends Figure{

    private Point a1; private Point b1; private Point c1; private Point d1;

    protected double a; double b; double c; double d;// Відрізки
    double diagonalAC; double diagonalBD;

    double s; // Проща
    double h; // Висота
    double p; // Полу-периметр
    double Cent; // центр

    public Quadrilateral(Point a, Point b, Point c, Point d) {

        if(a == null || b == null || c == null || d == null) throw new IllegalArgumentException();
        if(a.equals(b) || a.equals(c) || a.equals(d)|| b.equals(c) || b.equals(d) || c.equals(d))  throw new IllegalArgumentException();

        this.a1 = a;
        this.b1 = b;
        this.c1 = c;
        this.d1 = d;

        this.a = sideLenght(a, b);
        this.b = sideLenght(b, c);
        this.c = sideLenght(c, d);
        this.d = sideLenght(a, d);

        if(this.a <= 0 || this.b <= 0 || this.c <= 0 || this.d <= 0) throw new IllegalArgumentException();

        diagonalAC = sideLenght(a, c);
        diagonalBD = sideLenght(b, d);

        if(area() <=0) throw new IllegalArgumentException();
        //якщо трикутники проходять свої умови то квадрат не є дегеративним
        Point test = centroid();

        test360Angls();

    }

    public double area()
    {
        double temp = 0;
        temp += 4*(  pow(diagonalAC, 2)  * pow(diagonalBD, 2) )-(  pow(  (pow(a, 2)+pow(c, 2)-pow(b, 2)-pow(d, 2)), 2) );
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
            double diag3 = ((Quadrilateral) figure).getDiagonalAC();
            double diag4 = ((Quadrilateral) figure).getDiagonalAC();
             /* Segment segAB = new Segment(((Quadrilateral) figure).getA1(), ((Quadrilateral) figure).getB1());
            Segment segBC = new Segment(((Quadrilateral) figure).getB1(), ((Quadrilateral) figure).getC1());
            Segment segCD = new Segment(((Quadrilateral) figure).getC1(), ((Quadrilateral) figure).getD1());
            Segment segAD = new Segment(((Quadrilateral) figure).getA1(), ((Quadrilateral) figure).getD1());
            */
            double area = figure.area();
            //System.out.println("centroid 1 = " + ((Quadrilateral) figure).centroid() + "centroid 2 = " + this.centroid());
            if ((diag3 == this.diagonalAC || diag4 == this.diagonalBD)
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

    void test360Angls(){

        double angleA = Math.toDegrees(acos((a * a + d * d - diagonalBD * diagonalBD) / (2 * a * d)));
        double angleB = Math.toDegrees(acos((b * b + a * a - diagonalAC * diagonalAC) / (2 * b * a)));
        double angleC = Math.toDegrees(acos((c * c + b * b - diagonalBD * diagonalBD) / (2 * c * b)));
        double angleD = Math.toDegrees(acos((d * d + c * c - diagonalAC * diagonalAC) / (2 * d * c)));

        if(angleA > 180 || angleB > 180 || angleC > 180 || angleD >= 180) throw new IllegalArgumentException();

        //System.out.println("Side angle = " + angleA + " / "+ angleB + " / "+ angleC + " / "+ angleD);
       if(Math.abs(angleA+angleB+angleC+angleD) > 360.00005 || Math.abs(angleA+angleB+angleC+angleD) < 359) {
            // System.out.println("Wrong angle = " + (angleAtA + angleAtB + angleAtC) + " / ");
            throw new IllegalArgumentException();
        }


    }

    @Override
    public String pointsToString()
    {
        return a1.toString() + b1.toString() + c1.toString() + d1.toString();
    }

    public double getDiagonalAC() { return diagonalAC; }
    public double getDiagonalBD() {return diagonalBD; }

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
