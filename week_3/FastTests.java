import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FastTests {
    @Test
    public void testFast8() {
        // Your test code for addition
        Point[] points = new Point[8];
        points[0] = new Point(10000, 0);
        points[1] = new Point(0, 10000);
        points[2] = new Point(3000, 7000);
        points[3] = new Point(7000, 3000);
        points[4] = new Point(20000, 21000);
        points[5] = new Point(3000, 4000);
        points[6] = new Point(14000, 15000);
        points[7] = new Point(6000, 7000);

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(fastCollinearPoints.segments().length, 2);

    }

    @Test
    public void testFast6() {
        // Your test code for addition
        Point[] points = new Point[6];
        points[0] = new Point(19000, 10000);
        points[1] = new Point(18000, 10000);
        points[2] = new Point(32000, 10000);
        points[3] = new Point(21000, 10000);
        points[4] = new Point(1234, 5678);
        points[5] = new Point(14000, 10000);

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(fastCollinearPoints.segments().length, 2);

    }

    @Test
    public void testFastEqui() {
        // Your test code for addition
        Point[] points = new Point[15];
        points[0] = new Point(10000, 0);
        points[1] = new Point(8000, 2000);
        points[2] = new Point(2000, 8000);
        points[3] = new Point(0, 10000);

        points[4] = new Point(20000, 0);
        points[5] = new Point(18000, 2000);
        points[6] = new Point(2000, 18000);

        points[7] = new Point(10000, 20000);
        points[8] = new Point(30000, 0);
        points[9] = new Point(0, 30000);
        points[10] = new Point(20000, 10000);

        points[11] = new Point(13000, 0);
        points[12] = new Point(11000, 3000);
        points[13] = new Point(5000, 12000);
        points[14] = new Point(9000, 6000);

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(fastCollinearPoints.segments().length, 2);

    }

}
