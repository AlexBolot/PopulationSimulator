package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.RelationType.Couple;
import static PopulationSimulator.model.factories.PersonFactory.*;
import static PopulationSimulator.utils.Const.randBetween;
import static PopulationSimulator.utils.Const.randDelta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PartenerFinderTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 18/01/18 22:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class PartenerFinderTest
{
    private Person                person1;
    private Person                person2;
    private Context               context;
    private LinkedHashSet<Person> people;

    @Before
    public void before ()
    {
        people = new LinkedHashSet<>();
        context = new Context(people);
        person1 = null;
        person2 = null;
    }

    private void specialSetUp ()
    {
        int minimumAge = randDelta(25, 5);

        ArrayList<Person> couple = new ArrayList<>(createCouple(Female, Male));

        person1 = couple.get(0);
        person2 = couple.get(1);

        people.addAll(couple);

        context.relations().add(new Relation(person1, person2, Couple));

        int bound = randBetween(20, 40);
        for (int j = 0; j < bound; j++)
        {
            people.add(createOlder(minimumAge, minimumAge * 2));
        }
    }

    @Test
    public void find_Right ()
    {
        specialSetUp();

        assertEquals(person2, new ArrayList<>(new PartenerFinder().find(person1, context)).get(0));
        assertEquals(person1, new ArrayList<>(new PartenerFinder().find(person2, context)).get(0));
    }

    @Test
    public void find_Empty ()
    {
        LinkedHashSet<Person> hashSet = new PartenerFinder().find(createPerson(), context);

        assertTrue(hashSet.isEmpty());
    }

    @Test (expected = NullPointerException.class)
    public void find_Null_1stParam ()
    {
        Person person = null;

        new PartenerFinder().find(person, context);
    }

    @Test (expected = NullPointerException.class)
    public void find_Null_2ndParam ()
    {
        Context context = null;

        new PartenerFinder().find(createPerson(), context);
    }

    @Test (expected = NullPointerException.class)
    public void find_Null_BothParam ()
    {
        Person person = null;
        Context context = null;

        new PartenerFinder().find(person, context);
    }

    @Test
    public void merge_Right ()
    {
        specialSetUp();

        LinkedHashSet<Person> hashSet = new LinkedHashSet<Person>()
        {{
            add(createPerson());
            add(createPerson());
            add(createPerson());
            add(createPerson());
        }};

        assertEquals(4, hashSet.size());

        PartenerFinder finder = new PartenerFinder();

        finder.find(person1, context);

        LinkedHashSet<Person> merge = finder.merge(hashSet);

        assertEquals(5, merge.size());
    }

    @Test
    public void merge_Empty ()
    {
        specialSetUp();

        LinkedHashSet<Person> hashSet = new LinkedHashSet<>();

        assertEquals(0, hashSet.size());

        PartenerFinder finder = new PartenerFinder();

        finder.find(person1, context);

        LinkedHashSet<Person> merge = finder.merge(hashSet);

        assertEquals(1, merge.size());
    }

    @Test (expected = NullPointerException.class)
    public void merge_Null ()
    {
        LinkedHashSet<Person> hashSet = null;

        new PartenerFinder().merge(hashSet);
    }
}