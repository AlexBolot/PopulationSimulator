package PopulationSimulator;

import CodingUtils.ArrayList8;
import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.entities.Sector;
import PopulationSimulator.model.enums.EdgeType;
import PopulationSimulator.model.factories.PersonFactory;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import PopulationSimulator.model.rules.*;

import java.io.IOException;
import java.util.stream.IntStream;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/12/2018 16:03
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 * <hr>
 * <h2>This class is the main access to the Simulator.<br>
 * Nothing more to say :)</h2>
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList8<Person> people = new ArrayList8<>();
        IntStream.range(0, 50).forEach(i -> people.add(PersonFactory.createPerson()));

        ArrayList8<Applyable> rules = new ArrayList8<Applyable>() {{
            add(new CoupleRule(0));
            add(new ReproductionRule(2));
            add(new LifespanRule(60, 3));
            add(new MeetingRule());
        }};
        ArrayList8<Sector> sectors = new ArrayList8<Sector>() {{
            add(new Sector());
            add(new Sector());
            add(new Sector());
            add(new Sector());
        }};

        ArrayList8<Node> peopleNodes = people.mapAndCollect(Node::new);
        ArrayList8<Node> sectorNodes = sectors.mapAndCollect(Node::new);

        Graph graph = new Graph(peopleNodes.merge(sectorNodes));
        new SimulationController(rules, graph).simulate(50, EdgeType.Parent);
    }
}
