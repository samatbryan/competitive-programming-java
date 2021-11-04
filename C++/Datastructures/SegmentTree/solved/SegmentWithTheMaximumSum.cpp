// #include <bits/stdc++.h>
// string s;
// getline(cin, s);
// g++ -std=c++11 -O2 -Wall test.cpp -o test
// printf("%.9f\n", x); PRECISION
/*
Segment With the Maximum Sum
1. set(i, v)
2. max_segment(left, right) - return the maximum sum subarray within
[left,right]

Main idea
 */

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

struct item {
    long long seg, pre, suf, sum;
};
item NEUTRAL_ELEMENT() { return {INT_MIN, INT_MIN, INT_MIN, INT_MIN}; }

item single(int v) { return {max(v, 0), max(v, 0), max(v, 0), v}; }

item merge(item a, item b) {
    long long pre = max(a.sum + b.pre, a.pre);
    long long suf = max(b.sum + a.suf, b.suf);
    long long sum = a.sum + b.sum;
    long long seg = max(max(a.seg, b.seg), a.suf + b.pre);
    return {seg, pre, suf, sum};
}

struct segtree {
    int size;
    vector<item> values;
    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        values.resize(2 * size);
    }
    void set(int i, int v) { set(i, v, 0, 0, size); }

    void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            values[x] = single(v);
            return;
        }
        int mx = (lx + rx) / 2;
        if (i < mx)
            set(i, v, 2 * x + 1, lx, mx);
        else {
            set(i, v, 2 * x + 2, mx, rx);
        }
        values[x] = merge(values[2 * x + 1], values[2 * x + 2]);
    };

    long long max_segment(int l, int r) {
        return max_segment(l, r, 0, 0, size).seg;
    }
    item max_segment(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) return NEUTRAL_ELEMENT();
        if (lx >= l && rx <= r) return values[x];
        int mx = (lx + rx) / 2;
        item left = max_segment(l, r, 2 * x + 1, lx, mx);
        item right = max_segment(l, r, 2 * x + 2, mx, rx);
        return merge(left, right);
    }
};
void solve() {
    int n, m, a, idx, v;
    cin >> n >> m;
    segtree st;
    st.init(n);
    for (int i = 0; i < n; i++) {
        cin >> a;
        st.set(i, a);
    }
    cout << st.max_segment(0, n) << '\n';
    for (int i = 0; i < m; i++) {
        cin >> idx >> v;
        st.set(idx, v);
        cout << st.max_segment(0, n) << '\n';
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
