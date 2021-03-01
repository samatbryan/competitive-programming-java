public class SegmentTree {
    int n;
    int size;
    long[] vals;
    int NO_OPERATION = Integer.MAX_VALUE - 1;
    long OPERATION_BASE_CASE = 0; // FILL THIS FOR INITIALIZATION

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
        // FILL THIS FOR INITIALIZATION
    }

    /**
     * 
     * @param a first param
     * @param b second param
     * @return Returns applying some operation to a and b
     */
    public long apply_operation(long a, long b) {
        // FILL THIS
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
    public long calc(int left, int right) {
        return calc(left, right, 0, 0, size);
    }

    /**
     * O(logn) - Apply some operation with val to the segment defined by [l,r-1]:
     * The operator we apply (add in this case) must be ASSOCIATIVE and COMMUTATIVE
     * 
     * @param l   The leftmost of the segment
     * @param r   The rightmost of the segment
     * @param val The value to add to
     */
    public void modify(int l, int r, int val) {
        modify(l, r, val, 0, 0, this.size);
    }

    /**
     * O(logn) - Gets the val at inx in the user view array
     * 
     * @param idx The index to get the value in the array from
     * @return The long in the index
     */
    public long get(int idx) {
        return get(idx, 0, 0, size);
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
    void set(int idx, int val, int x, int l_idx, int r_idx) {
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
        vals[x] = apply_operation(vals[2 * x + 1], vals[2 * x + 2]);
    }

    /**
     * Calculates applying operation on the interval [left, right-1]
     * 
     * @param left  Inclusive index of the left to get the sum of
     * @param right Exclusive index of the right to get the sum of
     * @param x     The index representation of the binary heap
     * @param l_idx The left index of user array covered by node x inclusive
     * @param r_idx The right index of user array covered by node x exclusive
     */
    long calc(int left, int right, int x, int l_idx, int r_idx) {
        if (l_idx >= right || left >= r_idx)
            return OPERATION_BASE_CASE;
        if (l_idx >= left && r_idx <= right)
            return vals[x];

        int m_idx = (l_idx + r_idx) / 2;
        long left_calc = calc(left, right, 2 * x + 1, l_idx, m_idx);
        long right_calc = calc(left, right, 2 * x + 2, m_idx, r_idx);
        return apply_operation(left_calc, right_calc);
    }

    /**
     * 
     * @param a     The int array to build the segtree from
     * @param x     The current node in the heap
     * @param l_idx The leftmost index covered by node x
     * @param r_idx The rightmost index covered by node x exclusive
     */
    void build(int[] a, int x, int l_idx, int r_idx) {
        if (r_idx - l_idx == 1) {
            if (l_idx < a.length)
                vals[x] = a[l_idx];
            return;
        }
        int m_idx = (l_idx + r_idx) / 2;
        build(a, 2 * x + 1, l_idx, m_idx);
        build(a, 2 * x + 2, m_idx, r_idx);
        vals[x] = apply_operation(vals[2 * x + 1], vals[2 * x + 2]);
    }

    /**
     * 
     * @param l   The left index of the user array
     * @param r   The right index of the user array exclusive
     * @param val The value to add to segment [l, r-1]
     * @param x   The node in the heap
     * @param lx  The leftmost index that node x covers
     * @param rx  The rightmost index that node x covers exclusive
     */
    void modify(int l, int r, int val, int x, int lx, int rx) {
        if (lx >= r || l >= rx)
            return;
        if (lx >= l && rx <= r) {
            vals[x] = apply_operation(vals[x], val);
            return;
        }
        int mx = (lx + rx) / 2;
        modify(l, r, val, 2 * x + 1, lx, mx);
        modify(l, r, val, 2 * x + 2, mx, rx);
    }

    /**
     * 
     * @param idx The index of the user array to fetch from
     * @param x   The node in the heap
     * @param lx  The leftmost user mode idx that node x covers
     * @param rx  The rightmost user mode idx that node x covers. Exclusive
     * @return The value at idx
     */
    long get(int idx, int x, int lx, int rx) {
        if (rx - lx == 1) {
            return vals[x];
        }
        int mx = (lx + rx) / 2;
        long res = vals[x];
        if (idx < mx) {
            res = apply_operation(res, get(idx, 2 * x + 1, lx, mx));
        } else {
            res = apply_operation(res, get(idx, 2 * x + 2, mx, rx));
        }
        return res;
    }

}