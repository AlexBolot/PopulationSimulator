package PopulationSimulator.model.factories;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.RelationType;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.junit.Test;

import static PopulationSimulator.entities.enums.RelationType.Couple;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.model.factories.PersonFactory.createPerson;
import static PopulationSimulator.model.factories.RelationFactory.createCouple;
import static PopulationSimulator.model.factories.RelationFactory.createRelation;
import static org.junit.Assert.assertTrue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationFactoryTest class was coded by : Alexandre BOLOT
 .
 . Last modified : 06/02/18 22:28
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class RelationFactoryTest
{

    @Test
    public void createRelation_Right ()
    {
        for (int i = 0; i < 1000; i++)
        {
            Person person1 = createPerson();
            Person person2 = createPerson();

            RelationType type = RelationType.getRandom();

            Relation relation = createRelation(person1, person2, type);

            assertTrue(relation.involves(person1));
            assertTrue(relation.involves(person2));
            assertTrue(relation.type() == type);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void createRelation_Null ()
    {
        Person person1 = null;
        Person person2 = null;
        RelationType type = null;

        createRelation(person1, person2, type);
    }

    @Test
    public void createCouple_Person_Right ()
    {
        for (int i = 0; i < 1000; i++)
        {
            Person person1 = createPerson();
            Person person2 = createPerson();

            Relation relation = createRelation(person1, person2, Couple);

            assertTrue(relation.involves(person1));
            assertTrue(relation.involves(person2));
            assertTrue(relation.type() == Couple);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void createCouple_Person_Null ()
    {
        Person person1 = null;
        Person person2 = null;

        createCouple(person1, person2);
    }

    @Test
    public void createCouple_Gender_Right ()
    {
        for (int i = 0; i < 1000; i++)
        {
            Gender initGen1 = Gender.getRandom();
            Gender initGen2 = Gender.getRandom();

            Relation relation = createCouple(initGen1, initGen2);

            Gender gen1 = relation.person1().data().gender();
            Gender gen2 = relation.person2().data().gender();
            SexualOrientation ori1 = relation.person1().data().orientation();
            SexualOrientation ori2 = relation.person2().data().orientation();

            boolean validGen1 = gen1 == initGen1 || gen2 == initGen1;
            boolean validGen2 = gen1 == initGen2 || gen2 == initGen2;
            boolean validOri1 = (gen1 == gen2) ? ((ori1 == Bi) || (ori1 == Homo)) : ((ori1 == Bi) || (ori1 == Hetero));
            boolean validOri2 = (gen1 == gen2) ? ((ori2 == Bi) || (ori2 == Homo)) : ((ori2 == Bi) || (ori2 == Hetero));

            assertTrue(validGen1);
            assertTrue(validGen2);
            assertTrue(validOri1);
            assertTrue(validOri2);

            assertTrue(relation.type() == Couple);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void createCouple_Gender_Null ()
    {
        Gender gender1 = null;
        Gender gender2 = null;

        createCouple(gender1, gender2);
    }

    @Test
    public void createCouple_Orientation ()
    {
        for (int i = 0; i < 1000; i++)
        {
            SexualOrientation initOri1 = SexualOrientation.getRandom();
            SexualOrientation initOri2 = (initOri1 == Bi) ? SexualOrientation.getRandom() : ((initOri1 == Hetero) ? Hetero : Homo);

            Relation relation = createCouple(initOri1, initOri2);

            Gender gen1 = relation.person1().data().gender();
            Gender gen2 = relation.person2().data().gender();
            SexualOrientation ori1 = relation.person1().data().orientation();
            SexualOrientation ori2 = relation.person2().data().orientation();

            boolean validGen1 = (initOri1 == Bi) || ((initOri1 == Homo) ? gen1 == gen2 : gen1 == Gender.getOpposite(gen2));
            boolean validGen2 = (initOri2 == Bi) || ((initOri2 == Homo) ? gen1 == gen2 : gen1 == Gender.getOpposite(gen2));
            boolean validOri1 = ori1 == initOri1 || ori2 == initOri1;
            boolean validOri2 = ori1 == initOri2 || ori2 == initOri2;

            assertTrue(validGen1);
            assertTrue(validGen2);
            assertTrue(validOri1);
            assertTrue(validOri2);

            assertTrue(relation.type() == Couple);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void createCouple_Orientation_Null ()
    {
        SexualOrientation ori1 = null;
        SexualOrientation ori2 = null;

        createCouple(ori1, ori2);
    }
}