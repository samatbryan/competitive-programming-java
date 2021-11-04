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
    vector<int> nums;
    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        nums.assign(size * 2, 0);
    }
    void set(int i, int v) { set(i, v, 0, 0, size); }
    void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            nums[x] = 1;
            return;
        }
        int mx = (lx + rx) / 2;
        if (i < mx) {
            set(i, v, 2 * x + 1, lx, mx);
        } else {
            set(i, v, 2 * x + 2, mx, rx);
        }
        nums[x] = nums[2 * x + 1] + nums[2 * x + 2];
    }

    int sum(int l, int r) { return sum(l, r, 0, 0, size); }
    int sum(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) return 0;
        if (lx >= l && rx <= r) return nums[x];
        int mx = (lx + rx) / 2;
        return sum(l, r, 2 * x + 1, lx, mx) + sum(l, r, 2 * x + 2, mx, rx);
    }
};

void solve() {
    int n, m, a, q, idx;
    cin >> n;
    segtree st;
    st.init(n);
    for (int i = 0; i < n; i++) {
        cin >> a;
        st.set((a - 1), 1);
        cout << st.sum((a), n) << " ";
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
