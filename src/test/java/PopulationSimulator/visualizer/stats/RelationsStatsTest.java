package PopulationSimulator.visualizer.stats;

import CodingUtils.ArrayList8;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.RelationType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static PopulationSimulator.model.factories.PersonFactory.createPerson;
import static PopulationSimulator.utils.Const.randBetween;
import static PopulationSimulator.utils.Const.randDelta;
import static org.junit.Assert.assertEquals;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationsStatsTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/03/18 09:37
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class RelationsStatsTest
{
    //region --------------- Attributes ---------------------

    private final double delta = 0.0001;
    private RelationsStats relationsStats;

    //endregion

    //region --------------- SetUps -------------------------

    @Before
    public void setUp ()
    {
        relationsStats = new RelationsStats();

        IntStream.range(0, randDelta(5, 2)).forEach(i -> relationsStats.add(createRelationsPool()));
    }

    private ArrayList8<Relation> createRelationsPool ()
    {
        ArrayList8<Relation> relations = new ArrayList8<>();

        for (int j = 0; j < randDelta(15, 5); j++)
        {
            Person person1 = createPerson(-randDelta(20, 5));
            Person person2 = createPerson(-randDelta(20, 5));

            relations.add(new Relation(person1, person2, RelationType.getRandom()));
        }

        return relations;
    }

    //endregion

    //region --------------- Adder --------------------------

    @Test
    public void add ()
    {
        for (int i = 0; i < 1000; i++)
        {
            int initListSize = relationsStats.size();
            int initAllSize = relationsStats.getAll().size();

            ArrayList8<Relation> pool = createRelationsPool();

            relationsStats.add(pool);

            int newListSize = initListSize + 1;
            int newAllSize = initAllSize + pool.size();

            assertEquals(newListSize, relationsStats.size());
            assertEquals(newAllSize, relationsStats.getAll().size());
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

            int count = relationsStats.stream().mapToInt(ArrayList::size).sum();

            assertEquals(count, relationsStats.getAll().size());
        }
    }

    @Test
    public void countByTurn ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Integer> counts = relationsStats.stream().map(ArrayList::size).collect(Collectors.toCollection(ArrayList8::new));

            assertEquals(counts, relationsStats.countByTurn());
        }
    }

    @Test
    public void averageAmount ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            double average = relationsStats.stream().mapToInt(ArrayList8::size).average().getAsDouble();

            assertEquals(average, relationsStats.averageAmount(), delta);
        }
    }

    @Test
    public void getLongest ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int length = relationsStats.getAll().map(Relation::getDuration).max(Integer::compareTo).get();

            assertEquals(length, relationsStats.getLongest());
        }
    }

    @Test
    public void getShortest ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int length = relationsStats.getAll().map(Relation::getDuration).min(Integer::compareTo).get();

            assertEquals(length, relationsStats.getShortest());
        }
    }

    @Test
    public void getBiggestDelta ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int delta = relationsStats.getAll().map(Relation::getDeltaAge).max(Integer::compareTo).get();

            assertEquals(delta, relationsStats.getBiggestDelta());
        }
    }

    @Test
    public void getAverageDelta ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int average = (int) relationsStats.getAll().stream().mapToInt(Relation::getDeltaAge).average().getAsDouble();

            assertEquals(average, relationsStats.getAverageDelta(), delta);
        }
    }

    @Test
    public void getAverageDuration ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            double average = relationsStats.getAll().stream().mapToInt(Relation::getDuration).average().getAsDouble();

            assertEquals(average, relationsStats.getAverageDuration(), delta);
        }
    }

    //endregion

    //region --------------- Count --------------------------

    @Test
    public void countByGender ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            Gender gender1 = Gender.getRandom();
            Gender gender2 = Gender.getRandom();

            int poolSize = relationsStats.getByGender(gender1, gender2).size();

            assertEquals(poolSize, relationsStats.countByGender(gender1, gender2));
        }
    }

    @Test
    public void countByAgeDelta ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int smallerPoolSize = relationsStats.getByAgeDelta(age, true).size();
            assertEquals(smallerPoolSize, relationsStats.countByAgeDelta(age, true));

            int biggerPoolSize = relationsStats.getByAgeDelta(age, false).size();
            assertEquals(biggerPoolSize, relationsStats.countByAgeDelta(age, false));
        }
    }

    @Test
    public void countByType ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            RelationType type = RelationType.getRandom();

            int poolSize = relationsStats.getByType(type).size();
            assertEquals(poolSize, relationsStats.countByType(type));
        }
    }

    @Test
    public void countByDuration ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int duration = randBetween(0, 5);

            int smallerPoolSize = relationsStats.getByDuration(duration, true).size();
            assertEquals(smallerPoolSize, relationsStats.countByDuration(duration, true));

            int biggerPoolSize = relationsStats.getByDuration(duration, false).size();
            assertEquals(biggerPoolSize, relationsStats.countByDuration(duration, false));
        }
    }

    //endregion

    //region --------------- Get by -------------------------

    @Test
    public void getByGender ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            Gender gender1 = Gender.getRandom();
            Gender gender2 = Gender.getRandom();

            ArrayList8<Relation> pool = relationsStats.getAll().subList(r -> {
                Gender g1 = r.person1().data().gender();
                Gender g2 = r.person2().data().gender();

                return ((g1 == gender1) && (g2 == gender2)) || ((g1 == gender2) || (g2 == gender1));
            });

            assertEquals(pool, relationsStats.getByGender(gender1, gender2));
        }
    }

    @Test
    public void getByAgeDelta ()
    {
        int age = 20;

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Relation> smallerPool = relationsStats.getAll().subList(r -> r.getDeltaAge() < age);
            assertEquals(smallerPool, relationsStats.getByAgeDelta(age, true));

            ArrayList8<Relation> biggerPool = relationsStats.getAll().subList(r -> r.getDeltaAge() > age);
            assertEquals(biggerPool, relationsStats.getByAgeDelta(age, false));
        }
    }

    @Test
    public void getByType ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            RelationType type = RelationType.getRandom();

            ArrayList8<Relation> pool = relationsStats.getAll().subList(r -> r.type() == type);
            assertEquals(pool, relationsStats.getByType(type));
        }
    }

    @Test
    public void getByDuration ()
    {
        int duration = randBetween(0, 5);

        for (int i = 0; i < 1000; i++)
        {
            setUp();

            ArrayList8<Relation> smallerPool = relationsStats.getAll().subList(r -> r.getDuration() < duration);
            assertEquals(smallerPool, relationsStats.getByDuration(duration, true));

            ArrayList8<Relation> biggerPool = relationsStats.getAll().subList(r -> r.getDuration() > duration);
            assertEquals(biggerPool, relationsStats.getByDuration(duration, false));
        }
    }

    //endregion
}