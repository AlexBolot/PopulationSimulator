package PopulationSimulator.model.factories;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.RelationType;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static PopulationSimulator.entities.enums.Gender.getRandom;
import static PopulationSimulator.entities.enums.RelationType.Couple;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.model.factories.PersonFactory.createPerson;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationFactory class was coded by : Alexandre BOLOT
 .
 . Last modified : 06/02/18 22:28
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class RelationFactory
{
    /**
     <hr>
     <h2>Creates a new Relation, using [person1], [person2], [type]</h2>
     <hr>

     @param person1 Person to add to the relation
     @param person2 Person to add to the relation
     @param type    Type of relation
     @return A new Relation, involving [person1] and [person2], [type]
     */
    @NotNull
    public static Relation createRelation (@NotNull Person person1, @NotNull Person person2, @NotNull RelationType type)
    {
        return new Relation(person1, person2, type);
    }

    /**
     <hr>
     <h2>Creates a new Couple Relation, using [person1], [person2]</h2>
     <hr>

     @param person1 Person to add to the relation
     @param person2 Person to add to the relation
     @return A new Couple Relation, involving [person1] and [person2]
     */
    @NotNull
    public static Relation createCouple (@NotNull Person person1, @NotNull Person person2)
    {
        return createRelation(person1, person2, Couple);
    }

    /**
     <hr>
     <h2>Creates a new Couple Relation, using [gender1], [gender2]</h2>
     <h3>Note : creates the members of the Relation with random Sexual Orientation, fitting genders in params</h3>
     <hr>

     @param gender1 Gender of the 1st member of the Relation
     @param gender2 Gender of the 2nd member of the Relation
     @return A new Couple Relation, using [gender1], [gender2]
     */
    @NotNull
    public static Relation createCouple (@NotNull Gender gender1, @NotNull Gender gender2)
    {
        Random random = new Random();

        SexualOrientation orientation1;
        SexualOrientation orientation2;

        orientation1 = (gender1 == gender2) ? (random.nextBoolean() ? Homo : Bi) : (random.nextBoolean() ? Hetero : Bi);
        orientation2 = (gender1 == gender2) ? (random.nextBoolean() ? Homo : Bi) : (random.nextBoolean() ? Hetero : Bi);

        Person person1 = createPerson(gender1, orientation1);
        Person person2 = createPerson(gender2, orientation2);

        return createCouple(person1, person2);
    }

    /**
     <hr>
     <h2>Creates a new Couple Relation, using [ori1], [ori2]</h2>
     <h3>Note : creates the members of the Relation with random Genders, fitting sexual orientations in params</h3>
     <hr>

     @param ori1 Sexual Orientation of the 1st member of the Relation
     @param ori2 Sexual Orientation of the 2nd member of the Relation
     @return A new Couple Relation, using [ori1], [ori2]
     */
    @NotNull
    public static Relation createCouple (@NotNull SexualOrientation ori1, @NotNull SexualOrientation ori2)
    {
        if (ori1 == ori2 || ori1 == Bi || ori2 == Bi)
        {
            Gender gender1 = Gender.getRandom();
            Person person1 = createPerson(gender1, ori1);

            Gender gender2 = Gender.getRandom();

            if (ori1 == Bi)
            {
                if (ori2 == Bi) gender2 = getRandom();
                if (ori2 == Hetero) gender2 = Gender.getOpposite(gender1);
                if (ori2 == Homo) gender2 = gender1;
            }

            if (ori1 == Hetero) gender2 = Gender.getOpposite(gender1);
            if (ori1 == Homo) gender2 = gender1;

            Person person2 = createPerson(gender2, ori2);

            return createCouple(person1, person2);
        }
        else
        {
            throw new IllegalArgumentException("Sexual Orientations in conflict");
        }
    }
}
