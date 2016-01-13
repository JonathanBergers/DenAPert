package generic;

/**
 * Created by jonathan on 13-1-16.
 */
public abstract class Edge<T, V extends Vertex> {

    protected T value;
    protected V from , to;


    public Edge(T value, V from, V to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }
}
