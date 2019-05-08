package representations;

import representations.DirectedAL.Edge;

public class TestGraph
{
    public static void main(String[] args)
    {
        DirectedAL<Integer> numberGraph = new DirectedAL<>();

        //add a few vertices
        numberGraph.addVertex(10, 4, 2, 6, 7, 8);

        //add a few edges
        numberGraph.addEdge(
            new Edge<>(10, 2, 0),
            new Edge<>(2, 4, 0),
            new Edge<>(6, 7, 0),
            new Edge<>(6, 2, 0)
        );

        //verify that all is well!
        System.out.println("Verifying things in the graph: ");
        System.out.println(numberGraph.containsVertex(10));
        System.out.println(numberGraph.containsVertex(2));
        System.out.println(numberGraph.containsVertex(6));

        System.out.println(numberGraph.containsEdge(10, 2));
        System.out.println(numberGraph.containsEdge(6, 2));
        System.out.println(numberGraph.containsEdge(6, 7));

        System.out.println("Verifying things not in the graph");
        System.out.println(numberGraph.containsVertex(11));
        System.out.println(numberGraph.containsEdge(1, 2)); //missing vertex
        System.out.println(numberGraph.containsEdge(10, 6)); //missing edge
    }
}
