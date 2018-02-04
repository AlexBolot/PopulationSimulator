package PopulationSimulator.model.finders;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.utils.ArrayList8;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
 . Last modified : 01/02/18 00:56
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class PartenerFinderTest
{
    //region --------------- Attributes ----------------------
    private Person             person1;
    private Person             person2;
    private Context            context;
    private ArrayList8<Person> people;
    //endregion

    //region --------------- SetUps --------------------------
    @Before
    public void before ()
    {
        people = new ArrayList8<>();
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

        IntStream.range(0, bound).forEach(j -> people.add(createOlder(minimumAge, minimumAge * 2)));
    }
    //endregion

    //region --------------- find (x5) -----------------------
    @Test
    public void find_Right ()
    {
        specialSetUp();

        ArrayList8<Person> list1 = new PartenerFinder().find(person1, context);
        ArrayList8<Person> list2 = new PartenerFinder().find(person2, context);

        assertEquals(person2, list1.get(0));
        assertEquals(person1, list2.get(0));
    }

    @Test
    public void find_Empty ()
    {
        ArrayList8<Person> people = new PartenerFinder().find(createPerson(), context);

        assertTrue(people.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void find_Null_1stParam ()
    {
        Person person = null;

        new PartenerFinder().find(person, context);
    }

    @Test (expected = IllegalArgumentException.class)
    public void find_Null_2ndParam ()
    {
        Context context = null;

        new PartenerFinder().find(createPerson(), context);
    }

    @Test (expected = IllegalArgumentException.class)
    public void find_Null_BothParam ()
    {
        Person person = null;
        Context context = null;

        new PartenerFinder().find(person, context);
    }
    //endregion

    //region --------------- merge (x3) ----------------------
    @Test
    public void merge_Right ()
    {
        specialSetUp();

        people = new ArrayList8<Person>()
        {{
            add(createPerson());
            add(createPerson());
            add(createPerson());
            add(createPerson());
        }};

        assertEquals(4, people.size());

        PartenerFinder finder = new PartenerFinder();

        finder.find(person1, context);

        ArrayList8<Person> merge = finder.merge(people);

        assertEquals(5, merge.size());
    }

    @Test
    public void merge_Empty ()
    {
        specialSetUp();

        people = new ArrayList8<>();

        assertEquals(0, people.size());

        PartenerFinder finder = new PartenerFinder();

        finder.find(person1, context);

        ArrayList8<Person> merge = finder.merge(people);

        assertEquals(1, merge.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void merge_Null ()
    {
        people = null;

        new PartenerFinder().merge(people);
    }
    //endregion
}