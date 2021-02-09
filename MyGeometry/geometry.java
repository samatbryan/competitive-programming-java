public class Geometry {

    static double cross(Pair<Double, Double> p1, Pair<Double, Double> p2) {
        return p1.first * p2.second - p1.second * p2.first;
    }

    static int cross(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return p1.first * p2.second - p1.second * p2.first;
    }
    


}