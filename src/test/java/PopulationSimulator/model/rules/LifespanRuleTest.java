package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Person;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static PopulationSimulator.model.rules.RulesTestingUtils.randPerson;
import static PopulationSimulator.utils.Const.randBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LifespanRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 21/03/18 07:16
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LifespanRuleTest
{
    //region --------------- Attributes ----------------------
    private int                lifespan;
    private LifespanRule       lifespanRule;
    private ArrayList8<Person> people;
    //endregion

    //region --------------- SetUps --------------------------

    @Before
    public void before ()
    {
        people = new ArrayList8<>();
        lifespan = randBetween(50, 100);
        lifespanRule = new LifespanRule(lifespan);
    }
    //endregion

    //region --------------- apply (x3) ----------------------

    @Test
    public void apply_Right ()
    {
        long youngAmount;
        long oldAmount;

        for (int i = 0; i < 1000; i++)
        {
            people = new ArrayList8<Person>()
            {{
                IntStream.range(0, randBetween(5, 20)).forEach(j -> add(randPerson(lifespan, true)));
                IntStream.range(0, randBetween(5, 20)).forEach(k -> add(randPerson(lifespan, false)));
            }};

            youngAmount = people.subList(person1 -> person1.data().age() < lifespan).size();
            oldAmount = people.subList(person -> person.data().age() >= lifespan).size();
            Graph graph = new Graph(people.mapAndCollect(Node::new));

            assertEquals(youngAmount + oldAmount, graph.nodes().size());

            lifespanRule.apply(graph);

            assertEquals(youngAmount, graph.nodes().size());
        }
    }

    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        lifespanRule.apply(new Graph(people.mapAndCollect(Node::new)));

        assertTrue(people.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void apply_Null ()
    {
        Graph context = null;

        lifespanRule.apply(context);
    }
    //endregion
}