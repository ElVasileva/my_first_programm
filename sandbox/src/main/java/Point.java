import static java.lang.Math.sqrt;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point a, Point b) {
        return sqrt((a.x - b.x) + (a.y - b.y));
    }


}
