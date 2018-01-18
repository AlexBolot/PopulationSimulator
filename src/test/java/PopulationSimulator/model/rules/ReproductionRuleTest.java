package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.utils.Const;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;

import static PopulationSimulator.model.factories.PersonFactory.createAllCombinations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("FieldCanBeLocal")
public class ReproductionRuleTest
{
    private int                     minimumAge;
    private ReproductionRule        reproductionRule;
    private LinkedHashSet<Person>   people;
    private LinkedHashSet<Relation> relations;
    private Context                 context;

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
        people = new LinkedHashSet<>();
        relations = new LinkedHashSet<>();
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
        people = new LinkedHashSet<>(createAllCombinations(minimumAge));

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