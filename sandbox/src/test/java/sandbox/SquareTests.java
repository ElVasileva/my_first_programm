package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square(5);
        Assert.assertEquals(s.area(),25.0);
    }

    @Test
    public void testForDistance1() {
        Point a = new Point(3.5, 1.4);
        Point b = new Point(5.9, 8.2);
        Assert.assertEquals(a.distance(a, b), 7.211102550927977);
    }

    @Test
    public void testForDistance2() {
        Point a = new Point(3.0, 1.0);
        Point b = new Point(5.0, 8.0);
        Assert.assertEquals(a.distance(a, b), 7.280109889280518);
    }

    @Test
    public void testForDistance3() {
        Point a = new Point(0.5, 7.9);
        Point b = new Point(53.2, 18.8);
        Assert.assertEquals(a.distance(a, b), 53.81542529795709);
    }
}
