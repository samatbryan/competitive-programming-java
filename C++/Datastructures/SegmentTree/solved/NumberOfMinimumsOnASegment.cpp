// https://codeforces.com/edu/course/2/lesson/4/1/practice/contest/273169/problem/B
// #include <bits/stdc++.h>
// string s;
// getline(cin, s);
// g++ -std=c++11 -O2 -Wall test.cpp -o test
// printf("%.9f\n", x); PRECISION

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
    vector<pair<ll, ll>> nums;
    int size;

    void init(int n) {
        size = 1;
        while (size < n) size *= 2;
        nums.assign(2 * size, {INT_MAX, 0});
    }

    void set(int i, int v) { set(i, v, 0, 0, size); }

    void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            nums[x] = {v, 1};
            return;
        }
        int mx = (lx + rx) / 2;
        if (i < mx) {
            set(i, v, 2 * x + 1, lx, mx);
        } else {
            set(i, v, 2 * x + 2, mx, rx);
        }
        pair<ll, ll> left = nums[2 * x + 1];
        pair<ll, ll> right = nums[2 * x + 2];
        ll smallest = min(left.F, right.F);
        ll count = 0;
        if (left.F == smallest) count += left.S;
        if (right.F == smallest) count += right.S;
        nums[x] = {smallest, count};
    }
    pair<ll, ll> range_min(int l, int r) { return range_min(l, r, 0, 0, size); }

    pair<ll, ll> range_min(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) return {INT_MAX, 0};
        if (lx >= l && rx <= r) return nums[x];
        int mx = (lx + rx) / 2;
        pair<ll, ll> left = range_min(l, r, 2 * x + 1, lx, mx);
        pair<ll, ll> right = range_min(l, r, 2 * x + 2, mx, rx);
        ll smallest = min(left.F, right.F);
        ll count = 0;
        if (left.F == smallest) count += left.S;
        if (right.F == smallest) count += right.S;
        return {smallest, count};
    }
};
void solve() {
    int n, m, idx, v, l, r, op;
    cin >> n >> m;
    segtree st;
    st.init(n);
    for (int i = 0; i < n; i++) {
        cin >> v;
        st.set(i, v);
    }
    for (int i = 0; i < m; i++) {
        cin >> op;
        if (op == 1) {
            cin >> idx >> v;
            st.set(idx, v);
        } else {
            cin >> l >> r;
            pair<ll, ll> ans = st.range_min(l, r);
            cout << ans.F << " " << ans.S << endl;
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
