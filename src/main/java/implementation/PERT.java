package implementation;

import generic.Edge;
import generic.GenericGraph;
import generic.Vertex;

import java.util.List;

/**
 * Created by jonathan on 13-1-16.
 */
public class PERT extends GenericGraph<ActivityEdge, VertexMilestone> {









    void transform(){

    }


    private void calculateEarliestTime(){
        /*
        bepaal beginpunten
            in begin punt:
            voor elke vertext:
                uitgaande earliest = this earliest + vertex.incoming edge value, als kleiner is
        topologisch doorlopen
         */

    }

    private void calculateLatestTime(){



    }

    private boolean hasCycle(){
        return false;
    }



    private List<VertexMilestone> getBeginpoints(){

        return null;
    }


    private List<VertexMilestone> getEndpoints(){
        return null;

    }



}
