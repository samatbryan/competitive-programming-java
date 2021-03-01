public class MinQ<T extends Comparable<T>> {
    private LinkedList<T> q;

    /**
     * Class constructor for the MinQ class using Generic Type. A MinQ inserts item
     * in O(1) time and fetches the minimum element in O(1) time example usage: MinQ
     * minq = new MinQ();
     */
    public MinQ() {
        this.q = new LinkedList<T>();
    }

    /**
     * Time Complexity: O(1) Returns the minimum of all elements currently in the
     * queue
     * 
     * @return The minimum element in the queue
     */
    public T get_min() {
        return q.getFirst();
    }

    /**
     * Time Complexity: O(1) Pops and removes the minimum of all elements currently
     * in the queue
     * 
     * @return The minimum element in the queue
     */
    public T pop_min() {
        return q.removeFirst();
    }

    /**
     * Time Complexity: O(1) Adds an item to the queue and maintains the monotonic
     * property of the queue
     * 
     * @param item The item to add to the queue
     */
    public void add(T item) {
        while (!q.isEmpty() && (item.compareTo(q.getLast()) < 0)) {
            q.removeLast();
        }
        q.addLast(item);
    }

    /**
     * Time Complexity: O(1) Returns the number of elements in the queue
     * 
     * @return the size of the queue
     */
    public int size() {
        return this.q.size();
    }
}