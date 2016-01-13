package implementation;

/**
 * Created by jonathan on 13-1-16.
 */
public class PERTVertexValues {

    private int earliest, longest;


    public PERTVertexValues(int earliest, int longest) {
        this.earliest = earliest;
        this.longest = longest;
    }

    public int getEarliest() {
        return earliest;
    }

    public int getLongest() {
        return longest;
    }

    public void setEarliest(int earliest) {
        this.earliest = earliest;
    }

    public void setLongest(int longest) {
        this.longest = longest;
    }
}
