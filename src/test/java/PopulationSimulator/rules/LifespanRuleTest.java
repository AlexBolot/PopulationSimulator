package PopulationSimulator.rules;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.stream.IntStream;

import static PopulationSimulator.utils.Const.randBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LifespanRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 02:49
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LifespanRuleTest
{
    private int                   lifespan;
    private LifespanRule          lifespanRule;
    private LinkedHashSet<Person> people;

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
        people = new LinkedHashSet<>();
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
            people = new LinkedHashSet<Person>()
            {{
                IntStream.range(0, randBetween(5, 20)).forEach(j -> add(randPerson(lifespan, true)));
                IntStream.range(0, randBetween(5, 20)).forEach(k -> add(randPerson(lifespan, false)));
            }};

            youngAmount = people.stream().filter(person1 -> person1.data().age() < lifespan).count();
            oldAmount = people.stream().filter(person -> person.data().age() >= lifespan).count();

            assertEquals(youngAmount + oldAmount, people.size());

            lifespanRule.apply(new Population(people));

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

        lifespanRule.apply(new Population(people));

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

    /**
     <hr>
     <h2>Generates a random Person with age under/over the limit, depending on the young param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>

     @param limit Age limit of a Person's lifespan
     @param young True if the generated age has to be under the limit
     @return A random Person with age under/over the limit, depending on the young param
     */
    @NotNull
    private Person randPerson (int limit, boolean young)
    {
        int age = young ? -randBetween(0, limit) : -randBetween(limit, limit + 10);

        Gender gender = Gender.values()[Gender.values().length - 1];
        SexualOrientation orientation = SexualOrientation.values()[SexualOrientation.values().length - 1];

        return new Person(new PersonalData(age, gender, orientation));
    }
}