/*
    A rectangle with sides equal to even integers a and b is drawn on the Cartesian plane.
    Its center (the intersection point of its diagonals) coincides with the point (0, 0),
    but the sides of the rectangle are not parallel to the axes;
    instead, they are forming 45 degree angles with the axes.

    How many points with integer coordinates are located inside the given rectangle (including on its sides)?
    Example:

    For a = 6 and b = 4, the output should be 23
*/

public class RectangleRotation {
    public static void main(String[] args) {
        System.out.println(rectangleRotation(4552, 8646));
    }


    static int rectangleRotation(final int a, final int b) {
        int result;
        double halfA = a / 2;
        System.out.println("halfA: " + halfA);
        double halfB = b / 2;
        System.out.println("halfB: " + halfB);
        final double diagonalPerOneUnit = Math.sqrt(2.0);
        int insideRectangleSideSizeA = (int) (halfA / diagonalPerOneUnit);
        int insideRectangleSideSizeB = (int) (halfB / diagonalPerOneUnit);
        System.out.println("insideA: " + insideRectangleSideSizeA);
        System.out.println("insideB: " + insideRectangleSideSizeB);
        double aa = halfA - (insideRectangleSideSizeA * diagonalPerOneUnit);
        double bb = halfB - (insideRectangleSideSizeB * diagonalPerOneUnit);
        System.out.println("aa: " + aa);
        System.out.println("bb: " + bb);
        if (((halfA - (insideRectangleSideSizeA * diagonalPerOneUnit)) < (diagonalPerOneUnit / 2))
                && ((halfB - (insideRectangleSideSizeB * diagonalPerOneUnit)) < (diagonalPerOneUnit / 2))) {
            // ezek a legkülsõ sorok
            result = ((2 * insideRectangleSideSizeA + 1) * (2 * insideRectangleSideSizeB + 1)) +
                    ((2 * insideRectangleSideSizeA) * (2 * insideRectangleSideSizeB));
        } else if (((halfA - (insideRectangleSideSizeA * diagonalPerOneUnit)) > (diagonalPerOneUnit / 2))
                && ((halfB - (insideRectangleSideSizeB * diagonalPerOneUnit)) < (diagonalPerOneUnit / 2))) {
            // van még egy sor kiljebb 'A' oldalnál
            result=  ((2 * insideRectangleSideSizeA + 1) * (2 * insideRectangleSideSizeB + 1)) +
                    ((2 * insideRectangleSideSizeA+2) * (2 * insideRectangleSideSizeB));
        } else if (((halfA - (insideRectangleSideSizeA * diagonalPerOneUnit)) < (diagonalPerOneUnit / 2))
                && ((halfB - (insideRectangleSideSizeB * diagonalPerOneUnit)) > (diagonalPerOneUnit / 2))) {
            // van még egy sor kiljebb 'B' oldalnál
            result=  ((2 * insideRectangleSideSizeA + 1) * (2 * insideRectangleSideSizeB + 1)) +
                    ((2 * insideRectangleSideSizeA) * (2 * insideRectangleSideSizeB+2));
        } else {
            // van még egy sor kiljebb 'A' és 'B' oldalnál is
            result=  ((2 * insideRectangleSideSizeA + 1) * (2 * insideRectangleSideSizeB + 1)) +
                    ((2 * insideRectangleSideSizeA+2) * (2 * insideRectangleSideSizeB+2));
        }
        return result;
    }
}