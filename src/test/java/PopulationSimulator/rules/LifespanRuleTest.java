package PopulationSimulator.rules;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
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
 . Last modified : 11/01/18 22:22
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LifespanRuleTest
{
    private int                   lifespan;
    private LifespanRule          lifespanRule;
    private LinkedHashSet<Person> people;

    @Before
    public void before ()
    {
        people = new LinkedHashSet<>();
        lifespan = randBetween(50, 100);
        lifespanRule = new LifespanRule(lifespan);
    }

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

            youngAmount = people.stream().filter(person1 -> person1.data().getAge() < lifespan).count();
            oldAmount = people.stream().filter(person -> person.data().getAge() >= lifespan).count();

            assertEquals(youngAmount + oldAmount, people.size());

            lifespanRule.apply(new Population(people));

            assertEquals(youngAmount, people.size());
        }
    }

    @Test
    public void apply_Empty ()
    {
        assertTrue(people.isEmpty());

        lifespanRule.apply(new Population(people));

        assertTrue(people.isEmpty());
    }

    @Test (expected = NullPointerException.class)
    public void apply_Null ()
    {
        lifespanRule.apply(null);
    }

    private Person randPerson (int limit, boolean young)
    {
        int ID = randBetween(0, 2000);
        int age = young ? -randBetween(0, limit) : -randBetween(limit, limit + 10);

        Gender gender = Gender.values()[Gender.values().length - 1];
        SexualOrientation orientation = SexualOrientation.values()[SexualOrientation.values().length - 1];

        return new Person(new PersonalData(String.valueOf(ID), age, gender, orientation));
    }
}