package representations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DirectedAL<V>
{
    private Map<V, Node<V>> adjacencyLists;
    private int vertexSize;
    private int edgeSize;

    public DirectedAL()
    {
        adjacencyLists = new HashMap<>();
    }

    public void addVertex(V vertex)
    {

    }

    public void addEdge(V from, V to)
    {

    }

    public boolean containsVertex(V vertex)
    {
        return false;
    }

    public boolean containsEdge(V from, V to)
    {
        return false;
    }

    public boolean removeVertex(V vertex)
    {
        return false;
    }

    public boolean removeEdge(V from, V to)
    {
        return false;
    }

    public void clear()
    {

    }

    public int getVertexSize()
    {
        return 0;
    }

    public int getEdgeSize()
    {
        return 0;
    }

    public Set<V> vertices()
    {
        return null;
    }

    public Set<Edge> edges()
    {
        return null;
    }

    public class Edge
    {
        private V from;
        private V to;
        private double weight;

        public Edge(V from, V to, double weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom()
        {
            return from;
        }

        public V getTo()
        {
            return to;
        }

        public double getWeight()
        {
            return weight;
        }

        @Override
        public String toString()
        {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    private class Node<V>
    {
        private V vertex;
        private Node next;
        private double weight;

        public Node(V vertex, Node next, double weight)
        {
            this.vertex = vertex;
            this.next = next;
            this.weight = weight;
        }
    }
}
