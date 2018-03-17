package PopulationSimulator;

import CodingUtils.ArrayList8;
import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.model.Sector;
import PopulationSimulator.model.factories.PersonFactory;
import PopulationSimulator.model.rules.Applyable;
import PopulationSimulator.model.rules.CoupleRule;
import PopulationSimulator.model.rules.LifespanRule;
import PopulationSimulator.model.rules.ReproductionRule;
import PopulationSimulator.visualizer.Logger;
import PopulationSimulator.visualizer.cli.CLI;
import PopulationSimulator.visualizer.cli.Visualizer;

import java.util.LinkedHashMap;
import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.randBetween;
import static PopulationSimulator.visualizer.Logger.LogFile.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:32
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>This class is the main access to the Simulator.<br>
 Nothing more to say :)</h2>
 */
public class App
{
    public static void main (String[] args)
    {
        Logger.clearLogs(PeopeLogFile, RelationsLogFile, SectorsLogFile);

        ArrayList8<Person> people = new ArrayList8<>();
        IntStream.range(0, 10).forEach(i -> people.add(PersonFactory.createPerson()));

        ArrayList8<Applyable> rules = new ArrayList8<>();
        rules.add(new CoupleRule(5));
        rules.add(new ReproductionRule(7));
        rules.add(new LifespanRule(15));

        ArrayList8<Sector> sectors = new ArrayList8<Sector>();
        sectors.add(new Sector(1, 3)); // 0
        sectors.add(new Sector(0, 2)); // 1
        sectors.add(new Sector(1, 3)); // 2
        sectors.add(new Sector(0, 2)); // 3

        LinkedHashMap<Person, Sector> locations = new LinkedHashMap<>();

        people.forEach(person -> locations.put(person, sectors.get(randBetween(0, sectors.size()))));

        Context context = new Context(people, new ArrayList8<>(), sectors, locations);

        CLI cli = new CLI();
        cli.start(args);

        Visualizer visualizer = new Visualizer(cli.userArgs());

        new SimulationController(rules, context, visualizer).simulate(25);
    }
}
