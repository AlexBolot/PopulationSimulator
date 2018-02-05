package PopulationSimulator.visualizer;

import PopulationSimulator.entities.Context;
import PopulationSimulator.model.rules.CoupleRule;
import PopulationSimulator.visualizer.cli.CLI;
import PopulationSimulator.visualizer.cli.Visualizer;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static PopulationSimulator.model.factories.PersonFactory.createPerson;
import static PopulationSimulator.utils.Const.randDelta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The VisualizerTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 05/02/18 16:45
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class VisualizerTest
{
    private CLI        cli;
    private Context    context;
    private Visualizer visualizer;

    @Before
    public void setUp ()
    {
        cli = new CLI();
        visualizer = new Visualizer(cli.userArgs());
        setUpContext();
    }

    private void setUpContext ()
    {
        context = new Context();

        IntStream.range(0, randDelta(15, 5)).forEach(i -> context.people().add(createPerson()));

        new CoupleRule().apply(context);
    }

    private void setUpCli ()
    {
        cli.start();
        visualizer = new Visualizer(cli.userArgs());
    }

    @Test
    public void addTurn_Right ()
    {
        assertTrue(visualizer.peopleStats().isEmpty());
        assertTrue(visualizer.relationsStats().isEmpty());

        visualizer.addTurn(context);

        assertEquals(context.people(), visualizer.peopleStats().get(0));
        assertEquals(context.relations(), visualizer.relationsStats().get(0));

        setUpContext();

        visualizer.addTurn(context);

        assertEquals(context.people(), visualizer.peopleStats().get(1));
        assertEquals(context.relations(), visualizer.relationsStats().get(1));
    }

    @Test
    public void addTurn_Empty ()
    {
        assertTrue(visualizer.peopleStats().isEmpty());
        assertTrue(visualizer.peopleStats().isEmpty());

        visualizer.addTurn(new Context());

        assertTrue(visualizer.relationsStats().isEmpty());
        assertTrue(visualizer.relationsStats().isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addTurn_Null ()
    {
        Context context = null;
        visualizer.addTurn(context);
    }

    @Test
    public void printStats_Right ()
    {
        //TODO find a way to test that...
    }
}