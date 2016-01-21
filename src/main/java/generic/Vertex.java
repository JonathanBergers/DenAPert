package generic;

import implementation.PERTVertexValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 13-1-16.
 */
public abstract class Vertex<K, V> {


    protected List<Edge> in = new ArrayList<Edge>();
    protected List<Edge> out = new ArrayList<Edge>();

    protected K key;
    protected V value;

    public Vertex(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public List<Edge> getIn() {
        return in;
    }

    public List<Edge> getOut() {
        return out;
    }

    public void addIncoming(Edge edge) {
        in.add(edge);
    }

    public void addOutgoing(Edge edge) {
        out.add(edge);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}