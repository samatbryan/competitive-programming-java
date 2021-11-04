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

// lazy seg tree. associative functions work for this tree.
// each node is an operation, and the order matters
// so we need to propagate downwards.
//
// each node represents that were applying this op to all the values that this
// node represents
//
// x represents the current node
// lx, rx represents the range of A node x covers
// l, r represents the range of A
struct segtree {
    vector<long long> operations;
    int size;
    long long NO_OP = LLONG_MAX;

    // apply b to a. currently the assignment operation, but cna change this to
    // anything
    long long operation(long long a, long long b) {
        if (b == NO_OP) return a;
        return b;
    }

    void apply_operation(long long &a, long long b) { a = operation(a, b); }

    // propagate down the current op x to the children, and set the current
    // op x to NO_OP
    void propagate(int x, int lx, int rx) {
        if (rx - lx == 1) return;
        apply_operation(operations[2 * x + 1], operations[x]);
        apply_operation(operations[2 * x + 2], operations[x]);
        operations[x] = NO_OP;
    }

    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        operations.assign(2 * size, 0LL);
    }

    // modify all elements on the segment, using some operation defined by
    // operation
    void modify(int l, int r, int v) { return modify(l, r, v, 0, 0, size); }

    void modify(int l, int r, int v, int x, int lx, int rx) {
        // first need to propagate down this operation at this node down
        // since we might need to go below this operation and we need to
        // maintain order of operations
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) return;
        if (lx >= l && rx <= r) {
            apply_operation(operations[x], v);
            return;
        }
        int mx = (lx + rx) / 2;
        modify(l, r, v, 2 * x + 1, lx, mx);
        modify(l, r, v, 2 * x + 2, mx, rx);
    }

    long long get(int i) { return get(i, 0, 0, size); }
    long long get(int i, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (rx - lx == 1) return operations[x];
        int mx = (lx + rx) / 2;
        if (i < mx) {
            return get(i, 2 * x + 1, lx, mx);
        }
        return get(i, 2 * x + 2, mx, rx);
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