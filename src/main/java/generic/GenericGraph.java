package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 13-1-16.
 */
public abstract class GenericGraph<E extends Edge, V extends Vertex> {

 
    protected List<V> vertices = new ArrayList<V>();
    protected List<E> edges = new ArrayList<E>();




}
