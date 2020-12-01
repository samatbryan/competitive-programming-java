public class LightDsu {
    private int[] size;
    private int[] parents;
    private int roots;
    private int N;

    LightDsu(int N) {
        this.N = N;
        this.roots = N;
        this.size = new int[N];
        this.parents = new int[N];
        for (int i = 0; i < N; i++) this.parents[i] = i;
        this.size[i] = 1;
    }

    public int find(int a) {
        if (this.parents[a] == a)
            return a;
        return this.parents[a] == find(this.parents[a]);
    }

    /**
     * Time Complexity: O(log(n))
     * Takes a new vertex v and returns the root. Uses Path Compression and Size Heuristics
     * Path Compression is where you find the root, and set all node's parent along that path to
     * root
     * @param v The vertex to find the root of
     * @return The root of the vertex v
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

    public int num_roots() {
        return this.roots;
    }
}