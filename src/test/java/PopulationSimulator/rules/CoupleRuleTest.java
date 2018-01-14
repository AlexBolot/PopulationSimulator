package PopulationSimulator.rules;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import PopulationSimulator.utils.Const;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.stream.IntStream;

import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.utils.Const.randBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The CoupleRuleTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 02:49
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class CoupleRuleTest
{
    private int                   minimumAge;
    private CoupleRule            coupleRule;
    private LinkedHashSet<Person> people;

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
        minimumAge = Const.randBetween(18, 25);
        coupleRule = new CoupleRule(minimumAge);
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
        int tooYoung = minimumAge - randBetween(1, 5);
        int oldEnough = minimumAge + randBetween(1, 5);

        people = new LinkedHashSet<Person>()
        {{
            IntStream.range(0, 10).forEach(j -> {
                //Will generate 20 couples
                add(createPerson(oldEnough, Male, Bi));
                add(createPerson(oldEnough, Male, Homo));
                add(createPerson(oldEnough, Male, Hetero));
                add(createPerson(oldEnough, Female, Bi));
                add(createPerson(oldEnough, Female, Homo));
                add(createPerson(oldEnough, Female, Hetero));

                //Will generate 0 couples
                add(createPerson(tooYoung, Male, Bi));
                add(createPerson(tooYoung, Male, Homo));
                add(createPerson(tooYoung, Male, Hetero));
                add(createPerson(tooYoung, Female, Bi));
                add(createPerson(tooYoung, Female, Homo));
                add(createPerson(tooYoung, Female, Hetero));
            });
        }};

        Population population = new Population(people);

        coupleRule.apply(population);

        assertEquals(29, population.relations().size());
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

        Population population = new Population(people);

        coupleRule.apply(population);

        assertTrue(population.relations().isEmpty());
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
        coupleRule.apply(null);
    }

    /**
     <hr>
     <h2>Generates a Person created from given params : age, gender and orientation</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param age         Age of the Person
     @param gender      Gender of the Person
     @param orientation SexualOrientation of the Perso
     @return A Person created from given params : age, gender and orientation
     */
    @NotNull
    private Person createPerson (int age, Gender gender, SexualOrientation orientation)
    {
        return new Person(new PersonalData(-age, gender, orientation));
    }
}