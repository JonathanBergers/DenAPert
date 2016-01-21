package implementation;

import generic.Edge;
import generic.GenericGraph;
import generic.Vertex;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by jonathan on 13-1-16.
 */
public class PERT extends GenericGraph<ActivityEdge, VertexMilestone> {


    public PERT() {

    }


    /**
     * Berekent de vroegste tijd voor elke knoop
     */
    public void vroegsteTijden(){

        
        //kopieen, want er worden edges verwijdert
        ArrayList<VertexMilestone> verticesCopy = getCopyOfMilestones();
        ArrayList<ActivityEdge> edgesCopy = getCopyOfEdges(verticesCopy);

        //lijst met mogelijke opties om door te gaan.
        Queue<VertexMilestone> options = new LinkedList<VertexMilestone>();
        options.addAll(getBeginpoints(verticesCopy));


        while (!options.isEmpty()){
            //de eerste die aan de beurt is
            VertexMilestone milestone = options.poll();

            //elke uitgaande edge
            for(Edge edge : milestone.getOut()){
                //voeg waarde toe aan de kopie, maar ook aan de echte lijst
                //waarde = waarde van vorige milestone earliest+ de edge
                int earliest = milestone.getEarliest() + (Integer) edge.getValue();
                VertexMilestone nextMilestone = (VertexMilestone) edge.getTo();
                if(earliest > nextMilestone.getEarliest()){
                    getMilestoneByKey((String) edge.getTo().getKey()).setEarliest(earliest);
                    nextMilestone.setEarliest(earliest);
                }


                //haal de edge uit de lijst met incoming edges
                nextMilestone.getIn().remove(edge);

                //als de milestone geen inkomende edges heeft, kan deze worden gestart, dus wordt deze aan options toegevoegd.
                if(nextMilestone.getIn().isEmpty()){
                    options.add(nextMilestone);
                }
            }
            //      /\
            //     /__\
            //      ||    go up
            //      ||
        }


        //alle earliest waarden zijn nu ingevuld

    }

    /**
     * Berekent de laatste tijd voor elke knoop
     */
    public void laatsteTijden(){
        //eerst moet de earliestTime berekent zijn

        //kopieen, want er worden edges verwijdert
        ArrayList<VertexMilestone> verticesCopy = getCopyOfMilestones();
        ArrayList<ActivityEdge> edgesCopy = getCopyOfEdges(verticesCopy);

        //lijst met mogelijke opties om door te gaan.
        Queue<VertexMilestone> options = new LinkedList<VertexMilestone>();

        //lijst met endpointspoints
        ArrayList<VertexMilestone> endpoints = getEndpoints(verticesCopy);

        //begin achteraan
        options.addAll(endpoints);

        //bereken beginwaarde van latestTime
        int currentLongestValue = 0;
        for(VertexMilestone milestone : endpoints){
            //als de waarde van de milestone groter is dan de currentLongestValue, is de waarde de nieuwe currentLongest value
            if(milestone.getEarliest() > currentLongestValue){
                currentLongestValue = milestone.getEarliest();
            }
        }
        //zet alle beginwaardes in de vertices copy
        for(VertexMilestone milestone : verticesCopy){
            //voor de copy en de echte lijst
            milestone.setLongest(currentLongestValue);
            getMilestoneByKey(milestone.getKey()).setLongest(currentLongestValue);
        }



        while (!options.isEmpty()){
            //System.out.println("Options not empty");
            //de eerste die aan de beurt is
            VertexMilestone milestone = options.poll();

            //elke inkomende edge


            for(Edge edge : milestone.getIn()){


                //voeg waarde toe aan de kopie, maar ook aan de echte lijst
                //waarde = waarde van vorige milestone longest - de edge waarde

                int longest = milestone.getLongest() - (Integer) edge.getValue();
                VertexMilestone nextMilestone = (VertexMilestone) edge.getFrom();
                if(longest < nextMilestone.getLongest() || nextMilestone.getLongest() == currentLongestValue){
                    getMilestoneByKey((String) edge.getFrom().getKey()).setLongest(longest);
                    nextMilestone.setLongest(longest);
                }

                //System.out.println("length of outList from "+nextMilestone.getKey()+" = "+nextMilestone.getOut().size() );
                for(Edge edge1 : nextMilestone.getOut()){
                    //System.out.println("from : "+edge1.getFrom().getKey()+ "\t\t to : "+edge1.getTo().getKey());
                }


                //haal de edge uit de lijst met outgoing edges
                nextMilestone.getOut().remove(edge);


                //als de milestone geen uitgaande edges heeft, kan deze worden gestart, dus wordt deze aan options toegevoegd.
                if(nextMilestone.getOut().isEmpty()){
                    options.add(nextMilestone);
                }


            }


            //      /\
            //     /__\
            //      ||    go up
            //      ||

        }



    }

    /**
     * voegt een VertexMilestone toe aan dit PERT-netwerk
     * @param key de key van de VertexMilestone die toegevoegd dient te worden
     */
    public void addMilestone(String key){
        //key mag nog niet bezet zijn
        if(getMilestoneByKey(key) != null){
            System.out.println("Key "+key+" is already in use");
            return;
        }
        vertices.add(new VertexMilestone(key));

    }

    /**
     * voegt een ActivityEdge toe aan dit PERT-netwerk
     * @param keyFrom de key van de VertexMilestone waaruit de ActivityEdge wijst.
     * @param keyTo de key van de VertexMilestone waarnaar de ActivityEdge wijst.
     * @param value de waarde van de ActivityEdge
     */
    public void addActivityEdge(String keyFrom, String keyTo, int value){

        //een activiteit moet tenminste 1 duren, anders is het geen activiteit
        if(value < 1){
            System.out.println("the value cant be below 1");
            return;
        }

        VertexMilestone from = getMilestoneByKey(keyFrom);
        VertexMilestone to = getMilestoneByKey(keyTo);

        //activiteit niet gevonden
        if(to == null || from == null){
            System.out.println("not a valid key");
            return;
        }


        edges.add(new ActivityEdge(value, from, to));


    }


    /**
     *
     * @param vertices een lijst met VertexMilestones
     * @return een lijst met de eindpunten van de gegeven lijst
     */
    private ArrayList<VertexMilestone> getBeginpoints(ArrayList<VertexMilestone> vertices){
        ArrayList<VertexMilestone> beginpoints = new ArrayList<VertexMilestone>();
        for(VertexMilestone milestone : vertices ){
            //check if the milestone is a begin point
            if(milestone.isBegin()){
                beginpoints.add(milestone);
            }

        }
        return beginpoints;
    }

    /**
     *
     * @param vertices een lijst met VertexMilestones
     * @return een lijst met de eindpunten van de gegeven lijst
     */
    private ArrayList<VertexMilestone> getEndpoints(ArrayList<VertexMilestone> vertices){
        ArrayList<VertexMilestone> endpoints = new ArrayList<VertexMilestone>();
        for(VertexMilestone milestone : vertices ){
            //check if the milestone is a begin point
            if(milestone.isEnd()){
                endpoints.add(milestone);
            }

        }
        return endpoints;

    }

    /**
     *
     * @return een lijst met kopieen van de VertexMilestones
     */
    private ArrayList<VertexMilestone> getCopyOfMilestones(){
        ArrayList<VertexMilestone> copy = new ArrayList<VertexMilestone>();
        for(VertexMilestone milestone : vertices){
            copy.add(milestone.copyOf());
        }
        return copy;
    }

    /**
     *
     * @param copyOfMilestones een lijst met kopieen van de milestones
     * @return een lijst met kopieen van de ActivityEdges
     */
    private ArrayList<ActivityEdge> getCopyOfEdges(ArrayList<VertexMilestone> copyOfMilestones){
        ArrayList<ActivityEdge> copy = new ArrayList<ActivityEdge>();
        for(ActivityEdge edge : edges){
            //anders werkt de copy niet
            int indexVertexFrom = vertices.indexOf(getMilestoneByKey(edge.getFrom().getKey()));
            int indexVertexTo = vertices.indexOf(getMilestoneByKey(edge.getTo().getKey()));
            copy.add(edge.copyOf(copyOfMilestones.get(indexVertexFrom), copyOfMilestones.get(indexVertexTo)));
        }
        return copy;
    }

    /**
     *
     * @param key key van de gewilde VertexMilestone
     * @return de VertexMilestone met de gegeven key
     */
    private VertexMilestone getMilestoneByKey(String key){
        for (VertexMilestone milestone : vertices){
            if(key.equals(milestone.getKey())){
                return milestone;
            }
        }
        return null;
    }


    /**
     * maakt een DOTString, zodat deze weergegeven kan worden op http://webgraphviz.com/
     * @return de string
     */
    public String toDOTString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph mijngraaf {\n");


        // Create nodes
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(String.format("%s [label=\"%s\"];\n", vertices.get(i).getKey(), vertices.get(i).getKey()+" ("+vertices.get(i).getEarliest()+", "+vertices.get(i).getLongest()+")"));
        }

        // Create edges
        for (int i = 0; i < edges.size(); i++) {
            sb.append(String.format("%s -> %s;\n", edges.get(i).getFrom().getKey(), edges.get(i).getTo().getKey()));

        }

        sb.append("}");
        return sb.toString();



    }



}
