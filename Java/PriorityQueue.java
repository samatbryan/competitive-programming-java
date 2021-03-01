// This is the format for having custom complex comparators for 
// priorityqueue 
PriorityQueue<Fruit>pq=new PriorityQueue<Fruit>();
static class Fruit implements Comparable<Fruit> {
    int c;
    int s;
    int t;
    int use;
    int max;
    int cost;

    public Fruit(int c, int s, int t) {
            this.c = c;
            this.s = s;
            this.t = t;
            use = Math.min(sz / s, t);
            max = s * use;
            cost = c * use;
        }

    public int compareTo(Fruit other) {
        if (max != other.max) {
            return other.max - max;
        } else {
            return cost - other.cost;
        }
    }
}