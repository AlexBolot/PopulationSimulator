package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.graph.Edge;
import PopulationSimulator.model.graph.Graph;
import PopulationSimulator.model.graph.Node;
import PopulationSimulator.utils.Const;
import org.junit.Before;
import org.junit.Test;

import static PopulationSimulator.model.factories.PersonFactory.createAllCombinations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 23/03/18 18:20
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("FieldCanBeLocal")
public class ReproductionRuleTest
{
    //region --------------- Attributes ----------------------
    private int                minimumAge;
    private Graph              context;
    private ReproductionRule   reproductionRule;
    private ArrayList8<Person> people;
    private ArrayList8<Edge>   edges;
    //endregion

    //region --------------- SetUps --------------------------

    @Before
    public void before ()
    {
        people = new ArrayList8<>();
        edges = new ArrayList8<>();
        minimumAge = Const.randBetween(16, 18);
        reproductionRule = new ReproductionRule(minimumAge);
        context = new Graph(people.mapAndCollect(Node::new), edges);
    }
    //endregion

    //region --------------- apply (x3) ----------------------

    @Test
    public void apply_Right ()
    {
        people.addAll(createAllCombinations(minimumAge));

        context = new Graph(people.mapAndCollect(Node::new), edges);

        int sizeBefore = context.merge(new CoupleRule(minimumAge).apply(context)).nodes().size();

        assertEquals(24, sizeBefore);

        int sizeAfter = context.merge(reproductionRule.apply(context)).nodes().size();

        assertEquals(30, sizeAfter);
    }

    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        int sizeBefore = context.merge(new CoupleRule(minimumAge).apply(context)).nodes().size();

        assertEquals(0, sizeBefore);

        int sizeAfter = context.merge(reproductionRule.apply(context)).nodes().size();

        assertEquals(0, sizeAfter);
    }

    @Test (expected = IllegalArgumentException.class)
    public void apply_Null ()
    {
        Graph context = null;

        reproductionRule.apply(context);
    }
    //endregion
}