public class Pair<S extends Comparable<S>, T extends Comparable<T>> implements Comparable<Pair<S, T>> {
    S first;
    T second;
    
    Pair(S f, T s) {
        first = f;
        second = s;
    }

    @Override
    public int compareTo(Pair<S, T> o) {
        int t = first.compareTo(o.first);
        if (t == 0) return second.compareTo(o.second);
        return t;
    }
    
    @Override
    public int hashCode() {
        return (31 + first.hashCode()) * 31 + second.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        if (o == this) return true;
        Pair p = (Pair) o;
        return first.equals(p.first) && second.equals(p.second);
    }
    
    @Override
    public String toString() {
        return "Pair{" + first + ", " + second + "}";
    }
}