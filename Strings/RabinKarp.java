public class RabinKarp {
    String s;
    long[] prefix_hash;
    long[] prefix_hash_2;
    long[] powers;
    long[] powers_2;

    final int PRIME1 = 1009;
    final int PRIME2 = 3137;

    final int MOD1 = 998244353;
    final int MOD2 = 1000000007;

    /**
     * Time Complexity: O(N) Class constructor specifiying the string s we want to
     * work with.
     */
    RabinKarp(String s) {
        this.s = s;
        this.prefix_hash = new long[this.s.length() + 1];
        this.prefix_hash_2 = new long[this.s.length() + 1];
        this.powers = new long[this.s.length() + 1];
        this.powers_2 = new long[this.s.length() + 1];
        this.computeHashAndPow();
    }

    /**
     * Time Complexity: O(N) Computes the prefix hash values and computes the prefix
     * powers.
     */
    private void computeHashAndPow() {
        this.powers[0] = 1;
        for (int i = 1; i <= this.s.length(); i++) {
            long c = (long) this.s.charAt(i - 1) - 'a' + 1;
            this.prefix_hash[i] = ((prefix_hash[i - 1] * PRIME1 + c) % MOD1);
            this.powers[i] = ((this.powers[i - 1] * PRIME1) % MOD1);
        }
        this.powers_2[0] = 1;
        for (int i = 1; i <= this.s.length(); i++) {
            long c = (long) this.s.charAt(i - 1) - 'a' + 1;
            this.prefix_hash_2[i] = ((prefix_hash_2[i - 1] * PRIME2 + c) % MOD2);
            this.powers_2[i] = ((this.powers_2[i - 1] * PRIME2) % MOD2);
        }
    }

    /**
     * Time Complexity: O(1) Takes a left and right inclusive indices that resembles
     * a substring and calculates the hash in O(1) time
     * 
     * @param l The left of the substring
     * @param r The right of the substring
     * @return The hash of the substring [l,r].
     */
    public long getHashSubstring(int l, int r) {
        return (this.prefix_hash[r + 1] - this.prefix_hash[l] * this.powers[r - l + 1] % MOD1 + MOD1) % MOD1;
    }

    /**
     * Time Complexity: O(1) Takes a left and right inclusive indices that resembles
     * a substring and calculates the hash in O(1) time
     * 
     * @param l The left of the substring
     * @param r The right of the substring
     * @return The hash of the substring [l,r].
     */
    public Pair<Long, Long> getHashSubstring(int l, int r, boolean want_pair) {
        long hash1 = (this.prefix_hash[r + 1] - this.prefix_hash[l] * this.powers[r - l + 1] % MOD1 + MOD1) % MOD1;
        long hash2 = (this.prefix_hash_2[r + 1] - this.prefix_hash_2[l] * this.powers_2[r - l + 1] % MOD2 + MOD2)
                % MOD2;
        return new Pair(hash1, hash2);
    }
}