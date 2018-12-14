package PopulationSimulator.visualizer;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.enums.EdgeType;
import PopulationSimulator.model.graph.Edge;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Runtime.getRuntime;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DotGenerator class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 13:17
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class DotGenerator {

    private static final Path pathToGraphs = Paths.get("src", "main", "resources", "graphs");

    private String content = "";

    public DotGenerator() throws IOException {
        getRuntime().exec("find " + pathToGraphs.toString() + " -mindepth 1 -delete\n");
    }

    public void generate(String postFix, boolean autoOpen) throws IOException, InterruptedException {

        String dotFilePath = pathToGraphs.toString();
        String graphName = "graph" + postFix;

        Files.write(Paths.get(dotFilePath, graphName + ".dot"), content.getBytes());

        Process process = getRuntime().exec("dot -Tpng " + dotFilePath + "/" + graphName + ".dot -o " + dotFilePath + "/_" + graphName + ".png");

        process.waitFor();

        if (autoOpen)
            getRuntime().exec("open " + dotFilePath + "/_" + graphName + ".png");
        else getRuntime().exec("open " + dotFilePath);
    }

    public void produceFrom(Graph graph, EdgeType type) {

        StringBuilder builder = new StringBuilder("digraph PopSimulator {\n\n");

        //builder.append("rankdir=LR;\n");

        //builder.append("graph [splines=ortho]; \n");

        ArrayList8<Node> nodes = graph.nodes();

        for (Node n : nodes) {

            for (Edge edge : graph.getEdgesOfType(n, type)) {

                String nName = n.value().toString().replace("-", "");
                String neighboorName = edge.towards().value().toString().replace("-", "");

                builder.append("\t").append(nName).append(" -> ").append(neighboorName);
                builder.append(" [label = \"").append(edge.type().name()).append("\"]\n");
            }
        }

        builder.append("\n}");

        content = builder.toString();
    }
}
