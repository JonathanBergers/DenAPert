package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 13-1-16.
 */
public abstract class Vertex<K, V> {

    protected List<Edge> in = new ArrayList<>();
    protected List<Edge> out = new ArrayList<>();

    protected K key;
    protected V value;


    public void addIncoming(Edge edge){
        in.add(edge);
    }
    public void addOutGoing(Edge edge){
        out.add(edge);
    }

    public Vertex(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
