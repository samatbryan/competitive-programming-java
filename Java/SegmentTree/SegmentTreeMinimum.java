public class SegmentTree {
    int n;
    int size;
    long[] vals;

    /**
     * Implemented similarly to binary heap tree O(log(size)) operation for Gives
     * O(log(size)) operations for both set() and sum()
     * 
     * @param n The total number of elements in the array
     */
    public SegmentTree(int n) {
        this.n = n;
        this.size = 1;
        while (this.size < n) {
            this.size *= 2; // get the smallest power of 2 less than n
        }
        this.vals = new long[size * 2]; // binary heap takes size 2*size
        Arrays.fill(vals, Integer.MAX_VALUE);
    }

    /**
     * O(log(size)) operation for setting the value at idx to val. Also changes the
     * sums array to change the sums of all the nodes that cover the index idx
     * 
     * @param idx index in the user view array to change value
     * @param val The value to change to
     */
    public void set(int idx, int val) {
        set(idx, val, 0, 0, size);
    }

    /**
     * Builds the segment tree in O(N) time
     * 
     * @param a The array of numbers to build the segment tree from
     */
    public void build(int[] a) {
        build(a, 0, 0, size);
    }

    /**
     * O(log(size)) operation for getting the sum of interval [left, right).
     * 
     * @param left  inclusive idx
     * @param right exclusive idx
     */
    public long query(int left, int right) {
        return query(left, right, 0, 0, size);
    }

    /**
     * Find the first index between [Left, Right] inclusive with value <= val
     * 
     * @param left  The leftmost index of the array
     * @param right The rightmost index of the array inclusive
     * @param val   The value we want to find <=
     * @return The index of the array with value <= val
     */
    public int first_index_less_than(int left, int right, long val) {
        if (vals[0] > val)
            return -1;
        int x = size + left - 1;
        while (vals[x] > val) {
            // if were a right child, then go up and right
            while (x % 2 == 0) {
                x = x / 2 - 1;
            }
            x++;
        }
        while (x < size - 1) { // while its an internal node
            x = 2 * x + 1; // left child
            if (vals[x] > val) // go to right
                x++;
        }
        int res = x - (size - 1);
        if (res <= right)
            return res;
        return -1;
    }

    /**
     * Time Complexity: O(log(size)) Sets the value at idx to val and changes all
     * the corresponding relevant nodes in the segment tree property of the queue
     * 
     * @param idx   The index of the user view array
     * @param val   The value to set the index to
     * @param x     The index representation of the binary heap
     * @param l_idx The left index of user array covered by node x inclusive
     * @param r_idx The right index of user array covered by node x exclusive
     */
    private void set(int idx, int val, int x, int l_idx, int r_idx) {
        if (r_idx - l_idx == 1) { // the leaf node
            vals[x] = val;
            return;
        }
        int m_idx = (l_idx + r_idx) / 2;
        if (idx < m_idx) {
            set(idx, val, 2 * x + 1, l_idx, m_idx);
        } else {
            set(idx, val, 2 * x + 2, m_idx, r_idx);
        }
        vals[x] = Math.min(vals[2 * x + 1], vals[2 * x + 2]);
    }

    /**
     * Return the sum of all elements in the interval [left,right)
     * 
     * @param left  Inclusive index of the left to get the sum of
     * @param right Exclusive index of the right to get the sum of
     * @param x     The index representation of the binary heap
     * @param l_idx The left index of user array covered by node x inclusive
     * @param r_idx The right index of user array covered by node x exclusive
     */
    private long query(int left, int right, int x, int l_idx, int r_idx) {
        if (l_idx >= right || left >= r_idx)
            return Integer.MAX_VALUE;
        if (l_idx >= left && r_idx <= right)
            return vals[x];

        int m_idx = (l_idx + r_idx) / 2;
        long left_sum = query(left, right, 2 * x + 1, l_idx, m_idx);
        long right_sum = query(left, right, 2 * x + 2, m_idx, r_idx);
        return Math.min(left_sum, right_sum);
    }

    private void build(int[] a, int x, int l_idx, int r_idx) {
        if (r_idx - l_idx == 1) {
            if (l_idx < a.length)
                vals[x] = a[l_idx];
            return;
        }
        int m_idx = (l_idx + r_idx) / 2;
        build(a, 2 * x + 1, l_idx, m_idx);
        build(a, 2 * x + 2, m_idx, r_idx);
        vals[x] = Math.min(vals[2 * x + 1], vals[2 * x + 2]);
    }

}