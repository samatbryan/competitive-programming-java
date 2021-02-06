package Math;

public class permutation {
    static int[] next_permutation(int[] nums) {
        // if last, then no more:
        boolean changed = false;
        int[] res = (int[]) nums.clone();
        int n = res.length;
        for (int i = n - 1; i > 0; i--) {
            if (res[i - 1] < res[i]) {
                // find the smallest index
                int swap_idx = i;
                int min_value = Integer.MAX_VALUE;
                for (int k = i; k < n; k++) {
                    if (res[k] > res[i - 1] && res[k] < min_value) {
                        swap_idx = k;
                        min_value = res[k];
                    }
                }
                res[swap_idx] = res[i - 1];
                res[i - 1] = min_value;
                Arrays.sort(res, i, n);
                changed = true;
                break;
            }
        }
        if (!changed)
            return null;
        return res;
    }
}