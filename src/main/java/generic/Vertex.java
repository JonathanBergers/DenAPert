package generic;

import java.util.List;

/**
 * Created by jonathan on 13-1-16.
 */
public abstract class Vertex<K, V> {


    List<Edge> in;
    List<Edge> out;

    K key;
    V value;


    
}
