package representations;

import java.util.*;

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
            addEdge(edge.from, edge.to, edge.weight);
        }
    }

    public boolean addEdge(V from, V to, double weight)
    {
        //make sure the vertices exist, make sure this is not a duplicate edge
        if (!adjacencyLists.containsKey(from) || !adjacencyLists.containsKey(to) ||
            containsEdge(from, to))
        {
            return false;
        }

        if (adjacencyLists.get(from) == null)
        {
            adjacencyLists.put(from, new Node<V>(to, weight));
            return true;
        }
        else
        {
            //create a new head of the linked list of adjacent vertices
            Node<V> newNode = new Node<V>(to, weight);
            newNode.next = adjacencyLists.get(from);

            adjacencyLists.put(from, newNode);
            return true;
        }
    }

    public void addUndirectedEdge(V first, V second, double weight)
    {
        addEdge(first, second, weight);
        addEdge(second, first, weight);
    }

    public void addUndirectedEdge(Edge<V>... edges)
    {
        for (Edge<V> edge : edges)
        {
            addUndirectedEdge(edge.from, edge.to, edge.weight);
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
        adjacencyLists = new HashMap<>();
        vertexSize = 0;
        edgeSize = 0;
    }

    public int getVertexSize()
    {
        return vertexSize;
    }

    public int getEdgeSize()
    {
        return edgeSize;
    }

    public Set<V> vertices()
    {
        return Collections.unmodifiableSet(adjacencyLists.keySet());
    }

    public Set<Edge<V>> edges()
    {
        Set<Edge<V>> edges = new HashSet<>();

        for (V vertex : adjacencyLists.keySet())
        {
            Node<V> current = adjacencyLists.get(vertex);
            while (current != null)
            {
                edges.add(new Edge<V>(vertex, current.vertex, current.weight));
                current = current.next;
            }
        }
        return edges;
    }

    public boolean removeVertex(V vertex)
    {
        if (!adjacencyLists.containsKey(vertex)) return false;

        //remove outgoing edges
        adjacencyLists.put(vertex, null);

        //remove incoming edges
        for (V other : adjacencyLists.keySet())
        {
            Node<V> current = adjacencyLists.get(other);
            if (current != null)
            {
                if (current.vertex.equals(vertex))
                {
                    adjacencyLists.put(other, current.next);
                }
                else
                {
                    while (current.next != null && !current.next.vertex.equals(vertex))
                    {
                        current = current.next;
                    }

                    if (current.next != null)
                    {
                        current.next = current.next.next;
                    }
                }
            }
        }

        return true;
    }

    public boolean removeEdge(V from, V to)
    {
        if (!adjacencyLists.containsKey(from) || !adjacencyLists.containsKey(to)) return false;

        Node<V> startingPosition = adjacencyLists.get(from);
        if (startingPosition == null)
        {
            return false;
        }
        else if (startingPosition.vertex.equals(to))
        {
            adjacencyLists.put(from, startingPosition.next);
        }
        else
        {
            Node<V> before = findEdgeTo(adjacencyLists.get(from), to);

            if (before.next == null)
            {
                return false;
            }
            else
            {
                before.next = before.next.next;
            }
        }
        return true;
    }

    //finds the position where a node should be located
    private Node<V> findEdgeTo(Node<V> current, V vertex)
    {
        while (current.next != null && !current.next.vertex.equals(vertex))
        {
            current = current.next;
        }

        return current;
    }

    public Map<V, V> dijkstras(V source)
    {
        //make sure the source vertex exists
        if (!containsVertex(source))
        {
            throw new IllegalArgumentException("Source vertex does not exist: " + source);
        }

        //create our structures
        Map<V, V> results = new HashMap<>();
        Map<V, Double> labels = new HashMap<>(); //for quick lookup of label values
        PriorityQueue<OrderedVertex<V>> heap = new PriorityQueue<>();

        //store our vertex labels
        heap.add(new OrderedVertex<>(source, 0.0));
        labels.put(source, 0.0);
        results.put(source, null);

        for (V vertex : adjacencyLists.keySet())
        {
            if (!vertex.equals(source))
            {
                heap.add(new OrderedVertex<>(vertex, -1.0)); //-1 == infinity
                labels.put(vertex, -1.0);
            }
        }

        //calculate the shortest paths
        while (!heap.isEmpty())
        {
            //get the next smallest label
            OrderedVertex<V> label = heap.poll();
            V vertex = label.getVertex();
            double pathSoFar = label.getValue();

            //update adjacent vertex labels (if appropriate)
            Node<V> adjacent = adjacencyLists.get(vertex);
            while (adjacent != null)
            {
                double candidate = pathSoFar + adjacent.weight;
                double otherLabel = labels.get(adjacent.vertex);

                //should I update this label?
                if (otherLabel == -1 || candidate < otherLabel) //if infinity or a better path
                {
                    //update the heap
                    heap.remove(new OrderedVertex<>(adjacent.vertex, otherLabel));
                    heap.add(new OrderedVertex<>(adjacent.vertex, candidate));

                    //update our housekeeping variables
                    labels.put(adjacent.vertex, candidate);
                    results.put(adjacent.vertex, vertex);
                }

                adjacent = adjacent.next;
            }
        }

        return results;
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
        private Node<V> next;
        private double weight;

        public Node(V vertex)
        {
            this(vertex, null, 0.0);
        }

        public Node(V vertex, double weight)
        {
            this(vertex, null, weight);
        }

        public Node(V vertex, Node<V> next, double weight)
        {
            this.vertex = vertex;
            this.next = next;
            this.weight = weight;
        }
    }
}
