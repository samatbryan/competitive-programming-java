class Node implements Comparator<Node> {
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