package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.utils.ArrayList8;
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
 . Last modified : 18/01/18 23:00
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LifespanRuleTest
{
    private int                lifespan;
    private LifespanRule       lifespanRule;
    private ArrayList8<Person> people;

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
    @Before
    public void before ()
    {
        people = new ArrayList8<>();
        lifespan = randBetween(50, 100);
        lifespanRule = new LifespanRule(lifespan);
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
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

            youngAmount = people.stream().filter(person1 -> person1.data().age() < lifespan).count();
            oldAmount = people.stream().filter(person -> person.data().age() >= lifespan).count();

            assertEquals(youngAmount + oldAmount, people.size());

            lifespanRule.apply(new Context(people));

            assertEquals(youngAmount, people.size());
        }
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        lifespanRule.apply(new Context(people));

        assertTrue(people.isEmpty());
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     <hr>
     */
    @Test (expected = NullPointerException.class)
    public void apply_Null ()
    {
        lifespanRule.apply(null);
    }
}