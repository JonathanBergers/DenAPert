package implementation;

import generic.Edge;
import generic.Vertex;
import javafx.util.Pair;

/**
 * Created by jonathan on 13-1-16.
 */
public class ActivityEdge extends Edge<Integer, VertexMilestone> {


    public ActivityEdge(Integer value, VertexMilestone from, VertexMilestone to) {
        super(value, from, to);

        // voeg de nieuwe edge toe aan de milestones.
        from.getOut().add(this);
        to.getIn().add(this);


    }

    public ActivityEdge copyOf(VertexMilestone from, VertexMilestone to){
        return  new ActivityEdge(value, from, to );
    }



}
