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

    //pass any number of vertices into the graph
    public void addVertex(V... vertices)
    {
        for (V vertex : vertices)
        {
            addVertex(vertex);
        }
    }

    public boolean addVertex(V vertex)
    {
        if (containsVertex(vertex))
        {
            return false;
        }
        else
        {
            adjacencyLists.put(vertex, null);
            return true;
        }
    }

    public void addEdge(Edge<V>... edges)
    {
        for (Edge<V> edge : edges)
        {
            addEdge(edge.from, edge.to);
        }
    }

    public boolean addEdge(V from, V to)
    {
        //make sure the vertices exist, make sure this is not a duplicate edge
        if (!adjacencyLists.containsKey(from) || !adjacencyLists.containsKey(to) ||
            containsEdge(from, to))
        {
            return false;
        }

        if (adjacencyLists.get(from) == null)
        {
            adjacencyLists.put(from, new Node<V>(to));
            return true;
        }
        else
        {
            //create a new head of the linked list of adjacent vertices
            Node<V> newNode = new Node<>(to);
            newNode.next = adjacencyLists.get(from);

            adjacencyLists.put(from, newNode);
            return true;
        }
    }

    public boolean containsVertex(V vertex)
    {
        return adjacencyLists.containsKey(vertex);
    }

    public boolean containsEdge(V from, V to)
    {
        if (!adjacencyLists.containsKey(from) || !adjacencyLists.containsKey(to))
        {
            return false;
        }

        Node<V> current = adjacencyLists.get(from);
        while (current != null)
        {
            //is this the "to" vertex
            if (current.vertex.equals(to))
            {
                return true;
            }

            //otherwise move to the next adjacent vertex
            current = current.next;
        }

        return false; //we never found the vertex
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

    public Set<Edge<V>> edges()
    {
        return null;
    }

    public boolean removeVertex(V vertex)
    {
        return false;
    }

    public boolean removeEdge(V from, V to)
    {
        return false;
    }

    public static class Edge<V>
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

        public Node(V vertex)
        {
            this(vertex, null, 0.0);
        }

        public Node(V vertex, Node next, double weight)
        {
            this.vertex = vertex;
            this.next = next;
            this.weight = weight;
        }
    }
}
