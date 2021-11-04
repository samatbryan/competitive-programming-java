// #include <bits/stdc++.h>
// string s;
// getline(cin, s);
// g++ -std=c++11 -O2 -Wall test.cpp -o test
// printf("%.9f\n", x); PRECISION

#include <algorithm>
#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;
using ll = long long;

#define F first
#define S second

const long long MOD = 1e9 + 7;
const double EPS = 1e-9;

struct segtree {
    int size;
    vector<ll> values;

    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        values.assign(size * 2, 0);
    }

    void set(int i) { set(i, 0, 0, size); }

    void set(int i, int x, int lx, int rx) {
        if (rx - lx == 1) {
            values[x] ^= 1;
            return;
        }
        int mx = (lx + rx) / 2;
        if (i < mx) {
            set(i, 2 * x + 1, lx, mx);
        } else {
            set(i, 2 * x + 2, mx, rx);
        }
        values[x] = values[2 * x + 1] + values[2 * x + 2];
    }

    int kth_one(int k) { return kth_one(k, 0, 0, size); }

    int kth_one(int k, int x, int lx, int rx) {
        if (rx - lx == 1) return lx;
        int mx = (lx + rx) / 2;
        if (k < values[2 * x + 1]) {
            return kth_one(k, 2 * x + 1, lx, mx);
        } else {
            return kth_one(k - values[2 * x + 1], 2 * x + 2, mx, rx);
        }
    }
};
void solve() {
    int n, m, a, q, idx;
    cin >> n >> m;
    segtree st;
    st.init(n);
    for (int i = 0; i < n; i++) {
        cin >> a;
        if (a == 1) st.set(i);
    }
    for (int i = 0; i < m; i++) {
        cin >> q;
        if (q == 1) {
            cin >> idx;
            st.set(idx);
        } else {
            cin >> idx;
            cout << st.kth_one(idx) << endl;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t = 1;
    // cin >> t;
    for (int i = 1; i <= t; i++) {
        // cout <<"Case #" << i << ": ";
        solve();
    }
}
