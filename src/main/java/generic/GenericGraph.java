package generic;

import java.util.List;

/**
 * Created by jonathan on 13-1-16.
 */
public abstract class GenericGraph<E extends Edge, V extends Vertex> {

    List<V> vertices;
    List<E> edges;


}
