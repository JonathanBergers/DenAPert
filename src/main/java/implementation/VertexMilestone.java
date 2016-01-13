package implementation;

import generic.Vertex;

/**
 * Created by jonathan on 13-1-16.
 */
public class VertexMilestone extends Vertex<String, PERTVertexValues> {


    public VertexMilestone(final String key, final int earliest, final int latest){
        super(key, new PERTVertexValues(earliest, latest));
    }

    public void setEarliest(final int earliest){
        value.setEarliest(earliest);
    }

    public void setLongest(final int longest){
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




}
