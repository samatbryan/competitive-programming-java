import java.util.*;

public class Lis {
    /**
     * Time Complexity: O(NLog(N)) Takes an array and returns the length of the LIS
     * using binary search.
     * 
     * @param nums The array to find length of LIS
     * @return an int representing the length of the LIS
     */
    public int lis(int[] nums) {
        int n = nums.length;
        int res = 0;
        int[] lis = new int[n + 1];
        Arrays.fill(lis, Integer.MAX_VALUE);
        lis[0] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int cur = nums[i];

            int left = 0;
            int right = n;
            int insert_idx = 1;
            // find the count of numbers strictly less than cur
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (cur > lis[mid]) {
                    insert_idx = Math.max(insert_idx, mid + 1);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            lis[insert_idx] = cur;
            res = Math.max(res, insert_idx);
        }
        return res;
    }

    /**
     * Time Complexity: O(NLog(N)) Takes an array and returns the length of the LIS
     * using binary search.
     * 
     * @param nums An ArrayList<Integer> to find length of LIS
     * @return an int representing the length of the LIS
     */
    public int lis(ArrayList<Integer> nums) {
        int n = nums.size();
        int res = 0;
        int[] lis = new int[n + 1];
        Arrays.fill(lis, Integer.MAX_VALUE);
        lis[0] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int cur = nums.get(i);

            int left = 0;
            int right = n;
            int insert_idx = 1;
            // find the count of numbers strictly less than cur
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (cur > lis[mid]) {
                    insert_idx = Math.max(insert_idx, mid + 1);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            lis[insert_idx] = cur;
            res = Math.max(res, insert_idx);
        }
        return res;
    }
}