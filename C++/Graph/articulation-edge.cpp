#include <vector>
#include <stdio>

using namespace std;

vector<vector<int>> graph;
vector<int> parent;
vector<int> dfs_num;
vector<int> dfs_low;
int counter;
const int DFS_WHITE = -1;
vector<vector<int>> res;

void articulation(int cur)
{
    dfs_num[cur] = counter;
    dfs_low[cur] = counter;
    counter++;
    for (int neighbor : graph[cur])
    {
        if (dfs_num[neighbor] == DFS_WHITE)
        {
            parent[neighbor] = cur;

            articulation(neighbor); // recurse

            if (dfs_low[neighbor] > dfs_num[cur])
            {
                cout << "EDGE " << neighbor << " TO " << cur << " IS AN ARTICULATION EDGE" << endl;
            }
            dfs_low[cur] = min(dfs_low[cur], dfs_low[neighbor]);
        }

        else
        { // back edge
            if (parent[cur] != neighbor)
            {
                dfs_low[cur] = min(dfs_low[cur], dfs_low[neighbor]);
            }
        }
    }
}
int main()
{
    int n;
    dfs_num.assign(n, DFS_WHITE);
    counter = 0;
    res.clear();
    for (int i = 0; i < n; i++)
    {
        if (dfs_num[i] == DFS_WHITE)
        {
            articulation(i);
        }
    }
}
