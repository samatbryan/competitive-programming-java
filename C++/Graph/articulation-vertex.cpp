#include <vector>
#include <stdio>
using namespace std;

const int DFS_WHITE = 0;
const int DFS_GRAY = 1;

vector<vector<int>> graph;
vector<int> dfs_num;
vector<int> dfs_low;
vector<int> parent;

int dfsCount;

void articulation(int cur)
{
    dfs_low[cur] = dfs_num[cur] = dfsCount;
    dfsCount++;
    for (int neighbor : graph[cur])
    {
        if (dfs_num[cur] == DFS_WHITE)
        {
            parent[neighbor] = cur;
            articulation(neighbor);

            if (dfs_low[neighbor] >= dfs_num[cur])
            {
                cout << "CUR " << cur << " IS AN ARTICULATION VERTEX" << endl;
            }
            dfs_low[cur] = min(dfs_low[cur], dfs_low[neighbor]);
        }

        else if (parent[cur] != neighbor)
        {
            dfs_low[cur] = min(dfs_low[cur], dfs_low[neighbor]);
        }
    }
}
int main()
{
    dfsCount = 0;
    for (int i = 0; i < n; i++)
    {
        if (dfs_num[i] != DFS_WHITE)
        {
            articulation(i);
        }
    }
    // take care of the root 0
    if (graph[0].size() >= 2)
    {
        cout << "0 IS AN ARTICULATION VERTEX" << endl;
    }
}