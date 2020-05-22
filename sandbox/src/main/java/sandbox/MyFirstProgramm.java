package sandbox;

import static java.lang.Math.sqrt;

public class MyFirstProgramm {

    public static void main(String[] args) {

        Point a = new Point(3.5, 1.4);
        Point b = new Point(5.9, 8.2);


        System.out.println("Расстояние между точками с координатами " + a.x + ";" + a.y +
                " и " + b.x + ";" + b.y + " равно " + distance(a, b));


        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }


    public static double square(double a) {
        return a * a;
    }

    public static double distance(Point a, Point b) {
        return sqrt(square(a.x - b.x) + square(a.y - b.y));
    }


}

