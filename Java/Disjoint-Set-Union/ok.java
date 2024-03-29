import java.util.*;

class Solution {
    public class LightDsu {
        private int[] size;
        private int[] parents;
        private int roots;
        private int N;

        /**
         * Class constructor for the Light Disjoint Set Union class, uses integers
         * exclusively Instantiates N exclusive sets indexed from 0 to N-1 example
         * usage: LightDsu dsu = new LightDsu();
         */
        LightDsu(int N) {
            this.N = N;
            this.roots = N;
            this.size = new int[N];
            this.parents = new int[N];
            for (int i = 0; i < N; i++)
                this.parents[i] = i;
            Arrays.fill(size, 1);
        }

        /**
         * Time Complexity: O(log(n)) Takes a new vertex a and returns the root. Uses
         * Path Compression Path Compression is where you find the root, and set all
         * node's parent along that path to root
         * 
         * @param a The vertex to find the root of
         * @return The root of the vertex a
         */
        public int find_set(int a) {
            if (this.parents[a] == a)
                return a;
            return this.parents[a] = find_set(this.parents[a]);
        }

        /**
         * Time Complexity: O(log(n)) Takes a two vertex a and b and joins the two sets.
         * Uses Union by Size - put the smaller tree onto the bigger tree
         * 
         * @param a The first set to join
         * @param b The second set to join
         */
        public void union_set(int a, int b) {
            a = find_set(a);
            b = find_set(b);

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
         * Time Complexity: O(1) Returns the number of roots
         * 
         * @return The number of distinct roots
         */
        public int num_roots() {
            return this.roots;
        }

        /**
         * Time Complexity: O(LogN) Returns the size of set that contains a
         * 
         * @return True if a and b are in the same set
         */
        boolean connected(int a, int b) {
            return find_set(a) == find_set(b);
        }
    }

    public boolean equal(HashSet<Integer> a, HashSet<Integer> b) {
        if (a.size() != b.size())
            return false;
        for (int num : a) {
            if (!b.contains(num))
                return false;
        }
        return true;
    }

    public boolean gcdSort(int[] nums) {
        int[] snums = (int[]) nums.clone();

        Arrays.sort(snums);
        int n = nums.length;
        HashMap<Integer, HashSet<Integer>> hm = new HashMap();

        HashMap<Integer, HashSet<Integer>> needIndices = new HashMap();
        HashMap<Integer, HashSet<Integer>> haveIndices = new HashMap();

        for (int i = 0; i < n; i++) {
            needIndices.putIfAbsent(snums[i], new HashSet());
            needIndices.get(snums[i]).add(i);

            haveIndices.putIfAbsent(nums[i], new HashSet());
            haveIndices.get(nums[i]).add(i);
        }
        // form graph
        for (int i = 0; i < n; i++) {

        }
        int MAX_VAL = snums[n - 1];

        // DSU stuff
        LightDsu dsu = new LightDsu(MAX_VAL + 1);
        for (int num : nums) {
            int ognum = num;
            while (num <= MAX_VAL) {
                if (needIndices.containsKey(num)) {
                    dsu.union_set(ognum, num);
                }
                num += ognum;
            }
        }
        for (int num : nums) {
            dsu.find_set(num);
        }
        HashMap<Integer, ArrayList<Integer>> groupStuff = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int parent = dsu.parents[nums[i]];
            groupStuff.putIfAbsent(parent, new ArrayList());
            groupStuff.get(parent).add(nums[i]);
        }
        // end DSU stuff
        for (int k : groupStuff.keySet()) {
            ArrayList<Integer> groupVals = groupStuff.get(k);
            HashSet<Integer> have = new HashSet();
            HashSet<Integer> target = new HashSet();

            for (int val : groupVals) {
                for (int index : haveIndices.get(val)) {
                    have.add(index);
                }
                for (int index : needIndices.get(val)) {
                    target.add(index);
                }
            }

            if (!equal(have, target)) {
                for (int h : have) {
                    System.out.print("h: " + h);
                }
                System.out.println();
                for (int t : target) {
                    System.out.print("t: " + t);
                }
                return false;
            }
        }
        return true;
    }
}