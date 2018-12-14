package PopulationSimulator.model.graph;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Node class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:36
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Node<T> {
    private T value;

    public Node(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && ((Node) obj).value().equals(this.value());
    }

    @Override
    public String toString() {
        return " Node<" + value.toString() + "> ";
    }
}
