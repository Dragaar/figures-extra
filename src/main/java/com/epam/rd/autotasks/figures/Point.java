package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+ x +
                "," + y +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        //double res1 = Math.ceil(point.x)-Math.ceil(x);
        //double res2 = Math.ceil(point.y)-Math.ceil(y);

        //System.out.println("res1 = " + Double.compare(point.x, x) + " res2 = " + Double.compare(point.x, x));

        return deltaCompare(point.x, x, 0.001) && deltaCompare(point.y, y, 0.001);
       // return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    private static boolean deltaCompare(double v1, double v2, double delta) {
        //https://www.baeldung.com/java-comparing-doubles
        return Math.abs(v1 - v2) < delta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
