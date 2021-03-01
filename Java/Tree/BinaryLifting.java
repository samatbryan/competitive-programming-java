class TreeAncestor {
    int[][] dp;

    public TreeAncestor(int n, int[] parent) {
        dp = new int[n][31]; // dp[i][j] ith node to 2^j parent
        for (int i = 0; i < parent.length; i++) {
            dp[i][0] = parent[i];
        }

        for (int j = 1; j < 31; j++) {
            for (int i = 0; i < n; i++) {
                int par = dp[i][j - 1];
                if (par == -1) {
                    dp[i][j] = -1;
                    continue;
                }
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int cur = node;
        for (int i = 0; i < 31; i++) {
            if (cur == -1)
                return cur;
            if ((k & (1 << i)) != 0) {
                cur = dp[cur][i];
            }
        }
        return cur;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent); int param_1 =
 * obj.getKthAncestor(node,k);
 */