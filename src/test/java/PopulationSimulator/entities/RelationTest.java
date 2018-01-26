package PopulationSimulator.entities;

import PopulationSimulator.entities.enums.RelationType;
import PopulationSimulator.model.factories.PersonFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static PopulationSimulator.controllers.SimulationController.currentTime;
import static org.junit.Assert.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 08:37
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class RelationTest
{
    private Person   person1;
    private Person   person2;
    private Relation relation;

    @Before
    public void setUp ()
    {
        person1 = PersonFactory.createPerson();
        person2 = PersonFactory.createPerson();
        relation = new Relation(person1, person2, RelationType.getRandom());
    }

    @Test
    public void involves_Right ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            Person person3 = PersonFactory.createPerson();

            assertTrue(relation.involves(person1));
            assertTrue(relation.involves(person2));
            assertFalse(relation.involves(person3));
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void involves_Null ()
    {
        Person person = null;

        relation.involves(person);
    }

    @Test
    public void getDuration ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();
            assertEquals(currentTime(), relation.getDuration());
        }
    }

    @Test
    public void getDeltaAge ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            int age1 = person1.data().age();
            int age2 = person2.data().age();

            int delta = Math.abs(age1 - age2);

            assertEquals(delta, relation.getDeltaAge());
        }
    }

    @Test
    public void getOther_Right ()
    {
        for (int i = 0; i < 1000; i++)
        {
            setUp();

            Person person3 = PersonFactory.createPerson();

            Optional<Person> opt1 = relation.getOther(person1);
            Optional<Person> opt2 = relation.getOther(person2);
            Optional<Person> opt3 = relation.getOther(person3);

            assertTrue(opt1.isPresent());
            assertTrue(opt2.isPresent());
            assertFalse(opt3.isPresent());

            assertEquals(person2, opt1.get());
            assertEquals(person1, opt2.get());
        }
    }
}