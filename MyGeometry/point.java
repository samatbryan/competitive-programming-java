public class Point {
    Number x, y;

    Point(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    Point add(Point o) {
        if (o.x instanceof Double) {
            double cx = this.x.doubleValue(), cy = this.y.doubleValue();
            return new Point(o.x.doubleValue() + cx, o.y.doubleValue() + cy);
        } else if (o.x instanceof Integer) {
            int cx = this.x.intValue(), cy = this.y.intValue();
            return new Point(o.x.intValue() + cx, o.y.intValue() + cy);
        } else {
            long cx = this.x.longValue(), cy = this.y.longValue();
            return new Point(o.x.longValue() + cx, o.y.longValue() + cy);
        }
    }

    Point sub(Point o) {
        if (o.x instanceof Double) {
            double cx = this.x.doubleValue(), cy = this.y.doubleValue();
            return new Point(o.x.doubleValue() - cx, o.y.doubleValue() - cy);
        } else if (o.x instanceof Integer) {
            int cx = this.x.intValue(), cy = this.y.intValue();
            return new Point(o.x.intValue() - cx, o.y.intValue() - cy);
        } else {
            long cx = this.x.longValue(), cy = this.y.longValue();
            return new Point(o.x.longValue() - cx, o.y.longValue() - cy);
        }
    }

    Point mul(Point o) {
        if (o.x instanceof Double) {
            double cx = this.x.doubleValue(), cy = this.y.doubleValue();
            return new Point(o.x.doubleValue() * cx, o.y.doubleValue() * cy);
        } else if (o.x instanceof Integer) {
            int cx = this.x.intValue(), cy = this.y.intValue();
            return new Point(o.x.intValue() * cx, o.y.intValue() * cy);
        } else {
            long cx = this.x.longValue(), cy = this.y.longValue();
            return new Point(o.x.longValue() * cx, o.y.longValue() * cy);
        }
    }

    @Override
    public String toString() {
        return "Point{" + x.toString() + ", " + y.toString() + "}";
    }
}