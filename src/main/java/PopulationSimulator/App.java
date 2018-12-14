package PopulationSimulator;

import CodingUtils.ArrayList8;
import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.model.Sector;
import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.enums.EdgeType;
import PopulationSimulator.model.enums.Gender;
import PopulationSimulator.model.factories.PersonFactory;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import PopulationSimulator.model.rules.Applyable;
import PopulationSimulator.model.rules.CoupleRule;
import PopulationSimulator.model.rules.LifespanRule;
import PopulationSimulator.model.rules.ReproductionRule;

import java.io.IOException;
import java.util.LinkedHashMap;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/12/18 13:17
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
        //IntStream.range(0, 10).forEach(i -> people.add(PersonFactory.createPerson()));

        people.addAll(PersonFactory.createCouple(Gender.Male, Gender.Female));
        people.addAll(PersonFactory.createCouple(Gender.Male, Gender.Female));
        people.addAll(PersonFactory.createCouple(Gender.Male, Gender.Female));
        people.addAll(PersonFactory.createCouple(Gender.Male, Gender.Female));
        people.addAll(PersonFactory.createCouple(Gender.Male, Gender.Female));

        ArrayList8<Applyable> rules = new ArrayList8<>();
        rules.add(new CoupleRule(5));
        rules.add(new ReproductionRule(7));
        rules.add(new LifespanRule(60, 3));

        ArrayList8<Sector> sectors = new ArrayList8<>();
        sectors.add(new Sector(1, 3)); // 0
        sectors.add(new Sector(0, 2)); // 1
        sectors.add(new Sector(1, 3)); // 2
        sectors.add(new Sector(0, 2)); // 3

        LinkedHashMap<Person, Sector> locations = new LinkedHashMap<>();

        people.forEach(person -> locations.put(person, sectors.get(randBetween(0, sectors.size()))));

        Graph graph = new Graph(people.mapAndCollect(Node::new));
        new SimulationController(rules, graph).simulate(50, EdgeType.Parent);
    }
}
