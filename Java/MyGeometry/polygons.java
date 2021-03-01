public class polygons {

    /**
     * @param points An arraylist of points in order that determine a simple polygon
     * @return A double representing the area of the simple polygon
     */
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

    /**
     * @param lines An arraylist of line representations in order of connection
     * @return A boolean stating whether the lines form a simple polygon
     */
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
}