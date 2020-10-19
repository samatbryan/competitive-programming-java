public class RabinKarp{
    String s;
    long[] prefix_hash;
    long[] powers;

    final int PRIME1 = 3137;
    final int PRIME2 = 1009;
    final int MOD1 = 1000000007;
    final int MOD2 = 998244353;

    /**
    * Class constructor specifiying the string s we want to work with.
    */
    RabinKarp(String s){
        this.s = s;
        this.prefix_hash = new long[this.s.length() + 1];
        this.powers = new long[this.s.length() + 1];
        this.computeHashAndPow();
    }

    private void computeHashAndPow(){
        this.powers[0] = 1;

        for(int i=1; i<=this.s.length(); i++){
            long c = (long) this.s.charAt(i-1) - 'a' + 1;
            this.prefix_hash[i] = ((prefix_hash[i-1]*PRIME1 + c) % MOD1);
            this.powers[i] = ((this.powers[i-1]*PRIME1) % MOD1);
        }
    }

    /**
    * Takes a left and right inclusive indices that resembles a substring and calculates the hash in O(1) time 
    * @param l The left of the substring
    * @param r The right of the substring
    * @return The hash of the substring [l,r].
    */
    public long getHashSubstring(int l, int r){
        return (prefix_hash[r+1] - prefix_hash[l] * powers[r - l + 1] % MOD1 + MOD1) % MOD1;
    }
}