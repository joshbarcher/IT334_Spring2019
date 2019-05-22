package graphs;

import java.util.Objects;

public class OrderedVertex<V> implements Comparable<OrderedVertex<V>>
{
    private V vertex;
    private double value; //(label value) (-1 means infinity)

    public OrderedVertex(V vertex, double value)
    {
        this.vertex = vertex;
        this.value = value;
    }

    public V getVertex()
    {
        return vertex;
    }

    public double getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedVertex<?> that = (OrderedVertex<?>) o;
        return Objects.equals(vertex, that.vertex);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(vertex);
    }

    @Override
    public String toString()
    {
        return vertex + ", " + value;
    }

    @Override
    public int compareTo(OrderedVertex<V> other)
    {
        //handle any infinity values
        if (value == -1 && other.value == -1)
        {
            return 0;
        }
        else if (value == -1)
        {
            return 1; //value is larger, because it is infinity
        }
        else if (other.value == -1)
        {
            return -1; //other.value is larger, because other.value is infinity
        }

        //handle the basic comparison otherwise
        if (value < other.value)
        {
            return -1;
        }
        else if (value > other.value)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
