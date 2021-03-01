#include <iostream>
#include <vector>
using namespace std;

vector<int> dfs_num;
vector<int> dfs_parent;
vector<vector<pair<int, int>>> graph; // [node, edge_weight]
int n;
const int DFS_WHITE = 0;
const int DFS_GRAY = 1;
const int DFS_BLACK = 2;

void graphCheck(int cur)
{
    dfs_num[cur] = DFS_GRAY;
    for (pair<int, int> &nn : graph[cur])
    {
        int neighbor = nn.first;
        if (dfs_num[neighbor] == DFS_WHITE) // DFS_GRAY TO DFS_WHITE
        {
            dfs_parent[neighbor] = cur;
            graphCheck(cur);
            cout << "TREE EDGE";
        }
        else if (dfs_num[neighbor] == DFS_GRAY) // DFS_GRAY TO DFS_GRAY
        {
            if (dfs_parent[cur] == neighbor)
            {
                cout << "BIDIRECTIONAL EDGE";
            }
            else
            {
                cout << "BACK EDGE FORMS A CYCLE";
            }
        }
        else // DFS GRAY TO DFS BLACK
        {
            cout << "FORWARD / CROSS EDGE";
        }
    }
    dfs_num[cur] = DFS_BLACK;
}

int main()
{
    dfs_num.assign(n, DFS_WHITE);
    dfs_num.assign(n, -1);
    for (int i = 0; i < n; i++)
    {
        if (dfs_num[i] == DFS_WHITE)
        {
            graphCheck(i);
        }
    }
}