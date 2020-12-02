public class MaxQ<T extends Comparable<T>>{
    
    private LinkedList<T> q;
    
    public MaxQ(){
        this.q = new LinkedList<T>();
    }
    
    public T get_max(){
        return q.getFirst();
    }

    public T pop_max(){
        return q.removeFirst();
    }

    public void add(T item){
        while(!q.isEmpty() && (item.compareTo(q.getLast()) > 0)){
            q.removeLast();
        }
        q.addLast(item);
    }

    public int size(){
        return this.q.size();
    }
}