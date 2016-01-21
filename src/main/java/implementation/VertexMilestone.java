package implementation;

import generic.Edge;
import generic.Vertex;

/**
 * Created by jonathan on 13-1-16.
 */
public class VertexMilestone extends Vertex<String, PERTVertexValues> {


    public VertexMilestone(final String key, int earliest, int latest){
        super(key, new PERTVertexValues(earliest, latest));
    }

    public VertexMilestone(final String key){
        // 0 als begin waarde..
        super(key, new PERTVertexValues(0, 0));
    }

    public void setEarliest(final int earliest){
        //System.out.println("earliest time set");
        value.setEarliest(earliest);
    }

    public void setLongest(final int longest){
        //System.out.println("longest time set");
        value.setLongest(longest);
    }

    public boolean isBegin(){
        return in.isEmpty();
    }

    public boolean isEnd(){
        return out.isEmpty();
    }

    public int getEarliest(){
        return value.getEarliest();
    }

    public int getLongest(){
        return value.getLongest();
    }

    public VertexMilestone copyOf(){
        VertexMilestone copy = new VertexMilestone(key, getEarliest(), getLongest());
//        for(Edge inEdge : in){
//            copy.getIn().add(inEdge);
//        }
//        for(Edge outEdge : out){
//            copy.getOut().add(outEdge);
//        }
        return copy;
    }






}
