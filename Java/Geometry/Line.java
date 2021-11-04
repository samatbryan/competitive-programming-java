
public class Line {

    static final double INF = 1e9, EPS = 1e-9;

    double a, b, c;
    Point p, q;

    Line(Point p, Point q) {
        this.p = p;
        this.q = q;
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

    Point intersect(Line ls, boolean segment) {
        Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);

        if (l1.parallel(l2)) {
            return null;
        }
        Point c = l1.intersect(l2);
        if (c.between(p, q) && c.between(ls.p, ls.q)) {
            return c;
        }
        return null;
    }

    Point closestPoint(Point p) {
        if (Math.abs(b) < EPS)
            return new Point(-c, p.y);
        if (Math.abs(a) < EPS)
            return new Point(p.x, -c);
        return intersect(new Line(p, 1 / a));
    }

}
