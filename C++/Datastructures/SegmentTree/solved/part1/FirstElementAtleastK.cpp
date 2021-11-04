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
    vector<long long> nums;

    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        nums.assign(size * 2, INT_MIN);
    }

    void set(int i, int v) { set(i, v, 0, 0, size); }

    void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            nums[x] = v;
            return;
        }
        int mx = (lx + rx) / 2;
        if (i < mx) {
            set(i, v, 2 * x + 1, lx, mx);
        } else {
            set(i, v, 2 * x + 2, mx, rx);
        }
        nums[x] = max(nums[2 * x + 1], nums[2 * x + 2]);
    }

    int first_index_atleast_x(int v) {
        return first_index_atleast_x(v, 0, 0, size);
    }

    int first_index_atleast_x(int v, int x, int lx, int rx) {
        if (nums[x] < v) return -1;
        if (rx - lx == 1) return lx;
        int mx = (lx + rx) / 2;
        if (nums[2 * x + 1] >= v)
            return first_index_atleast_x(v, 2 * x + 1, lx, mx);
        else {
            return first_index_atleast_x(v, 2 * x + 2, mx, rx);
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
        st.set(i, a);
    }
    for (int i = 0; i < m; i++) {
        cin >> q;
        if (q == 1) {
            cin >> idx >> a;
            st.set(idx, a);
        } else {
            cin >> a;
            cout << st.first_index_atleast_x(a) << endl;
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