import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Max Flow: Edmonds Karp's Algorithm
 */
public class MaxFlow {

    static int V, source, target; // s != t
    static ArrayList<Integer>[] adjList;
    static int[][] edge_val; // instead of edge_val, you can use c[][], f[][] so as not to destroy the graph
    static int[] p; // edge_val (edge_validual) = c (capacity) - f (flow)

    static int edmondsKarp() // O(min(VE^2, flow * E)) for adjList, O(V^3E)
    {
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

    static int augment(int v, int flow) {
        if (v == source)
            return flow;
        flow = augment(p[v], Math.min(flow, edge_val[p[v]][v]));
        edge_val[p[v]][v] -= flow;
        edge_val[v][p[v]] += flow;

        return flow;
    }
}
