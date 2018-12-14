package PopulationSimulator.model.graph;

import PopulationSimulator.model.enums.EdgeType;

import static PopulationSimulator.controllers.SimulationController.currentTime;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Edge class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 07:36
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Edge {
    private int start;
    private Node from;
    private Node towards;
    private EdgeType type;

    public Edge(Node from, Node towards, EdgeType type) {
        this.from = from;
        this.type = type;
        this.towards = towards;
        start = currentTime();
    }

    public Node from() {
        return from;
    }

    public Node towards() {
        return towards;
    }

    public EdgeType type() {
        return type;
    }

    public int duration() {
        return currentTime() - start;
    }

    public boolean isFrom(Node node) {
        return from.equals(node);
    }

    public boolean isTowards(Node node) {
        return towards.equals(node);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(this.getClass())) return false;

        Edge toTest = (Edge) o;

        if (toTest.duration() != this.duration()) return false;

        return toTest.from().equals(this.from()) && toTest.towards().equals(this.towards());
    }

    @Override
    public String toString() {
        return from + " -- " + type.name() + " -> " + towards;
    }
}