package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.utils.ArrayList8;
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
 . Last modified : 18/01/18 23:02
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("FieldCanBeLocal")
public class ReproductionRuleTest
{
    private int                  minimumAge;
    private ReproductionRule     reproductionRule;
    private ArrayList8<Person>   people;
    private ArrayList8<Relation> relations;
    private Context              context;

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>
     */
    @Before
    public void before ()
    {
        people = new ArrayList8<>();
        relations = new ArrayList8<>();
        minimumAge = Const.randBetween(16, 18);
        reproductionRule = new ReproductionRule(minimumAge);
        context = new Context(people, relations);
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>
     */
    @Test
    public void apply_Right ()
    {
        people = new ArrayList8<>(createAllCombinations(minimumAge));

        new CoupleRule(minimumAge).apply(context);

        assertEquals(people.size(), 30);

        reproductionRule.apply(context);

        assertEquals(people.size(), 30);
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>
     */
    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        reproductionRule.apply(context);

        assertTrue(people.isEmpty());
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>
     */
    @Test (expected = NullPointerException.class)
    public void apply_Null ()
    {
        reproductionRule.apply(null);
    }
}