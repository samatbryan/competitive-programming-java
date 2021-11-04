// ACCEPTED
// https://codeforces.com/edu/course/2/lesson/4/1/practice/contest/273169/problem/A
// #include <bits/stdc++.h>
// string s;
// getline(cin, s);
// g++ -std=c++11 -O2 -Wall test.cpp -o test
// printf("%.9f\n", x); PRECISION

#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;

#define F first
#define S second

const long long MOD = 1e9 + 7;
const double EPS = 1e-9;

struct segtree {
    vector<long long> nums;
    int size;

    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        nums.assign(2 * size, 0LL);
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
        nums[x] = nums[2 * x + 1] + nums[2 * x + 2];
    }
    long long range_sum(int l, int r) { return range_sum(l, r, 0, 0, size); }

    long long range_sum(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) return 0;
        if (lx >= l && rx <= r) return nums[x];
        int mx = (lx + rx) / 2;
        return range_sum(l, r, 2 * x + 1, lx, mx) +
               range_sum(l, r, 2 * x + 2, mx, rx);
    }

    void add_range(int l, int r, int v) {
        return add_range(l, r, v, 0, 0, size);
    }

    void add_range(int l, int r, int v, int x, int lx, int rx) {
        if (l >= rx || lx >= r) return;
        if (lx >= l && rx <= r) {
            nums[x] += v;
            return;
        }
        int mx = (lx + rx) / 2;
        add_range(l, r, v, 2 * x + 1, lx, mx);
        add_range(l, r, v, 2 * x + 2, mx, rx);
    }

    long long get(int i) { return get(i, 0, 0, size); }
    long long get(int i, int x, int lx, int rx) {
        if (rx - lx == 1) return nums[x];
        int mx = (lx + rx) / 2;
        if (i < mx) {
            return nums[x] + get(i, 2 * x + 1, lx, mx);
        }
        return nums[x] + get(i, 2 * x + 2, mx, rx);
    }
};

void solve() {
    int n, m, a, v, l, r, op, idx;
    cin >> n >> m;
    segtree st;
    st.init(n);
    for (int i = 0; i < m; i++) {
        cin >> op;
        if (op == 1) {
            cin >> l >> r >> v;
            st.add_range(l, r, v);
        } else {
            cin >> idx;
            cout << st.get(idx) << '\n';
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