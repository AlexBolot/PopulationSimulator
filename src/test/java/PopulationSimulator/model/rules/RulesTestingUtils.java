package PopulationSimulator.model.rules;

import PopulationSimulator.model.entities.Person;
import PopulationSimulator.model.entities.PersonalData;
import PopulationSimulator.model.enums.Gender;
import PopulationSimulator.model.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RulesTestingUtils class was coded by : Alexandre BOLOT
 .
 . Last modified : 16/12/18 14:10
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class RulesTestingUtils {
    /**
     * <hr>
     * <h2>Generates a Person created from given params : age, gender and orientation</h2>
     * <hr>
     *
     * @param age         Age of the Person
     * @param gender      Gender of the Person
     * @param orientation SexualOrientation of the Perso
     * @return A Person created from given params : age, gender and orientation
     */
    @NotNull
    static Person createPerson(int age, Gender gender, SexualOrientation orientation) {
        return new Person(new PersonalData(-age, gender, orientation));
    }

    /**
     * <hr>
     * <h2>Generates a random Person with age under/over the limit, depending on the young param</h2>
     * <hr>
     *
     * @param limit Age limit of a Person's lifespan
     * @param young True if the generated age has to be under the limit
     * @return A random Person with age under/over the limit, depending on the young param
     */
    @NotNull
    static Person randPerson(int limit, boolean young) {
        int age = young ? -randBetween(0, limit) : -randBetween(limit, limit + 10);

        Gender gender = Gender.values()[Gender.values().length - 1];
        SexualOrientation orientation = SexualOrientation.values()[SexualOrientation.values().length - 1];

        return new Person(new PersonalData(age, gender, orientation));
    }
}
