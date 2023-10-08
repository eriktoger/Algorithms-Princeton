import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        if (points.length <= 25) {
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    if (points[i].compareTo(points[j]) == 0) {
                        throw new java.lang.IllegalArgumentException();
                    }

                }
            }
        }

        int length = points.length;
        LineSegment[] tempLineSegments = new LineSegment[length];
        int counter = 0;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1; k < length - 1; k++) {
                    for (int l = k + 1; l < length; l++) {

                        double slopeJ = points[i].slopeTo(points[j]);
                        double slopeK = points[i].slopeTo(points[k]);
                        double slopeL = points[i].slopeTo(points[l]);
                        if (slopeJ == Double.NEGATIVE_INFINITY || slopeK == Double.NEGATIVE_INFINITY
                                || slopeL == Double.NEGATIVE_INFINITY) {
                            throw new java.lang.IllegalArgumentException();
                        }
                        if (slopeJ == slopeK && slopeJ == slopeL) {
                            Point[] fourpoints = new Point[4];
                            fourpoints[0] = points[i];
                            fourpoints[1] = points[j];
                            fourpoints[2] = points[k];
                            fourpoints[3] = points[l];
                            Point lowest = points[i];
                            Point highest = points[i];
                            for (Point point : fourpoints) {

                                if (lowest.compareTo(point) < 0) {
                                    lowest = point;
                                }
                                if (highest.compareTo(point) > 0) {
                                    highest = point;
                                }
                            }

                            tempLineSegments[counter] = new LineSegment(highest, lowest);
                            counter++;
                        }

                    }
                }
            }
        }

        lineSegments = new LineSegment[counter];
        for (

                int i = 0; i < counter; i++) {
            lineSegments[i] = tempLineSegments[i];
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] returnLineSegments = new LineSegment[lineSegments.length];
        for (int i = 0; i < lineSegments.length; i++) {
            returnLineSegments[i] = lineSegments[i];
        }
        return returnLineSegments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}