#include <string>
#include <vector>
using namespace std;

struct RabinKarp
{
    long PRIME1 = 1009, PRIME2 = 3137, MOD1 = 998244353, MOD2 = 1000000007;
    vector<long> prefix_hash, prefix_hash_2, powers, powers_2;
    string s;
    int n;

    void init(string x)
    {
        s = x;
        n = s.size();
        prefix_hash.assign(n + 1, 0);
        prefix_hash_2.assign(n + 1, 0);
        powers.assign(n + 1, 0);
        powers_2.assign(n + 1, 0);
        computeHashAndPow();
    }

    void computeHashAndPow()
    {
        powers[0] = powers_2[0] = 1;
        for (int i = 1; i <= n; i++)
        {
            long c = (long)(s[i - 1] - 'a') + 1;
            prefix_hash[i] = ((prefix_hash[i - 1] * PRIME1 + c) % MOD1);
            prefix_hash_2[i] = ((prefix_hash_2[i - 1] * PRIME2 + c) % MOD2);
            powers[i] = ((powers[i - 1] * PRIME1) % MOD1);
            powers_2[i] = ((powers_2[i - 1] * PRIME2) % MOD2);
        }
    }

    long getHashSubstring(int l, int r)
    {
        return (prefix_hash[r + 1] - prefix_hash[l] * powers[r - l + 1] % MOD1 + MOD1) % MOD1;
    }

    pair<int, int> getHashSubstring(int l, int r, bool want_pair)
    {
        long hash1 = (prefix_hash[r + 1] - prefix_hash[l] * powers[r - l + 1] % MOD1 + MOD1) % MOD1;
        long hash2 = (prefix_hash_2[r + 1] - prefix_hash_2[l] * powers_2[r - l + 1] % MOD2 + MOD2) % MOD2;
        return {hash1, hash2};
    }
};