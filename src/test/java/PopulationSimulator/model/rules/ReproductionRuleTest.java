package PopulationSimulator.model.rules;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
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
 . Last modified : 16/03/18 09:37
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("FieldCanBeLocal")
public class ReproductionRuleTest
{
    //region --------------- Attributes ----------------------
    private int                  minimumAge;
    private Context              context;
    private ReproductionRule     reproductionRule;
    private ArrayList8<Person>   people;
    private ArrayList8<Relation> relations;
    //endregion

    //region --------------- SetUps --------------------------

    @Before
    public void before ()
    {
        people = new ArrayList8<>();
        relations = new ArrayList8<>();
        minimumAge = Const.randBetween(16, 18);
        reproductionRule = new ReproductionRule(minimumAge);
        context = new Context(people, relations);
    }
    //endregion

    //region --------------- apply (x3) ----------------------

    @Test
    public void apply_Right ()
    {
        people.addAll(createAllCombinations(minimumAge));

        int sizeBefore = context.merge(new CoupleRule(minimumAge).apply(context)).people().size();

        assertEquals(24, sizeBefore);

        int sizeAfter = context.merge(reproductionRule.apply(context)).people().size();

        assertEquals(30, sizeAfter);
    }

    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        int sizeBefore = context.merge(new CoupleRule(minimumAge).apply(context)).people().size();

        assertEquals(0, sizeBefore);

        int sizeAfter = context.merge(reproductionRule.apply(context)).people().size();

        assertEquals(0, sizeAfter);
    }

    @Test (expected = IllegalArgumentException.class)
    public void apply_Null ()
    {
        Context context = null;

        reproductionRule.apply(context);
    }
    //endregion
}