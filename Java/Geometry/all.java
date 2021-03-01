static class Point implements Comparable<Point> {

    static final double EPS = 1e-9;

    double x, y;

    Point(double a, double b) {
        x = a;
        y = b;
    }

    @Override
    public int compareTo(Point p) {
        if (Math.abs(x - p.x) > EPS)
            return x > p.x ? 1 : -1;
        if (Math.abs(y - p.y) > EPS)
            return y > p.y ? 1 : -1;
        return 0;
    }

    double dist(Point p) {
        return Math.sqrt(sq(x - p.x) + sq(y - p.y));
    }

    double square() {
        return sq(x) + sq(y);
    }

    double sq(double x) {
        return x * x;
    }

    Point perp() {
        return new Point(-1 * y, x);
    }

    Point rotate(double angle) {
        double c = Math.cos(angle), s = Math.sin(angle);
        return new Point(x * c - y * s, x * s + y * c);
    }
    // for integer points and rotation by 90 (counterclockwise) : swap x and y,
    // negate x

    Point rotate(double theta, Point p) // rotate around p
    {
        Vector v = new Vector(p, new Point(0, 0));
        return translate(v).rotate(theta).translate(v.reverse());
    }

    Point translate(Vector v) {
        return new Point(x + v.x, y + v.y);
    }

    Point reflectionPoint(Line l) // reflection point of p on line l
    {
        Point p = l.closestPoint(this);
        Vector v = new Vector(this, p);
        return this.translate(v).translate(v);
    }

    boolean between(Point p, Point q) {
        return x < Math.max(p.x, q.x) + EPS && x + EPS > Math.min(p.x, q.x) && y < Math.max(p.y, q.y) + EPS
                && y + EPS > Math.min(p.y, q.y);
    }

    // returns true if it is on the line defined by a and b
    boolean onLine(Point a, Point b) {
        if (a.compareTo(b) == 0)
            return compareTo(a) == 0;
        return Math.abs(new Vector(a, b).cross(new Vector(a, this))) < EPS;
    }

    boolean onSegment(Point a, Point b) {
        if (a.compareTo(b) == 0)
            return compareTo(a) == 0;
        return onRay(a, b) && onRay(b, a);
    }

    // returns true if it is on the ray whose start point is a and passes through b
    boolean onRay(Point a, Point b) {
        if (a.compareTo(b) == 0)
            return compareTo(a) == 0;
        return new Vector(a, b).normalize().equals(new Vector(a, this).normalize()); // implement equals()
    }

    // returns true if it is on the left side of Line pq
    // add EPS to LHS if on-line points are accepted
    static boolean ccw(Point p, Point q, Point r) {
        return new Vector(p, q).cross(new Vector(p, r)) > 0;
    }

    static boolean collinear(Point p, Point q, Point r) {
        return Math.abs(new Vector(p, q).cross(new Vector(p, r))) < EPS;
    }

    static double angle(Point a, Point o, Point b) // angle AOB
    {
        Vector oa = new Vector(o, a), ob = new Vector(o, b);
        return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
    }

    static double distToLine(Point p, Point a, Point b) // distance between point p and a line defined by points a,
                                                        // b (a
                                                        // != b)
    {
        if (a.compareTo(b) == 0)
            return p.dist(a);
        // formula: c = a + u * ab
        Vector ap = new Vector(a, p), ab = new Vector(a, b);
        double u = ap.dot(ab) / ab.norm2();
        Point c = a.translate(ab.scale(u));
        return p.dist(c);
    }
    // Another way: find closest point and calculate the distance between it and p

    static double distToLineSegment(Point p, Point a, Point b) {
        Vector ap = new Vector(a, p), ab = new Vector(a, b);
        double u = ap.dot(ab) / ab.norm2();
        if (u < 0.0)
            return p.dist(a);
        if (u > 1.0)
            return p.dist(b);
        return distToLine(p, a, b);
    }
    // Another way: find closest point and calculate the distance between it and p

    static Point sub(Point p, Point q) {
        return new Point(p.x - q.x, p.y - q.y);
    }

    static Point sub(Point p, double c) {
        return new Point(p.x - c, p.y - c);
    }

    static Point add(Point p, Point q) {
        return new Point(p.x + q.x, p.y + q.y);
    }

    static Point add(Point p, double c) {
        return new Point(p.x + c, p.y + c);
    }

    static Point mul(Point p, Point q) {
        return new Point(p.x * q.x, p.y * q.y);
    }

    static Point mul(Point p, double c) {
        return new Point(p.x * c, p.y * c);
    }

    static Point div(Point p, Point q) {
        return new Point(p.x / q.x, p.y / q.y);
    }

    static Point div(Point p, double c) {
        return new Point(p.x / c, p.y / c);
    }

}

static class Line {

    static final double INF = 1e9, EPS = 1e-9;

    double a, b, c;

    Line(Point p, Point q) {
        if (Math.abs(p.x - q.x) < EPS) {
            a = 1;
            b = 0;
            c = -p.x;
        } else {
            a = (p.y - q.y) / (q.x - p.x);
            b = 1.0;
            c = -(a * p.x + p.y);
        }

    }

    Line(Point p, double m) {
        a = -m;
        b = 1;
        c = -(a * p.x + p.y);
    }

    boolean parallel(Line l) {
        return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS;
    }

    boolean same(Line l) {
        return parallel(l) && Math.abs(c - l.c) < EPS;
    }

    Point intersect(Line l) {
        if (parallel(l))
            return null;
        double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
        double y;
        if (Math.abs(b) < EPS)
            y = -l.a * x - l.c;
        else
            y = -a * x - c;

        return new Point(x, y);
    }

    Point closestPoint(Point p) {
        if (Math.abs(b) < EPS)
            return new Point(-c, p.y);
        if (Math.abs(a) < EPS)
            return new Point(p.x, -c);
        return intersect(new Line(p, 1 / a));
    }

}

public class Circle { // equation: (x-c.x)^2 + (y-c.y)^2 = r^2

    static final double EPS = 1e-9;

    Point c;
    double r;

    Circle(Point p, double k, int count) {
        c = p;
        r = k;
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

    boolean circleLine(Line l) { // return true if this circle intersects with Line l
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

    Point findCenter(Point p, Point q, double r) // for the other center, swap p and q
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

static class Vector {

    double x, y;

    Vector(double a, double b) {
        x = a;
        y = b;
    }

    Vector(Point a, Point b) {
        this(b.x - a.x, b.y - a.y);
    }

    Vector scale(double s) {
        return new Vector(x * s, y * s);
    } // s is a non-negative value

    double dot(Vector v) {
        return (x * v.x + y * v.y);
    }

    double cross(Vector v) {
        return x * v.y - y * v.x;
    }

    double norm2() {
        return x * x + y * y;
    }

    Vector reverse() {
        return new Vector(-x, -y);
    }

    Vector normalize() {
        double d = Math.sqrt(norm2());
        return scale(1 / d);
    }
}

public class Rectangle {

    static final double EPS = 1e-9;

    Point ll, ur;

    Rectangle(Point a, Point b) {
        ll = a;
        ur = b;
    }

    double area() {
        return (ur.x - ll.x) * (ur.y - ll.y);
    }

    boolean contains(Point p) {
        return p.x <= ur.x + EPS && p.x + EPS >= ll.x && p.y <= ur.y + EPS && p.y + EPS >= ll.y;
    }

    Rectangle intersect(Rectangle r) {
        Point ll = new Point(Math.max(this.ll.x, r.ll.x), Math.max(this.ll.y, r.ll.y));
        Point ur = new Point(Math.min(this.ur.x, r.ur.x), Math.min(this.ur.y, r.ur.y));
        if (Math.abs(ur.x - ll.x) > EPS && Math.abs(ur.y - ll.y) > EPS && this.contains(ll) && this.contains(ur)
                && r.contains(ll) && r.contains(ur))
            return new Rectangle(ll, ur);
        return null;
    }

}