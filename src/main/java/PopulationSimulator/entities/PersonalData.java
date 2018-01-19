package PopulationSimulator.entities;

import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static PopulationSimulator.controllers.SimulationController.currentTime;
import static PopulationSimulator.entities.enums.SexualOrientation.Hetero;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonalData class was coded by : Alexandre BOLOT
 .
 . Last modified : 19/01/18 23:31
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
public class PersonalData
{
    //region --------------- Attributes ----------------------
    private Gender            gender;
    private int               birthday;
    private SexualOrientation orientation;
    //endregion

    //region --------------- Constructors --------------------

    /**
     <hr>
     <h2>Constructor of PersonalData using : <br>
     - Gender : gender param <br>
     - Orientation : Hetero (as default) <br>
     - Birthday : Now (SimulationController.currentTime())</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param gender Gender of the Person
     */
    public PersonalData (Gender gender)
    {
        this(currentTime(), gender, Hetero);
    }

    /**
     <hr>
     <h2>Constructor of PersonalData using : <br>
     - Gender : gender param <br>
     - Orientation : orientation param <br>
     - Birthday : Now (SimulationController.currentTime())</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param gender      Gender of the Person
     @param orientation Sexual Orientation of the Person
     */
    public PersonalData (Gender gender, SexualOrientation orientation)
    {
        this(currentTime(), gender, orientation);
    }

    /**
     <hr>
     <h2>Constructor of PersonalData using : <br>
     - Gender : gender param <br>
     - Orientation : orientation param <br>
     - Birthday : birthday param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param gender      Gender of the Person
     @param orientation Sexual Orientation of the Person
     @param birthday    Birthday of the Person
     */
    public PersonalData (int birthday, @NotNull Gender gender, @NotNull SexualOrientation orientation)
    {
        //region --> Check params
        if (birthday > currentTime()) throw new IllegalArgumentException("Bday param can't be bigger than currentTime()");
        if (gender == null) throw new IllegalArgumentException("Gender param is null");
        if (orientation == null) throw new IllegalArgumentException("Orientation param is null");
        //endregion

        this.gender = gender;
        this.orientation = orientation;
        this.birthday = birthday;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public Gender gender () { return gender; }

    public int age () { return currentTime() - birthday; }

    public int birthday () { return birthday; }

    public SexualOrientation orientation () { return orientation; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Data + age + gender + orientation</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Data + age + gender + orientation
     */
    @Override
    public String toString ()
    {
        return String.format("Data {%d}{%s}{%s}", age(), gender, orientation);
    }

    /**
     <hr>
     <h2>Compares : birthday, gender and orientation</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param obj The Object to compare with this
     @return True if obj is equal to this, False otherwise
     */
    @Override
    public boolean equals (Object obj)
    {
        if (obj == null || !getClass().isInstance(obj)) return false;

        PersonalData dataCompare = (PersonalData) obj;

        if (birthday != dataCompare.birthday) return false;
        if (!gender.equals(dataCompare.gender)) return false;
        if (!orientation.equals(dataCompare.orientation)) return false;

        return true;
    }


    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 16/01
     </h3>
     <hr>

     @return Unique HashCode of this PersonalData instance <br>
     Based on : birthday, gender and orientation
     */
    @Override
    public int hashCode ()
    {
        return Objects.hash(birthday, gender, orientation);
    }

    //endregion
}
