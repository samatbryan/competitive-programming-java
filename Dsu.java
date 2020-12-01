public class Dsu<T> {
    private HashMap<T, T> parent;
    private HashMap<T, Integer> size;

    /**
     * Class constructor for the Disjoint Set Union class, uses generics
     * example usage:
     * using integers: Dsu<Integer> dsu = new Dsu<Integer>()
     * using pairs: Dsu<Pair<Integer,Integer>> dsu = new Dsu<Pair<Integer,Integer>>();
     */
    public Dsu() {
        this.parent = new HashMap<T, T>();
        this.size = new HashMap<T, Integer>();
    }

    /**
     * Time Complexity: O(1)
     * Takes a new vertex v and creates a new set as a parent of itself in O
     * @param v The vertex to add to the DSU
     */
    void make_set(T v) {
        parent.put(v, v);
        size.put(v, 1);
    }

    /**
     * Time Complexity: O(log(n))
     * Takes a new vertex v and returns the root. Uses Path Compression
     * Path Compression is where you find the root, and set all node's parent along that path to
     * root
     * @param v The vertex to find the root of
     * @return The root of the vertex v
     */
    T find_set(T v) {
        if (parent.get(v).equals(v))
            return v;
        T root = find_set(parent.get(v));
        parent.put(v, root);
        return root;
    }

    /**
     * Time Complexity: O(log(n))
     * Takes a new vertex v and returns the root. Uses Path Compression and Size Heuristics
     * Path Compression is where you find the root, and set all node's parent along that path to
     * root
     * @param v The vertex to find the root of
     * @return The root of the vertex v
     */
    void union_set(T a, T b) {
        a = find_set(a);
        b = find_set(b);

        if (!a.equals(b)) {
            if (size.get(a) < size.get(b)) {
                parent.put(a, b);
                size.put(b, size.get(b) + size.get(a));
            } else {
                parent.put(b, a);
                size.put(a, size.get(b) + size.get(a));
            }
        }
    }
}