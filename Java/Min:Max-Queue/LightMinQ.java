public class LightMinQ {
    private LinkedList<Integer> q;

    /**
     * Class constructor for the MaxQ class using Generic Type. A MaxQ inserts item
     * in O(1) time and fetches the maximum element in O(1) time example usage: MaxQ
     * maxq = new MaxQ();
     */
    public LightMinQ() {
        this.q = new LinkedList<Integer>();
    }

    /**
     * Time Complexity: O(1) Returns the maximum of all elements currently in the
     * queue
     * 
     * @return The maximum element in the queue
     */
    public int get_min() {
        return q.getFirst();
    }

    /**
     * Time Complexity: O(1) Pops and removes the maximum of all elements currently
     * in the queue
     * 
     * @return The maximum element in the queue
     */
    public int pop_min() {
        return q.removeFirst();
    }

    /**
     * Time Complexity: O(1) Adds an item to the queue and maintains the monotonic
     * property of the queue
     * 
     * @param item The item to add to the queue
     */
    public void add(int item) {
        while (!q.isEmpty() && (item < q.getLast())) {
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