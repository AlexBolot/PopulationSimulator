package PopulationSimulator.model.factories;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import PopulationSimulator.utils.ArrayList8;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static PopulationSimulator.controllers.SimulationController.currentTime;
import static PopulationSimulator.entities.enums.Gender.Female;
import static PopulationSimulator.entities.enums.Gender.Male;
import static PopulationSimulator.entities.enums.SexualOrientation.*;
import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonFactory class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 08:16
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class PersonFactory
{
    //region --------------- Create Person -------------------

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = currentTime <br>
     - Gender = random <br>
     - SexualOrientation = random </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {currentTime, randomGender, randomOrientation}
     */
    @NotNull
    public static Person createPerson ()
    {
        return createPerson(currentTime());
    }

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = bday param <br>
     - Gender = random <br>
     - SexualOrientation = random </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {bday param, randomGender, randomOrientation}
     */
    @NotNull
    public static Person createPerson (int bday)
    {
        return createPerson(bday, Gender.getRandom(), SexualOrientation.getRandom());
    }

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = currentTime <br>
     - Gender = gender param <br>
     - SexualOrientation = Hetero </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {currentTime, gender param, Hetero}
     */
    @NotNull
    public static Person createPerson (@NotNull Gender gender)
    {
        return new Person(new PersonalData(gender));
    }

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = currentTime <br>
     - Gender = gender param <br>
     - SexualOrientation = orientation param </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {currentTime, gender param, orientation param}
     */
    @NotNull
    public static Person createPerson (@NotNull Gender gender, @NotNull SexualOrientation orientation)
    {
        return new Person(new PersonalData(gender, orientation));
    }

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = bday param <br>
     - Gender = gender param <br>
     - SexualOrientation = orientation param </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {bday param, gender param, orientation param}
     */
    @NotNull
    public static Person createPerson (int bday, @NotNull Gender gender, @NotNull SexualOrientation orientation)
    {
        return new Person(new PersonalData(bday, gender, orientation));
    }

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = calculated to be younger than age param <br>
     - Gender = random <br>
     - SexualOrientation = random </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {random younger than age param, randomGender, randomOrientation}
     */
    @NotNull
    public static Person createYounger (int age)
    {
        int currentTime = currentTime();

        //region --> Check params
        if (age > currentTime) throw new IllegalArgumentException("Age param can't be bigger than currentTime()");
        //endregion

        int bday = randBetween((currentTime - age) + 1, currentTime);

        return createPerson(bday, Gender.getRandom(), SexualOrientation.getRandom());
    }

    /**
     <hr>
     <h2>Creates a new Person with : <br>
     - Birthday = calculated for age to be between age param and limit param <br>
     - Gender = random <br>
     - SexualOrientation = random </h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A Person created with {random older than age param and younger than limit param, randomGender, randomOrientation}
     */
    @NotNull
    public static Person createOlder (int age, int limit)
    {
        //region --> Check params
        if (age <= 0) throw new IllegalArgumentException("Age param can't negative or zero");
        if (limit < age) throw new IllegalArgumentException("Limit param can't be smaller than age");
        //endregion

        int currentTime = currentTime();
        int bday = randBetween(currentTime - limit, currentTime - age);

        return createPerson(bday, Gender.getRandom(), SexualOrientation.getRandom());
    }
    //endregion

    //region --------------- Create Groups -------------------

    /**
     <hr>
     <h2>Creates a valid couple of People (Relation object not included)</h2>
     <h3>If both genders are the same : <br>
     -> They'll have same gender and be Homosexual or Bi <br>
     <br>
     Else : <br>
     -> They'll have opposite gender and be Heterosexual or Bi</h3>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return Creates a valid couple of People (Relation object not included)
     */
    public static ArrayList8<Person> createCouple (@NotNull Gender gender1, @NotNull Gender gender2)
    {
        //region --> Check params
        if (gender1 == null) throw new IllegalArgumentException("Gendre1 param is null");
        if (gender2 == null) throw new IllegalArgumentException("Gendre2 param is null");
        //endregion

        Random random = new Random();
        SexualOrientation orientation;

        ArrayList8<Person> people = new ArrayList8<>();

        if (gender1.equals(gender2))
        {
            orientation = random.nextBoolean() ? Homo : Bi;
            people.add(createPerson(gender1, orientation));

            orientation = random.nextBoolean() ? Homo : Bi;
            people.add(createPerson(gender1, orientation));
        }
        else
        {
            orientation = random.nextBoolean() ? Hetero : Bi;
            people.add(createPerson(gender1, orientation));

            orientation = random.nextBoolean() ? Hetero : Bi;
            people.add(createPerson(gender2, orientation));
        }

        return people;
    }

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
    public static ArrayList8<Person> createAllCombinations (int minimumAge)
    {
        int oldEnough = -(minimumAge + randBetween(1, 5));

        ArrayList8<Person> people = new ArrayList8<>();

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
    //endregion
}
