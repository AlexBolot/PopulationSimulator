package PopulationSimulator.model.rules;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;

import java.util.Objects;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 15/01/18 13:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 <hr>
 <h2>Creates a new Person if both mebers of the couple are in age and opposite gender</h2>
 <hr>
 */
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
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>
     */
    public ReproductionRule () { this(anyAge); }

    /**
     <hr>
     <h2>Constructor of ReproductionRule using minimumAge param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 10/01
     </h3>
     <hr>

     @param minimumAge Minimum age to reach to have kids
     */
    public ReproductionRule (int minimumAge) { this.minimumAge = minimumAge; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Applies this Rule on the Population param</h2>
     <h3>A new Person is created if
     - Both members of a Couple relationship have reached the minimumAge <br>
     - They have opposite Gender (Female X Male or Male X Female) <br>
     <br>
     Creates a new Person with random Gender and SexualOrientation
     </h3>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param population Population to apply this rule onto
     */
    public void apply (Population population)
    {
        Objects.requireNonNull(population, "population param is null");

        for (Relation relation : population.relations())
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

            int age = SimulationController.currentTime();
            Gender gender = Gender.values()[randBetween(0, Gender.values().length)];
            SexualOrientation orientation = SexualOrientation.values()[randBetween(0, SexualOrientation.values().length)];

            Person newPerson = new Person(new PersonalData(age, gender, orientation));

            population.people().add(newPerson);
        }
    }
    //endregion
}
