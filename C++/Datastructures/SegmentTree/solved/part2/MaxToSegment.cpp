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
    vector<long long> operations;
    int size;

    // must be commutative and associative operation, unless lazy prop is used!
    long long operation(long long a, long long b) { return max(a, b); }

    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        operations.assign(2 * size, 0LL);
    }

    // modify all elements on the segment, using some operation defined by
    // operation
    void modify(int l, int r, int v) { return modify(l, r, v, 0, 0, size); }

    void modify(int l, int r, int v, int x, int lx, int rx) {
        if (l >= rx || lx >= r) return;
        if (lx >= l && rx <= r) {
            operations[x] = operation(operations[x], (long long)v);
            return;
        }
        int mx = (lx + rx) / 2;
        modify(l, r, v, 2 * x + 1, lx, mx);
        modify(l, r, v, 2 * x + 2, mx, rx);
    }

    long long get(int i) { return get(i, 0, 0, size); }
    long long get(int i, int x, int lx, int rx) {
        if (rx - lx == 1) return operations[x];
        int mx = (lx + rx) / 2;
        if (i < mx) {
            return operation(operations[x], get(i, 2 * x + 1, lx, mx));
        }
        return operation(operations[x], get(i, 2 * x + 2, mx, rx));
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
            st.modify(l, r, v);
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