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
 . Last modified : 04/02/18 22:28
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

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

     @param gender Gender of the Person
     */
    public PersonalData (@NotNull Gender gender)
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

     @param gender      Gender of the Person
     @param orientation Sexual Orientation of the Person
     */
    public PersonalData (@NotNull Gender gender, @NotNull SexualOrientation orientation)
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

     @param gender      Gender of the Person
     @param orientation Sexual Orientation of the Person
     @param birthday    Birthday of the Person
     */
    public PersonalData (int birthday, @NotNull Gender gender, @NotNull SexualOrientation orientation)
    {
        if (birthday > currentTime()) throw new IllegalArgumentException("Bday param can't be bigger than currentTime()");

        this.gender = gender;
        this.orientation = orientation;
        this.birthday = birthday;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @NotNull
    public Gender gender () { return gender; }

    public int age () { return currentTime() - birthday; }

    public int birthday () { return birthday; }

    @NotNull
    public SexualOrientation orientation () { return orientation; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Data + age + gender + orientation</h2>
     <hr>

     @return Data + age + gender + orientation
     */
    @Override
    @NotNull
    public String toString ()
    {
        return String.format("Data {%d}{%s}{%s}", age(), gender, orientation);
    }

    /**
     <hr>
     <h2>Compares : birthday, gender and orientation</h2>
     <hr>

     @param obj The Object to compare with this
     @return True if obj is equal to this, False otherwise
     */
    @Override
    public boolean equals (@NotNull Object obj)
    {
        if (!getClass().isInstance(obj)) return false;

        PersonalData dataCompare = (PersonalData) obj;

        if (birthday != dataCompare.birthday) return false;
        if (!gender.equals(dataCompare.gender)) return false;
        if (!orientation.equals(dataCompare.orientation)) return false;

        return true;
    }

    /**
     <hr>
     <h2>Returns a unique HashCode for this Context instance <br>
     Based on : birthday, gender and orientation</h2>
     <hr>

     @return A unique HashCode for this Context instance <br>
     Based on : birthday, gender and orientation
     */
    @Override
    public int hashCode ()
    {
        return Objects.hash(birthday, gender, orientation);
    }

    //endregion
}
