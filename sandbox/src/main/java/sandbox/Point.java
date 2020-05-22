package sandbox;

import static java.lang.Math.sqrt;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double square(double a) {
        return a * a;
    }

    public double distance(Point a, Point b) {
        return sqrt(square(a.x - b.x) + square(a.y - b.y));
    }

}
