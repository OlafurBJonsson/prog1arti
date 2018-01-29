import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;



public class UniCost{
    

    public static void UniformCostSearch(Nodes s, Nodes g){

        s.pathCost = 0;
        PriorityQueue<Nodes> queue = new PriorityQueue<Nodes>(); 
            new Comparator<Nodes>(){

                
                public int compare(Nodes i, Nodes j){
                    if(i.pathCost > j.pathCost){
                        return 1;
                    }

                    else if (i.pathCost < j.pathCost){
                        return -1;
                    }

                    else{
                        return 0;
                    }
                }
            }

        ;

        queue.add(s);
        Set<Nodes> explored = new HashSet<Nodes>();
        boolean found = false;

        //while frontier is not empty
        do{

            Nodes current = queue.poll();
            explored.add(current);


            if(current.value.equals(g.value)){
                found = true;


            }




            for(Edge e: current.adjacencies){
                Nodes child = e.target;
                int cost = e.cost;
                child.pathCost = current.pathCost + cost;



                if(!explored.contains(child) && !queue.contains(child)){

                    child.parent = current;
                    queue.add(child);

                    System.out.println(child);
                    System.out.println(queue);
                    System.out.println();

                }


                else if((queue.contains(child))&&(child.pathCost>current.pathCost)){

                    child.parent=current;
                    current = child;

                }


            }
        }while(!queue.isEmpty());

    }

    public static List<Nodes> printPath(Nodes target){
        List<Nodes> path = new ArrayList<Nodes>();
        for(Nodes Nodes = target; Nodes!=null; Nodes = Nodes.parent){
            path.add(Nodes);
        }

        Collections.reverse(path);

        return path;

    }

}

class Nodes{

    public final String value;
    public int pathCost;
    public Edge[] adjacencies;
    public Nodes parent;

    public Nodes(String val){

        value = val;

    }

    public String toString(){
        return value;
    }

}

class Edge{
    public final int cost;
    public final Nodes target;

    public Edge(Nodes targetNodes, int costVal){
        cost = costVal;
        target = targetNodes;

    }
}
