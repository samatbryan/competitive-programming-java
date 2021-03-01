public static class Polynomial {

    double t0, t1, t2, t3;

    public Polynomial(double a, double b, double c, double d) {
        t0 = a;
        t1 = b;
        t2 = c;
        t3 = d;

    }

    double evaluate(double d) {
        double ans = t0;
        ans += (d * t1);
        ans += (d * d * t2);
        ans += (d * d * d * t3);

        return ans;
    }

    public String toString() {
        return String.format("%f + %fx + %fx^2 + %fx^3", t0, t1, t2, t3);
    }

}

public class Polynomial {

    static final double EPS = 1e-9;

    int degree, coeffs[];

    Polynomial(int[] c) { // lowest degree to highest degree
        degree = c.length - 1;
        coeffs = c;
    }

    double evaluate(double x) // O(degree)
    {
        double res = 0.0;
        for (int i = degree; i >= 0; --i)
            res = res * x + coeffs[i];
        return res;
    }

    Polynomial derivative() // O(degree)
    {
        if (degree == 0)
            return new Polynomial(new int[1]);
        int[] c = new int[degree];
        for (int i = 0; i < degree; ++i)
            c[i] = coeffs[i + 1] * (i + 1);
        return new Polynomial(c);
    }

    double findGreatestRoot() {
        Polynomial d = derivative();
        double x1 = 1e6; // initial guess must be greater than greatest root
        while (true) // may make a for loop for polynomials with no roots
        {
            double x2 = x1 - evaluate(x1) / d.evaluate(x1);
            if (Math.abs(x1 - x2) < EPS)
                break;
            x1 = x2;
        }
        return x1;
    }
}