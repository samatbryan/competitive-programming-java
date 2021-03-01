public class Circle { // equation: (x-c.x)^2 + (y-c.y)^2 = r^2

    static final double EPS = 1e-9;

    Point c;
    double r;

    Circle(Point p, double k) {
        c = p;
        r = k;
    }

    boolean consumes(Circle other) {
        Point other_c = other.c;
        double dist = Math.pow(other_c.x - c.x, 2) + Math.pow(other_c.y - c.y, 2);
        dist = Math.sqrt(dist);
        if (r + EPS >= dist + other.r) {
            return true;
        }
        if (r >= dist + other.r + EPS) {
            return true;
        }
        return false;

    }

    int inside(Point p) // 1 for inside, 0 for border, -1 for outside
    {
        double d = p.dist(c);

        return d + EPS < r ? 1 : Math.abs(d - r) < EPS ? 0 : -1;
    }

    double circum() {
        return 2 * Math.PI * r;
    }

    double area() {
        return Math.PI * r * r;
    }

    double arcLength(double deg) {
        return deg / 360.0 * circum();
    } // major and minor chords exist

    double chordLength(double deg) {
        return 2 * r * Math.sin(degToRad(deg) / 2.0);
    }

    double sectorArea(double deg) {
        return deg / 360.0 * area();
    }

    double segmentArea(double deg) {
        return sectorArea(deg) - r * r * Math.sin(degToRad(deg)) / 2.0;
    }

    boolean intersect(Circle cir) {
        return c.dist(cir.c) <= r + cir.r + EPS && c.dist(cir.c) + EPS >= Math.abs(r - cir.r);
    }

    boolean circleLine(Line l) { // return true if this circle intersects wiht Line L
        double x = c.x, y = c.y;
        double radius = r;
        double dist = (Math.abs(l.a * x + l.b * y + l.c)) / Math.sqrt(l.a * l.a + l.b * l.b);
        return radius + EPS >= dist;
    }

    ArrayList<Line> tangents(Circle other, boolean inner) {
        Point o1 = c, o2 = other.c;
        double r1 = r, r2 = other.r;
        if (inner)
            r2 = -r2;
        ArrayList<Line> res = new ArrayList();
        Point d = Point.sub(o2, o1);
        double dr = r1 - r2, d2 = d.square(), h2 = d2 - dr * dr;
        if (d2 == 0 || h2 < 0) {
            return null;
        }
        for (int sign : new int[] { -1, 1 }) {
            Point left = Point.mul(d, dr);
            Point right = Point.mul(Point.mul(d.perp(), Math.sqrt(h2)), -1);
            Point v = Point.div(Point.add(left, right), d2);

            Point a = Point.add(o1, Point.mul(v, r1));
            Point b = Point.add(o2, Point.mul(v, r2));
            res.add(new Line(a, b));
        }
        return res;
    }

    // returns true if the circle intersects with the line segment defined by p and
    // q at one or two points
    boolean intersect(Point p, Point q) {
        Line l = new Line(p, q);
        if (Math.abs(l.b) < EPS) {
            if (l.c * l.c > r * r + EPS)
                return false;

            double y1 = Math.sqrt(Math.abs(r * r - l.c * l.c)), y2 = -y1;
            return new Point(-l.c, y1).between(p, q) && new Point(-l.c, y2).between(p, q);
        }
        double a = l.a * l.a + 1, b = 2 * l.a * l.c, c = l.c * l.c - r * r;
        if (b * b - 4 * a * c + EPS < 0)
            return false;

        double dis = b * b - 4 * a * c;

        double x1 = (-b + Math.sqrt(dis)) / (2.0 * a), x2 = (-b - Math.sqrt(dis)) / (2.0 * a);

        return new Point(x1, -l.a * x1 - l.c).between(p, q) || new Point(x2, -l.a * x2 - l.c).between(p, q);
    }

    static Point findCenter(Point p, Point q, double r) // for the other center, swap p and q
    {
        double d2 = (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
        double det = r * r / d2 - 0.25;
        if (Math.abs(det) < EPS)
            det = 0.0;
        if (det < 0.0)
            return null;
        double h = Math.sqrt(det);
        return new Point((p.x + q.x) / 2.0 + (p.y - q.y) * h, (p.y + q.y) / 2.0 + (q.x - p.x) * h);
    }

    double degToRad(double d) {
        return d * Math.PI / 180.0;
    }

    double radToDeg(double r) {
        return r * 180.0 / Math.PI;
    }

    double round(double x) {
        return Math.round(x * 1000) / 1000.0;
    } // use it because of -0.000

}
