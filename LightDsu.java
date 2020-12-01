public class LightDsu {
    private int[] size;
    private int[] parents;
    private int roots;
    private int N;

    /**
     * Class constructor for the Light Disjoint Set Union class, uses integers exclusively
     * Instantiates N exclusive sets indexed from 0 to N-1
     * example usage:
     * LightDsu dsu = new LightDsu();
     */

    LightDsu(int N) {
        this.N = N;
        this.roots = N;
        this.size = new int[N];
        this.parents = new int[N];
        for (int i = 0; i < N; i++) this.parents[i] = i;
        this.size[i] = 1;
    }

    /**
     * Time Complexity: O(log(n))
     * Takes a new vertex a and returns the root. Uses Path Compression
     * Path Compression is where you find the root, and set all node's parent along that path to
     * root
     * @param a The vertex to find the root of
     * @return The root of the vertex a
     */
    public int find_set(int a) {
        if (this.parents[a] == a)
            return a;
        return this.parents[a] == find_set(this.parents[a]);
    }

    /**
     * Time Complexity: O(log(n))
     * Takes a two vertex a and b and joins the two sets. Uses Union by Size
     * @param a The first set to join
     * @param b The second set to join
     */
    public void union_set(int a, int b) {
        int a = find(a);
        int b = find(b);

        if (a != b) {
            this.roots--;
            if (this.size[a] < this.size[b]) {
                int c = a;
                a = b;
                b = c;
            }
            this.size[a] += this.size[b];
            this.parents[b] = a;
        }
    }

    /**
     * Time Complexity: O(1)
     * Returns the number of roots
     * @return The number of distinct roots
     */
    public int num_roots() {
        return this.roots;
    }
}