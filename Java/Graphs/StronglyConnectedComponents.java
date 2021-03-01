import java.util.*;

public class StronglyConnectedComponents {

    // Given a directed graph, returns the scc table in O(N) time using Kosaraju
    public int[] scc(ArrayList<Integer>[] graph) { // returns scc table that defines what scc the node i belongs to

        int n = graph.length;

        // dfs and add to stack,
        // pop from stack and add it to SCC
        Stack<Integer> stack = new Stack();
        HashSet<Integer> first_visited = new HashSet();
        for (int i = 0; i < n; i++) {
            if (first_visited.contains(i))
                continue;
            first_dfs(graph, stack, first_visited, i);
        }

        HashSet<Integer> second_visited = new HashSet();
        ArrayList<Integer>[] reverse_graph = reverse(graph);
        int scc_num = 0;
        int[] scc = new int[n];

        while (stack.size() > 0) {
            int cur_node = stack.pop();
            if (second_visited.contains(cur_node))
                continue;
            second_dfs(reverse_graph, stack, second_visited, scc, cur_node, scc_num);
            scc_num++;
        }
        return scc;

    }

    public ArrayList<Integer>[] reverse(ArrayList<Integer>[] graph) {
        int n = graph.length;
        ArrayList<Integer>[] reverse_graph = new ArrayList<Integer>[n];
        for (int i = 0; i < n; i++)
            reverse_graph[i] = new ArrayList();
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                reverse_graph[neighbor].add(i);
            }
        }
        return reverse_graph;
    }

    // add to stack for first dfs call
    public void first_dfs(ArrayList<Integer>[] graph, Stack<Integer> stack, HashSet<Integer> visited, int cur_node) {
        if (visited.contains(cur_node))
            return;
        visited.add(cur_node);
        for (int neighbor : graph[cur_node]) {
            if (visited.contains(neighbor))
                continue;
            first_dfs(graph, stack, visited, neighbor);
        }
        stack.push(cur_node);
    }

    // pop from stack to fill in SCC. All visited from this call is in the same
    // stack
    public void second_dfs(ArrayList<Integer>[] reverse_graph, Stack<Integer> stack, HashSet<Integer> visited,
            int[] scc, int cur_node, int scc_num) {
        if (visited.contains(cur_node))
            return;
        visited.add(cur_node);
        scc[cur_node] = scc_num;
        for (int neighbor : reverse_graph[cur_node]) {
            if (visited.contains(neighbor))
                continue;
            second_dfs(reverse_graph, stack, visited, scc, neighbor, scc_num);
        }
    }
}
