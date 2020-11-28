import java.util.*;


public class Dijkstras {
      int V;
      int[] dist;
      ArrayList<Node>[] adj;

      Dijkstras(int n) {
          V = n;
          dist = new int[V];
          adj = new ArrayList[V];
          for (int i = 0; i < V; i++) {
              adj[i] = new ArrayList<Node>();
          }
      }

      public void addEdge(int u, int v, int weight) {
          Node n = new Node(v, weight);
          adj[u].add(n);
      }

      public int[] dijkstras(int src) {
          HashSet<Integer> popped = new HashSet();
          PriorityQueue<Node> pq = new PriorityQueue<Node>(V, new Node());

          Arrays.fill(dist, Integer.MAX_VALUE);
          dist[src] = 0;
          pq.add(new Node(src, 0));

          while (!pq.isEmpty()) {
              Node n = pq.remove();
              popped.add(n.node);
              for (Node neighbor : adj[n.node]) {
                  if (popped.contains(neighbor.node)) {
                      continue;
                  }
                  if (dist[neighbor.node] > dist[n.node] + neighbor.cost) {
                      dist[neighbor.node] = dist[n.node] + neighbor.cost;
                      pq.add(new Node(neighbor.node, dist[neighbor.node]));
                  }
              }
          }
          return dist;
      }
      public ArrayList<Node> neighbor(int u) {
          return adj[u];
      }
  }

  static class Node implements Comparator<Node> {
      int node;
      int cost;

      Node() {
      }
      
      Node(int node, int cost) {
          this.node = node;
          this.cost = cost;
      }

      @Override
      public int compare(Node node1, Node node2) {
          return node1.cost - node2.cost;
      }
  }


