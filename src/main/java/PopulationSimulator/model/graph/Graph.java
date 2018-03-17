package PopulationSimulator.model.graph;

import CodingUtils.ArrayList8;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Graph class was coded by : Alexandre BOLOT
 .
 . Last modified : 17/03/18 02:11
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Graph
{
    private ArrayList8<Node> nodes;
    private ArrayList8<Edge> edges;

    public Graph () { this(new ArrayList8<>(), new ArrayList8<>()); }

    public Graph (Node... nodes) { this(Arrays.asList(nodes), new ArrayList8<>()); }

    public Graph (Collection<Node> nodes, Collection<Edge> edges)
    {
        this.nodes = new ArrayList8<>(nodes);
        this.edges = new ArrayList8<>(edges);
    }

    public ArrayList8<Node> nodes () { return nodes; }

    public ArrayList8<Edge> edges () { return edges; }

    public void addNode (Node node) { nodes.addIf(node, n -> !nodes.contains(n)); }

    public void addLink (Node from, Node towards, EdgeType type) { edges.addIf(new Edge(from, towards, type), e -> !edges.contains(e)); }

    public void addLinkBoth (Node node1, Node node2, EdgeType type)
    {
        addLink(node1, node2, type);
        addLink(node2, node1, type);
    }

    public void remove (Node from, Node towards)
    {
        getEdge(from, towards).ifPresent(this::remove);
    }

    public void remove (Edge edge)
    {
        edges.remove(edge);
    }

    public void remove (Node node)
    {
        nodes.remove(node);
        edges.removeAll(getEdgesFrom(node));
        edges.removeAll(getEdgesTowards(node));
    }

    public boolean hasEdge (Node from, Node towards) { return getEdge(from, towards).isPresent(); }

    public boolean hasEdge (Node from, Node towards, EdgeType edgeType)
    {
        Optional<Edge> edge = getEdge(from, towards);

        return edge.isPresent() && edge.get().type() == edgeType;
    }

    public ArrayList8<Edge> getEdgesFrom (Node from) { return edges.subList(edge -> edge.isFrom(from)); }

    public ArrayList8<Edge> getEdgesTowards (Node towards) { return edges.subList(edge -> edge.isTowards(towards)); }

    public Optional<Edge> getEdge (Node from, Node towards) { return edges.findAny(edge -> edge.isFrom(from) && edge.isTowards(towards)); }

    public ArrayList8<Node> getNeighboorNodes (Node origin, EdgeType type)
    {
        return getNeighboorNodes(origin, type, Integer.MAX_VALUE);
    }

    public ArrayList8<Node> getNeighboorNodes (Node origin, EdgeType type, int range)
    {
        ArrayList8<Node> neighboorNodes = getNeighboorNodes(origin, type, range, new ArrayList8<>(singletonList(origin)));

        neighboorNodes.remove(origin);

        return neighboorNodes;
    }

    private ArrayList8<Node> getNeighboorNodes (Node origin, EdgeType type, int range, ArrayList8<Node> visited)
    {
        if (range == 0) return new ArrayList8<>();

        ArrayList8<Edge> newEdges = getEdgesFrom(origin).subList(edge -> edge.type().isSame(type));

        ArrayList8<Node> newNodes = newEdges.mapAndCollect(Edge::towards).subList(node -> !visited.contains(node));

        visited.merge(newNodes);

        newNodes.forEach(node -> getNeighboorNodes(node, type, range - 1, visited));

        return visited;
    }
}
