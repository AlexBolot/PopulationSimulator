package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Context;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.controllers.SimulationController.currentTime;
import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 04/02/18 22:33
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Creates a new Person if both mebers of the couple are in age and opposite gender</h2>
 <hr>
 */
@SuppressWarnings ("unused")
public class ReproductionRule extends SimpleRule
{
    //region --------------- Attributes ----------------------
    private static final int anyAge = -1;

    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of ReproductionRule : sets minimumAge at -1</h2>
     <hr>
     */
    public ReproductionRule () { this(anyAge); }

    /**
     <hr>
     <h2>Constructor of ReproductionRule using minimumAge param</h2>
     <hr>

     @param minimumAge Minimum age to reach to have kids
     */
    public ReproductionRule (int minimumAge)
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
     <h3>A new Person is created if
     - Both members of a Couple relationship have reached the minimumAge <br>
     - They have opposite Gender (Female X Male or Male X Female) <br>
     <br>
     Creates a new Person with random Gender and SexualOrientation
     </h3>
     <hr>

     @param context Context to apply this rule onto
     */
    public Context apply (@NotNull Context context)
    {
        Context newContext = new Context();

        for (Relation relation : context.relations())
        {
            PersonalData dataP1 = relation.person1().data();
            PersonalData dataP2 = relation.person2().data();

            Gender gender1 = dataP1.gender();
            Gender gender2 = dataP2.gender();

            int age1 = dataP1.age();
            int age2 = dataP2.age();

            if (gender1.equals(gender2)) continue;
            if (minimumAge != anyAge && age1 < minimumAge) continue;
            if (minimumAge != anyAge && age2 < minimumAge) continue;

            int age = currentTime();
            Gender gender = Gender.values()[randBetween(0, Gender.values().length)];
            SexualOrientation orientation = SexualOrientation.values()[randBetween(0, SexualOrientation.values().length)];

            Person newPerson = new Person(new PersonalData(age, gender, orientation));

            context.people().add(newPerson);
            newContext.people().add(newPerson);
        }

        return newContext;
    }
    //endregion
}
