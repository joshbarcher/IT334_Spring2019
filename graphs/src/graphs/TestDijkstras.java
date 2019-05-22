package graphs;

import java.util.Map;
import graphs.DirectedAL.Edge;

public class TestDijkstras
{
    public static void main(String[] args)
    {
        DirectedAL<Character> letterGraph = new DirectedAL<>();

        letterGraph.addVertex('a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm');

        letterGraph.addUndirectedEdge(
            new Edge<Character>('a', 'b', 1.0),
            new Edge<Character>('a', 'f', 2.0),

            new Edge<Character>('b', 'c', 2.0),

            new Edge<Character>('e', 'b', 2.0),
            new Edge<Character>('e', 'c', 1.0),
            new Edge<Character>('e', 'd', 3.0),
            new Edge<Character>('e', 'f', 3.0),
            new Edge<Character>('e', 'g', 1.0),
            new Edge<Character>('e', 'h', 2.0),
            new Edge<Character>('e', 'i', 5.0),

            new Edge<Character>('f', 'j', 4.0),
            new Edge<Character>('i', 'h', 2.0),

            new Edge<Character>('k', 'l', 1.0),
            new Edge<Character>('k', 'm', 3.0),
            new Edge<Character>('l', 'm', 4.0)
        );

        Map<Character, Character> results = letterGraph.dijkstras('b');

        //print the map
        for (char vertex : results.keySet())
        {
            System.out.println(vertex + " -> " + results.get(vertex));
        }
    }
}
