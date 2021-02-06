public class Geometry {

    static class Line {
        double A, B, C;
        double x1, y1, x2, y2;

        Line(double x1, double y1, double x2, double y2) {
            this.A = y2 - y1;
            this.B = x1 - x2;
            this.C = A * x1 + B * y1;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static Line generate_line(Pair<Double, Double> p1, Pair<Double, Double> p2) {
        double x1 = p1.first, y1 = p1.second;
        double x2 = p2.first, y2 = p2.second;
        return new Line(x1, y1, x2, y2);
    }

    static boolean intersect(Line line1, Line line2, boolean segment) {
        // return true if line1 and line2 intersect
        double det = line1.A * line2.B - line2.A * line1.B;
        if (det == 0) {
            return false;
        }
        // this is line intersection
        double X = (line2.B * line1.C - line1.B * line2.C) / det;
        double Y = (line1.A * line2.C - line2.A * line1.C) / det;

        if (segment) { // line segment version
            double tolerance = 1e-6;
            if (X < Math.min(line1.x1, line1.x2) - tolerance || X > Math.max(line1.x1, line1.x2) + tolerance)
                return false;
            if (Y < Math.min(line1.y1, line1.y2) - tolerance || Y > Math.max(line1.y1, line1.y2) + tolerance)
                return false;
            if (X < Math.min(line2.x1, line2.x2) - tolerance || X > Math.max(line2.x1, line2.x2) + tolerance)
                return false;
            if (Y < Math.min(line2.y1, line2.y2) - tolerance || Y > Math.max(line2.y1, line2.y2) + tolerance)
                return false;
            return true;
        }
        return true;
    }

    static double polygon_area(ArrayList<Pair<Double, Double>> points) {
        double area = 0;
        int n = points.size();
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            area += points.get(i).second * points.get(j).first - points.get(i).first * points.get(j).second;
            j = i;
        }
        return Math.abs(area) / 2;
    }

    static boolean simple_polygon(ArrayList<Line> lines) {
        // check that every pair of nonadjacent line segments dont intersect
        int n = lines.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(i - j) <= 1)
                    continue;
                if (i == 0 && j == n - 1)
                    continue;
                if (i == n - 1 && j == 0)
                    continue;

                if (intersect(lines.get(i), lines.get(j), true)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Time Complexity: O(1) Returns a list of center of circles determined by two
     * points and a radius
     * 
     * https://stackoverflow.com/questions/36211171/finding-center-of-a-circle-given-two-points-and-radius
     * 
     * @param point1 First x,y point on the cartesion plane
     * @param point2 Second x,y point on the cartesion plane
     * @param r      The radius of the circle
     * @return A list of (x,y) points that each represent the center of a circle
     *         determined by the given two points and a radius
     */
    static double[][] get_circle_centers(double[] point1, double[] point2, double r) {
        double x1 = point1[0], y1 = point1[1];
        double x2 = point2[0], y2 = point2[1];
        double xc = (x1 + x2) / 2, yc = (y1 + y2) / 2;
        double q = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double err = 1e-6;
        if (Math.abs(q - 2 * r) <= err) { // one exact circle can be determined
            return new double[][] { { xc, yc } };
        } else if (q > 2 * r) // no circles can be determined
            return null;
        double dx = (y1 - y2) / q * Math.sqrt(r * r - q * q / 4);
        double dy = (x2 - x1) / q * Math.sqrt(r * r - q * q / 4);
        return new double[][] { { xc + dx, yc + dy }, { xc - dx, yc - dy } };
    }

}