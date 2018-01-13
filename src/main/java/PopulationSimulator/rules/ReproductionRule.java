package PopulationSimulator.rules;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.Population;
import PopulationSimulator.entities.Relation;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;

import java.util.Objects;
import java.util.Random;

import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ReproductionRule class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 00:39
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ReproductionRule extends SimpleRule
{
    private static final int anyAge = -1;
    //region --------------- Attributes ----------------------
    private int minimumAge;
    //endregion

    //region --------------- Constructors --------------------
    public ReproductionRule () { this(anyAge); }

    public ReproductionRule (int minimumAge) { this.minimumAge = minimumAge; }
    //endregion

    //region --------------- Override ------------------------
    public void apply (Population population)
    {
        Objects.requireNonNull(population, "population param is null");

        for (Relation relation : population.relations())
        {
            Gender gender1 = relation.person1().data().getGender();
            Gender gender2 = relation.person2().data().getGender();

            if (gender1.equals(Male) && gender2.equals(Female))
            {
                Random random = new Random();

                int age = SimulationController.currentTime;
                Gender gender = Gender.values()[random.nextInt(Gender.values().length)];
                SexualOrientation orientation = SexualOrientation.values()[random.nextInt(SexualOrientation.values().length)];

                Person newPerson = new Person(new PersonalData(age, gender, orientation));

                population.people().add(newPerson);
            }
        }
    }
    //endregion
}
