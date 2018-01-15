package PopulationSimulator.model.rules;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;

import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RulesTestingUtils class was coded by : Alexandre BOLOT
 .
 . Last modified : 15/01/18 13:35
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class RulesTestingUtils
{
    /**
     <hr>
     <h2>Creates all possible combinations of couples</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return A LinkedHashSet of Person, containing all possible combinations of couples
     */
    @NotNull
    static LinkedHashSet<Person> createAllCombinations (int minimumAge)
    {
        int oldEnough = minimumAge + randBetween(1, 5);

        LinkedHashSet<Person> people = new LinkedHashSet<>();

        //region --> Hetero Couples (x3 Couples - x3 Repro)
        people.add(createPerson(oldEnough, Male, Hetero));
        people.add(createPerson(oldEnough, Female, Hetero));

        people.add(createPerson(oldEnough, Male, Hetero));
        people.add(createPerson(oldEnough, Female, Hetero));

        people.add(createPerson(oldEnough, Male, Hetero));
        people.add(createPerson(oldEnough, Female, Hetero));
        //endregion
        //region --> Homo Couples   (x2 Couples - x0 Repro)
        people.add(createPerson(oldEnough, Male, Homo));
        people.add(createPerson(oldEnough, Male, Homo));

        people.add(createPerson(oldEnough, Female, Homo));
        people.add(createPerson(oldEnough, Female, Homo));
        //endregion
        //region --> Bi Couples     (x7 Couples - x3 Repro)
        people.add(createPerson(oldEnough, Male, Bi));
        people.add(createPerson(oldEnough, Female, Bi));

        people.add(createPerson(oldEnough, Male, Bi));
        people.add(createPerson(oldEnough, Male, Bi));

        people.add(createPerson(oldEnough, Female, Bi));
        people.add(createPerson(oldEnough, Female, Bi));

        people.add(createPerson(oldEnough, Male, Bi));
        people.add(createPerson(oldEnough, Male, Homo));

        people.add(createPerson(oldEnough, Female, Bi));
        people.add(createPerson(oldEnough, Female, Homo));

        people.add(createPerson(oldEnough, Male, Bi));
        people.add(createPerson(oldEnough, Female, Hetero));

        people.add(createPerson(oldEnough, Female, Bi));
        people.add(createPerson(oldEnough, Male, Hetero));
        //endregion

        return people;
    }

    /**
     <hr>
     <h2>Generates a Person created from given params : age, gender and orientation</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param age         Age of the Person
     @param gender      Gender of the Person
     @param orientation SexualOrientation of the Perso
     @return A Person created from given params : age, gender and orientation
     */
    @NotNull
    static Person createPerson (int age, Gender gender, SexualOrientation orientation)
    {
        return new Person(new PersonalData(-age, gender, orientation));
    }

    /**
     <hr>
     <h2>Generates a random Person with age under/over the limit, depending on the young param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 14/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param limit Age limit of a Person's lifespan
     @param young True if the generated age has to be under the limit
     @return A random Person with age under/over the limit, depending on the young param
     */
    @NotNull
    static Person randPerson (int limit, boolean young)
    {
        int age = young ? -randBetween(0, limit) : -randBetween(limit, limit + 10);

        Gender gender = Gender.values()[Gender.values().length - 1];
        SexualOrientation orientation = SexualOrientation.values()[SexualOrientation.values().length - 1];

        return new Person(new PersonalData(age, gender, orientation));
    }
}
