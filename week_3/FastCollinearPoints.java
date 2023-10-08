import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
   private LineSegment[] lineSegments;

   private class Slope {
      int index;
      double slope;

      public Slope(int index, double slope) {
         this.index = index;
         this.slope = slope;
      }
   }

   private Slope[] deepCopy(Slope[] slopes) {
      Slope[] newSlopes = new Slope[slopes.length];

      for (int i = 0; i < slopes.length; i++) {
         newSlopes[i] = slopes[i];
      }
      return newSlopes;
   }

   private Slope[] mergeSort(Slope[] slopes) {
      // I need to rewrite merge sort.
      // try doing it as array or arrays
      // and fill it up will null up to 2^n?
      // and that should be much easier I think.
      if (slopes.length == 0) {
         return new Slope[0];
      }
      Slope[] temp = deepCopy(slopes);
      Slope[][] matrix = new Slope[temp.length][1];
      for (int i = 0; i < temp.length; i++) {
         matrix[i][0] = temp[i];
      }

      while (matrix.length > 1) {

         Slope[][] newMatrix = new Slope[(matrix.length + 1) / 2][matrix[0].length * 2];
         for (int i = 0; i < matrix.length; i += 2) {
            Slope[] arrayA = matrix[i];
            // it is not sure this one exists
            if (i + 1 < matrix.length) {
               Slope[] arrayB = matrix[i + 1];
               // alternate a and b
               int indexA = 0;
               int indexB = 0;
               if (arrayB[indexB] == null) {
                  indexB = arrayB.length;
               }
               if (arrayA[indexA] == null) {
                  indexA = arrayA.length;
               }
               int newArrayIndex = 0;
               Slope[] newArray = new Slope[arrayA.length + arrayB.length];
               while (indexA < arrayA.length && indexB < arrayB.length) {
                  if (arrayA[indexA].slope < arrayB[indexB].slope) {
                     newArray[newArrayIndex] = arrayA[indexA];
                     indexA++;
                     if (indexA < arrayA.length && arrayA[indexA] == null) {
                        indexA = arrayA.length;
                     }
                  } else {
                     newArray[newArrayIndex] = arrayB[indexB];
                     indexB++;
                     if (indexB < arrayB.length && arrayB[indexB] == null) {
                        indexB = arrayB.length;
                     }
                  }
                  newArrayIndex++;
               }
               // fill up with the other arary
               while (indexA < arrayA.length) {
                  newArray[newArrayIndex] = arrayA[indexA];
                  indexA++;
                  newArrayIndex++;
               }
               while (indexB < arrayB.length) {
                  newArray[newArrayIndex] = arrayB[indexB];
                  indexB++;
                  newArrayIndex++;
               }
               newMatrix[i / 2] = newArray;

            } else {
               newMatrix[i / 2] = deepCopy(arrayA);
            }

         }
         matrix = newMatrix;
      }
      return matrix[0];

   }

   public FastCollinearPoints(Point[] points) {
      if (points == null) {
         throw new java.lang.IllegalArgumentException();
      }
      for (Point point : points) {
         if (point == null) {
            throw new java.lang.IllegalArgumentException();
         }
      }

      int length = points.length;
      LineSegment[] tempLineSegments = new LineSegment[length];
      double[] slopesTaken = new double[length];
      Point[] lowestsTaken = new Point[length];
      int slopesTakenCounter = 0;
      int lowestTakenCounter = 0;
      int lineSegmentCounter = 0;
      for (int i = 0; i < length; i++) {
         Point point = points[i];
         Slope[] slopes = new Slope[length - 1];
         int slopeCounter = 0;
         for (int j = 0; j < length; j++) {
            if (i == j) {
               continue;
            }
            slopes[slopeCounter] = new Slope(j, point.slopeTo(points[j]));
            slopeCounter++;
         }
         slopes = mergeSort(slopes);
         if (slopes.length == 0) {
            continue;
         }
         int count = 1;
         Slope current = slopes[0];
         Point[] linePoints = new Point[length];
         linePoints[0] = point;
         for (int j = 0; j < slopes.length; j++) {
            if (current.slope == slopes[j].slope) {
               linePoints[count] = points[slopes[j].index];
               count++;
            } else {

               if (count > 3) {
                  // do somthing
                  Point lowest = linePoints[0];
                  Point highest = linePoints[0];
                  for (int c = 0; c < count; c++) {
                     Point p = linePoints[c];
                     if (lowest.compareTo(p) < 0) {
                        lowest = p;
                     }
                     if (highest.compareTo(p) > 0) {
                        highest = p;
                     }
                  }
                  boolean slopTaken = false;
                  for (int slopIndex = 0; slopIndex < slopesTakenCounter; slopIndex++) {
                     if (slopesTaken[slopIndex] == current.slope) {
                        slopTaken = true;
                        break;
                     }
                  }
                  boolean lowestTaken = false;
                  for (int lowestIndex = 0; lowestIndex < lowestTakenCounter; lowestIndex++) {
                     if (lowestsTaken[lowestIndex].compareTo(lowest) == 0) {
                        lowestTaken = true;
                        break;
                     }
                  }
                  // this one can not only be slope
                  // needs to be lowest + slope or something
                  if (!(slopTaken && lowestTaken)) {
                     tempLineSegments[lineSegmentCounter] = new LineSegment(lowest, highest);
                     lineSegmentCounter++;
                     slopesTaken[slopesTakenCounter] = current.slope;
                     slopesTakenCounter++;
                     lowestsTaken[lowestTakenCounter] = lowest;
                     lowestTakenCounter++;
                  }

               }
               current = slopes[j];
               linePoints[0] = point;
               linePoints[1] = points[current.index];
               count = 2;

            }
         }
         if (count > 3) {
            // do somthing
            Point lowest = linePoints[0];
            Point highest = linePoints[0];
            for (int c = 0; c < count; c++) {
               Point p = linePoints[c];
               if (lowest.compareTo(p) < 0) {
                  lowest = p;
               }
               if (highest.compareTo(p) > 0) {
                  highest = p;
               }
            }
            boolean slopTaken = false;
            for (int slopIndex = 0; slopIndex < slopesTakenCounter; slopIndex++) {
               if (slopesTaken[slopIndex] == current.slope) {
                  slopTaken = true;
                  break;
               }
            }
            boolean lowestTaken = false;
            for (int lowestIndex = 0; lowestIndex < lowestTakenCounter; lowestIndex++) {
               if (lowestsTaken[lowestIndex].compareTo(lowest) == 0) {
                  lowestTaken = true;
                  break;
               }
            }
            // this one can not only be slope
            // needs to be lowest + slope or something
            if (!(slopTaken && lowestTaken)) {
               tempLineSegments[lineSegmentCounter] = new LineSegment(lowest, highest);
               lineSegmentCounter++;
               slopesTaken[slopesTakenCounter] = current.slope;
               slopesTakenCounter++;
               lowestsTaken[lowestTakenCounter] = lowest;
               lowestTakenCounter++;
            }
         }

      }

      lineSegments = new LineSegment[lineSegmentCounter];
      for (int i = 0; i < lineSegmentCounter; i++) {
         lineSegments[i] = tempLineSegments[i];
      }

   } // finds all line segments containing 4 or more points

   public int numberOfSegments() {
      return lineSegments.length;
   } // the number of line segments

   public LineSegment[] segments() {
      LineSegment[] returnLineSegments = new LineSegment[lineSegments.length];
      for (int i = 0; i < lineSegments.length; i++) {
         returnLineSegments[i] = lineSegments[i];
      }
      return returnLineSegments;
   } // the line segments

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
      FastCollinearPoints collinear = new FastCollinearPoints(points);
      for (LineSegment segment : collinear.segments()) {
         StdOut.println(segment);
         segment.draw();
      }
      StdDraw.show();
   }
}