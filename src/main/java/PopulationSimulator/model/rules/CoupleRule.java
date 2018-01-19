package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static PopulationSimulator.controllers.SimulationController.currentTime;
import static PopulationSimulator.entities.enums.RelationType.Couple;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.model.factories.PersonFactory.getOppositeGender;

/*................................................................................................................................
 . Copyright (c)
 .
 . The CoupleRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:53
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Creates Couple relationships within the population</h2>
 <hr>
 */
@SuppressWarnings ("ConstantConditions")
public class CoupleRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private static final int anyAge = -1;

    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of CoupleRule : sets minimumAge at -1</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
    public CoupleRule () { this(anyAge); }

    /**
     <hr>
     <h2>Constructor of CoupleRule using minimumAge param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>

     @param minimumAge Minimum age to create a couple relationship with any other Person
     */
    public CoupleRule (int minimumAge)
    {
        //region --> Check params
        if (minimumAge < 0 && minimumAge != anyAge) throw new IllegalArgumentException("MinimumAge param can't be negative");
        //endregion

        this.minimumAge = minimumAge;
    }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Applies this Rule on the Context param</h2>
     <h3>
     — A Person that didn't reach minium age can't get in couple <br>
     — A Person that already is in couple, can't get in couple with someone else <br>
     — 2 People have to <code>match</code> to get in couple <br>
     See {@link CoupleRule#isMatch(Person, Person)}</h3>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param context Context to apply this rule onto
     */
    @Contract ("null -> fail")
    public void apply (@NotNull Context context)
    {
        //region --> Check params
        if (context == null) throw new IllegalArgumentException("Contect param is null");
        //endregion

        ArrayList<Person> tmpPeople = new ArrayList<>(context.people());
        if (minimumAge != anyAge) tmpPeople.removeIf(person -> person.data().age() < minimumAge);
        tmpPeople.removeIf(person -> context.relations().stream().anyMatch(relation -> relation.involves(person)));

        for (Person person1 : tmpPeople)
        {
            for (Person person2 : tmpPeople)
            {
                if (person2.equals(person1)) continue;
                if (context.relations().contains(relation -> relation.involves(person1))) continue;
                if (context.relations().contains(relation -> relation.involves(person2))) continue;

                if (isMatch(person1, person2))
                {
                    context.relations().add(new Relation(person1, person2, Couple, currentTime()));
                    break;
                }
            }
        }
    }
    //endregion

    //region --------------- Methods -------------------------

    /**
     <hr>
     <h2>Checks if 2 people match each other</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param p1 Person to match with p2
     @param p2 Person to match with p1
     @return True if p1 matches p2 (and reciprocally), False otherwise
     */
    private boolean isMatch (@NotNull Person p1, @NotNull Person p2)
    {
        //region --> Check params
        if (p1 == null) throw new IllegalArgumentException("Person1 param is null");
        if (p2 == null) throw new IllegalArgumentException("Person2 param is null");
        //endregion

        SexualOrientation ori1 = p1.data().orientation();
        SexualOrientation ori2 = p2.data().orientation();

        Gender gen1 = p1.data().gender();
        Gender gen2 = p2.data().gender();

        if (ori1 == Bi)
        {
            if (ori2 == Bi) return true;
            if (ori2 == Hetero) return gen1 == getOppositeGender(gen2);
            if (ori2 == Homo) return gen1 == gen2;
        }

        if (ori1 == Hetero) return gen1 == getOppositeGender(gen2) && (ori2 == Hetero || ori2 == Bi);
        if (ori1 == Homo) return gen1 == gen2 && (ori2 == Homo || ori2 == Bi);

        return false;
    }
    //endregion
}
