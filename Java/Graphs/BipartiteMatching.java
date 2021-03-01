import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

/*
 * Max Flow: Edmonds Karp's Algorithm
 */
public class BipartiteMatching {

    static int V, source, target; // s != t
    static ArrayList<Integer>[] adjList;
    static int[][] edge_val; // instead of edge_val, you can use c[][], f[][] so as not to destroy the graph
    static int[] p; // edge_val (edge_validual) = c (capacity) - f (flow)

    static int bipartiteMatching() // O(min(VE^2, flow * E)) for adjList, O(V^3E) - uses Edmond Karp Algorithm
                                   // (Ford Fulkerson)
    {
        // making bipartite matching work starts here
        int n = adjList.length;
        source = n; // dummy node attached to one side of the bipartite graph
        target = n + 1; // dummy node attached to the other side of the bipartite graph
        HashSet<Integer> set1 = new HashSet(); // find all the nodes of one side of the graph
        HashSet<Integer> set2 = new HashSet(); // find all the nodes of the other side of the graph
        find_set(0, set1, set2, 0);
        ArrayList<Integer>[] new_adjList = new ArrayList[n + 3];
        for (int i = 0; i < n; i++)
            new_adjList[i] = adjList[i];
        new_adjList[n] = new ArrayList();
        new_adjList[n + 1] = new ArrayList();

        for (int set1_node : set1)
            adjList[n].add(set1_node);
        for (int set2_node : set2)
            adjList[n + 1].add(set2_node);

        edge_val = new int[n + 3][n + 3];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(edge_val[i], 1);
        }
        // making bipartite matching work ends here

        // start of edmonds karp algortithm fromsource to target.
        int INF = (int) 1e9;
        int mf = 0;
        while (true) {
            Queue<Integer> q = new LinkedList<Integer>();
            p = new int[V];
            Arrays.fill(p, -1);
            q.add(source);
            p[source] = source;
            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == target)
                    break;
                for (int v : adjList[u])
                    if (edge_val[u][v] > 0 && p[v] == -1) {
                        p[v] = u;
                        q.add(v);
                    }
            }

            if (p[target] == -1)
                break;
            mf += augment(target, INF);
        }
        return mf;
    }

    static void find_set(int cur, HashSet<Integer> set1, HashSet<Integer> set2, int cur_set) {
        if (set1.contains(cur) || set2.contains(cur))
            return;
        if (cur_set == 0)
            set1.add(cur);
        else {
            set2.add(cur);
        }
        for (int neighbor : adjList[cur]) {
            if (set1.contains(neighbor) || set2.contains(neighbor))
                continue;
            find_set(neighbor, set1, set2, cur_set ^ 1);
        }
    }

    static int augment(int v, int flow) {
        if (v == source)
            return flow;
        flow = augment(p[v], Math.min(flow, edge_val[p[v]][v]));
        edge_val[p[v]][v] -= flow;
        edge_val[v][p[v]] += flow;

        return flow;
    }
}
