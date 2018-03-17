package PopulationSimulator.visualizer.stats;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static PopulationSimulator.model.factories.PersonFactory.createPerson;
import static PopulationSimulator.utils.Const.randDelta;
import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PeopleStatsTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:37
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class PeopleStatsTest
{
    //region --------------- Attributes ---------------------

    private final double delta = 0.0001;
    private PeopleStats peopleStats;

    //endregion

    //region --------------- SetUps -------------------------

    @Before
    public void setUp ()
    {
        peopleStats = new PeopleStats();
        IntStream.range(0, randDelta(5, 2)).forEach(i -> peopleStats.add(createPeoplePool()));
    }

    @NotNull
    private ArrayList8<Person> createPeoplePool ()
    {
        int amount = randDelta(15, 5);

        return IntStream.range(0, amount).mapToObj(i -> createPerson(-randDelta(20, 5))).collect(Collectors.toCollection(ArrayList8::new));
    }

    //endregion

    //region --------------- Adder --------------------------

    @Test
    public void add ()
    {
        for (int i = 0; i < 1000; i++)
        {
            int initListSize = peopleStats.size();
            int initAllSize = peopleStats.getAll().size();

            ArrayList8<Person> pool = createPeoplePool();

            peopleStats.add(pool);

            int newListSize = initListSize + 1;
            int newAllSize = initAllSize + pool.size();

            assertEquals(newListSize, peopleStats.size());
            assertEquals(newAllSize, peopleStats.getAll().size());
        }
    }

    //endregion

    //region --------------- Global accessors ---------------

    @Test
    public void getAll ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int count = peopleStats.stream().mapToInt(ArrayList::size).sum();

            assertEquals(count, peopleStats.getAll().size());
        }
    }

    @Test
    public void countByTurn ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Integer> counts = peopleStats.stream().map(ArrayList::size).collect(Collectors.toCollection(ArrayList8::new));

            assertEquals(counts, peopleStats.countByTurn());
        }
    }

    @Test
    public void averageAmount ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            double average = peopleStats.stream().mapToInt(ArrayList8::size).average().getAsDouble();

            assertEquals(average, peopleStats.averageAmount(), delta);
        }
    }

    //endregion

    //region --------------- Count --------------------------

    @Test
    public void countByAge ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int peopleSize = peopleStats.getByAge(age).size();

            assertEquals(peopleSize, peopleStats.countByAge(age));
        }
    }

    @Test
    public void countOlderThan ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int peopleSize = peopleStats.getByAge(age).size();

            assertEquals(peopleSize, peopleStats.countByAge(age));
        }
    }

    @Test
    public void countYoungerThan ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int peopleSize = peopleStats.getByAge(age).size();

            assertEquals(peopleSize, peopleStats.countByAge(age));
        }
    }

    @Test
    public void countByGender ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            Gender gender = Gender.getRandom();

            int peopleSize = peopleStats.getByGender(gender).size();

            assertEquals(peopleSize, peopleStats.countByGender(gender));
        }
    }

    @Test
    public void countByOrientation ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            SexualOrientation orientation = SexualOrientation.getRandom();

            int peopleSize = peopleStats.getByOrientation(orientation).size();

            assertEquals(peopleSize, peopleStats.countByOrientation(orientation));
        }
    }

    //endregion

    //region --------------- Get by -------------------------

    @Test
    public void getByAge ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Person> people = peopleStats.getAll().subList(p -> p.data().age() == age);

            assertEquals(people, peopleStats.getByAge(age));
        }
    }

    @Test
    public void getOlderThan ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Person> people = peopleStats.getAll().subList(p -> p.data().age() > age);

            assertEquals(people, peopleStats.getOlderThan(age));
        }
    }

    @Test
    public void getYoungerThan ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Person> people = peopleStats.getAll().subList(p -> p.data().age() < age);

            assertEquals(people, peopleStats.getYoungerThan(age));
        }
    }

    @Test
    public void getByGender ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            Gender gender = Gender.getRandom();

            ArrayList8<Person> pool = peopleStats.getAll().subList(p -> p.data().gender() == gender);

            assertEquals(pool, peopleStats.getByGender(gender));
        }
    }

    @Test
    public void getByOrientation ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            SexualOrientation orientation = SexualOrientation.getRandom();

            ArrayList8<Person> people = peopleStats.getAll().subList(p -> p.data().orientation() == orientation);
            assertEquals(people, peopleStats.getByOrientation(orientation));
        }
    }

    //endregion
}