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
 . Last modified : 19/01/18 21:49
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
    //endregion

    //region --------------- apply (x3) ----------------------

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
        people.addAll(createAllCombinations(minimumAge));

        new CoupleRule(minimumAge).apply(context);

        assertEquals(24, people.size());

        reproductionRule.apply(context);

        assertEquals(30, people.size());
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
        Context context = null;

        reproductionRule.apply(context);
    }
    //endregion
}