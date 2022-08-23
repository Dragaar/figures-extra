package com.epam.rd.autotasks.figures;


import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.max;
import static java.lang.Math.min;

class Segment {
    public double x1, x2, y1, y2;

    public Segment(Point start, Point end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException();
        }
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end.getY();
    }

    double length() {
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

    Point middle() {
        double rx = (x1 + x2) / 2;
        double ry = (y1 + y2) / 2;
        return new Point(rx, ry);
    }

    Point intersection(Segment another) {

        double x11 = this.x1, y11 = this.y1;
        double x12 = this.x2, y12 = this.y2;

        double x21 = another.x1, y21 = another.y1;
        double x22 = another.x2, y22 = another.y2;

        double dx1, dy1, dx2, dy2;
        dx1 = x12 - x11;
        dy1 = y12 - y11;
        dx2 = x22 - x21;
        dy2 = y22 - y21;
        double s, t;

        s = (-dy1 * (x11 - x21) + dx1 * (y11 - y21)) / (-dx2 * dy1 + dx1 * dy2);
        t = (dx2 * (y11 - y21) - dy2 * (x11 - x21)) / (-dx2 * dy1 + dx1 * dy2);


        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) { // Collision detected
            double intX = x11 + (t * dx1);
            double intY = y11 + (t * dy1);
            return new Point(intX, intY);

        }
        return null; // No collision
    }
}
