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

    /**
     * @param p1 The first point
     * @param p2 The second point
     * @return A Line representing the line determined by the two points
     */
    static Line generate_line(Pair<Double, Double> p1, Pair<Double, Double> p2) {
        double x1 = p1.first, y1 = p1.second;
        double x2 = p2.first, y2 = p2.second;
        return new Line(x1, y1, x2, y2);
    }

/**
 * @param line1   The first line
 * @param line2   The second line
 * @param segment A boolean representing whether we want to determine the
 *                intersection of segments or lines
 * @return A double representing if the two lines intersect
 */
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
