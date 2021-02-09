
public class circles {

    /**
     * 
     * @param circle A pair representing the x,y point of the circle's center
     * @param radius The radius of the circle centered at circle
     * @param line   The line representing the a line
     * @return The number of intersections circle and line make
     */
    int circleLine(Pair<Double, Double> circle, double radius, Line line) {
        
    }

    /**
     * https://stackoverflow.com/questions/36211171/finding-center-of-a-circle-given-two-points-and-radius
     * 
     * @param point1 First x,y point on the cartesion plane
     * @param point2 Second x,y point on the cartesion plane
     * @param r      The radius of the circle
     * @return A list of (x,y) points that each represent the center of a circle
     *         determined by the given two points and a radius
     */
    static double[][] get_circle_centers(double[] point1, double[] point2, double r) {
        double x1 = point1[0], y1 = point1[1], x2 = point2[0], y2 = point2[1], err = 1e-6;
        double xc = (x1 + x2) / 2, yc = (y1 + y2) / 2, q = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if (Math.abs(q - 2 * r) <= err) { // one exact circle can be determined
            return new double[][] { { xc, yc } };
        } else if (q > 2 * r) // no circles can be determined
            return null;
        double dx = (y1 - y2) / q * Math.sqrt(r * r - q * q / 4), dy = (x2 - x1) / q * Math.sqrt(r * r - q * q / 4);
        return new double[][] { { xc + dx, yc + dy }, { xc - dx, yc - dy } };
    }

}