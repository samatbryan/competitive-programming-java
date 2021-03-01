public class SegmentTree {
    int n;
    int size;
    long[] sums;

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
        this.sums = new long[size * 2]; // binary heap takes size 2*size
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
    public long sum(int left, int right) {
        return sum(left, right, 0, 0, size);
    }

    /* PRIVATE FUNCTIONS START HERE */
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
            sums[x] = val;
            return;
        }
        int m_idx = (l_idx + r_idx) / 2;
        if (idx < m_idx) {
            set(idx, val, 2 * x + 1, l_idx, m_idx);
        } else {
            set(idx, val, 2 * x + 2, m_idx, r_idx);
        }
        sums[x] = sums[2 * x + 1] + sums[2 * x + 2];
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
    private long sum(int left, int right, int x, int l_idx, int r_idx) {
        if (l_idx >= right || left >= r_idx)
            return 0;
        if (l_idx >= left && r_idx <= right)
            return sums[x];

        int m_idx = (l_idx + r_idx) / 2;
        long left_sum = sum(left, right, 2 * x + 1, l_idx, m_idx);
        long right_sum = sum(left, right, 2 * x + 2, m_idx, r_idx);
        return left_sum + right_sum;
    }

    private void build(int[] a, int x, int l_idx, int r_idx) {
        if (r_idx - l_idx == 1) {
            if (l_idx < a.length)
                sums[x] = a[l_idx];
            return;
        }
        int m_idx = (l_idx + r_idx) / 2;
        build(a, 2 * x + 1, l_idx, m_idx);
        build(a, 2 * x + 2, m_idx, r_idx);
        sums[x] = sums[2 * x + 1] + sums[2 * x + 2];
    }

}