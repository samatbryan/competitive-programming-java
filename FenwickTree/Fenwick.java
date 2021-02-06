import java.util.*;

public class Fenwick {

    int[] bit;
    int n;

    Fenwick(int n) {
        this.bit = new int[n];
        this.n = n;
    }

    public int sum(int left, int right) {
        return sum(right) - sum(left - 1);
    }

    /*
     * Adds the sum of the range [g(r),r] (i.e. T[r]) to the result then, it "jumps"
     * to the range [g(g(r)−1),g(r)−1], and adds this range's sum to the result and
     * so on, until it "jumps" from [0,g(g(…g(r)−1⋯−1)−1)] to [g(−1),−1]; that is
     * where the sum function stops jumping.
     * 
     */
    private int sum(int right) {
        int res = 0;
        for (int i = right; i >= 0; i = (i & (i + 1)) - 1) {
            res += bit[i];
        }
        return res;
    }

    /*
     * We just need to find a way to iterate over all j's, such that g(j)≤i≤j. We
     * can find all such j's by starting with idx and flipping the last unset bit.
     * We will call this operation h(j). For example, for i=10 we have:
     * 
     * 10 = 0001010 h(10)=11=0001011 h(11)=15=0001111 h(15)=31=0011111
     * h(31)=63=0111111 Unsurprisingly, there also exists a simple way to perform h
     * using bitwise operations: h(j)=j | (j+1),
     * 
     */
    public void add(int idx, int val) {
        for (int j = idx; j < this.n; j = j | (j + 1)) {
            this.bit[j] += val;
        }
    }

}