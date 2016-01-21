package implementation;

/**
 * Created by falco on 21-1-16.
 */
public class Main {


    public static void main(String[] args) {

        PERT pert = new PERT();

        pert.addMilestone("A");
        pert.addMilestone("B");
        pert.addMilestone("C");
        pert.addMilestone("D");
        pert.addMilestone("E");
        pert.addMilestone("F");
        pert.addMilestone("G");
        pert.addMilestone("H");



        pert.addActivityEdge("A", "C", 7);
        pert.addActivityEdge("A", "F", 5);
        pert.addActivityEdge("B", "C", 9);
        pert.addActivityEdge("B", "E", 4);
        pert.addActivityEdge("C", "D", 4);
        pert.addActivityEdge("D", "G", 12);
        pert.addActivityEdge("F", "G", 2);
        pert.addActivityEdge("E", "H", 7);
        pert.addActivityEdge("F", "H", 13);



        pert.vroegsteTijden();
        pert.laatsteTijden();

        System.out.println(pert.toDOTString());






    }


}
