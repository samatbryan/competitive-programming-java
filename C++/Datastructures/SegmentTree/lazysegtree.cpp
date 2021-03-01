#include <iostream>
#include <vector>
#include <climits>
using namespace std;
struct segtree
{
    int size;
    long long NO_OPERATION = LLONG_MAX;
    vector<long long> operations;

    long long operation(long long a, long long b)
    {
        if (b == NO_OPERATION)
            return a;
        return b;
    }

    void apply_operation(long long &a, long long b)
    {
        a = operation(a, b);
    }

    void init(int n)
    {
        size = 1;
        while (size < n)
            size *= 2;
        operations.assign(2 * size, 0LL);
    }

    void propagate(int x, int lx, int rx)
    {
        if (rx - lx == 1)
            return;
        apply_operation(operations[2 * x + 1], operations[x]);
        apply_operation(operations[2 * x + 2], operations[x]);
        operations[x] = NO_OPERATION;
    }

    void modify(int l, int r, int v)
    {
        modify(l, r, v, 0, 0, size);
    }

    void modify(int l, int r, int v, int x, int lx, int rx)
    {
        propagate(x, lx, rx);
        if (lx >= r || l >= rx)
            return;
        if (lx >= l && rx <= r)
        {
            apply_operation(operations[x], v);
            return;
        }

        int mx = (lx + rx) / 2;
        modify(l, r, v, 2 * x + 1, lx, mx);
        modify(l, r, v, 2 * x + 2, mx, rx);
    }

    long get(int i)
    {
        return get(i, 0, 0, size);
    }

    long get(int i, int x, int lx, int rx)
    {
        if (rx - lx == 1)
            return operations[x];
        int mx = (lx + rx) / 2;
        long long res;
        if (i < mx)
        {
            res = get(i, 2 * x + 1, lx, mx);
        }
        else
        {
            res = get(i, 2 * x + 2, mx, rx);
        }
        return operation(res, operations[x]);
    }
};

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, m;
    cin >> n >> m;
    segtree st;
    st.init(n);

    for (int i = 0; i < m; i++)
    {
        int q;
        cin >> q;
        if (q == 1)
        {
            int l, r, v;
            cin >> l >> r >> v;
            st.modify(l, r, v);
        }
        else
        {
            int idx;
            cin >> idx;
            cout << st.get(idx) << endl;
        }
    }
}
