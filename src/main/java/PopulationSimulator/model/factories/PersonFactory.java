package PopulationSimulator.model.factories;

import PopulationSimulator.entities.Person;
import PopulationSimulator.entities.PersonalData;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.Objects;
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
 . Last modified : 18/01/18 23:14
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class PersonFactory
{
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
        return new Person(new PersonalData(currentTime(), randomGender(), randomOrientation()));
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
        if (bday > currentTime()) throw new IllegalArgumentException("Bday param can't be bigger than currentTime()");

        return new Person(new PersonalData(bday, randomGender(), randomOrientation()));
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
    public static Person createPerson (Gender gender)
    {
        Objects.requireNonNull(gender, "Gender param is null");

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
    public static Person createPerson (Gender gender, SexualOrientation orientation)
    {
        Objects.requireNonNull(gender, "Gender param is null");
        Objects.requireNonNull(orientation, "SexualOrientation param is null");

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
    public static Person createPerson (int bday, Gender gender, SexualOrientation orientation)
    {
        if (bday > currentTime()) throw new IllegalArgumentException("Bday param can't be bigger than currentTime()");
        Objects.requireNonNull(gender, "Gender param is null");
        Objects.requireNonNull(orientation, "SexualOrientation param is null");

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
        if (age > currentTime()) throw new IllegalArgumentException("Age param can't be bigger than currentTime()");

        int bday = randBetween((currentTime() - age) + 1, currentTime());

        return createPerson(bday, randomGender(), randomOrientation());
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
        if (age <= 0) throw new IllegalArgumentException("Age param can't negative or zero");
        if (limit < age) throw new IllegalArgumentException("Limit param can't be smaller than age");

        int bday = randBetween(currentTime() - limit, currentTime() - age);

        return createPerson(bday, randomGender(), randomOrientation());
    }

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
    public static LinkedHashSet<Person> createCouple (Gender gender1, Gender gender2)
    {
        Objects.requireNonNull(gender1, "Gender1 param is null");
        Objects.requireNonNull(gender2, "Gender2 param is null");

        Random random = new Random();
        SexualOrientation orientation;

        LinkedHashSet<Person> hashSet = new LinkedHashSet<>();

        if (gender1.equals(gender2))
        {
            orientation = random.nextBoolean() ? Homo : Bi;
            hashSet.add(createPerson(gender1, orientation));

            orientation = random.nextBoolean() ? Homo : Bi;
            hashSet.add(createPerson(gender1, orientation));
        }
        else
        {
            orientation = random.nextBoolean() ? Hetero : Bi;
            hashSet.add(createPerson(gender1, orientation));

            orientation = random.nextBoolean() ? Hetero : Bi;
            hashSet.add(createPerson(gender2, orientation));
        }

        return hashSet;
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
    public static LinkedHashSet<Person> createAllCombinations (int minimumAge)
    {
        int oldEnough = -(minimumAge + randBetween(1, 5));

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
     <h2>Gives a random Gender out of the Gender Enum</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A random Gender out of the Gender Enum
     */
    public static Gender randomGender ()
    {
        Gender[] values = Gender.values();

        return values[randBetween(0, values.length)];
    }

    /**
     <hr>
     <h2>Gives the opposite gender than the one given as param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return The opposite gender than the one given as param
     */
    @Nullable
    public static Gender getOppositeGender (Gender gender)
    {
        Objects.requireNonNull(gender, "Gender param is null");

        if (gender.equals(Male)) return Female;
        if (gender.equals(Female)) return Male;

        else return null;
    }

    /**
     <hr>
     <h2>Gives a random SexualOrientation out of the SexualOrientation Enum</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 15/01 <br>
     Modified : Alexandre Bolot 15/01
     </h3>
     <hr>

     @return A random SexualOrientation out of the SexualOrientation Enum
     */
    public static SexualOrientation randomOrientation ()
    {
        SexualOrientation[] values = SexualOrientation.values();

        return values[randBetween(0, values.length)];
    }
}
