public class MinQ<T extends Comparable<T>>{
    
    private LinkedList<T> q;
    
    public MinQ(){
        this.q = new LinkedList<T>();
    }
    
    public T get_min(){
        return q.getFirst();
    }

    public T pop_min(){
        return q.removeFirst();
    }

    public void add(T item){
        while(!q.isEmpty() && (item.compareTo(q.getLast()) < 0)){
            q.removeLast();
        }
        q.addLast(item);
    }

    public int size(){
        return this.q.size();
    }
}