package PopulationSimulator.entities;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;

import static PopulationSimulator.entities.enums.SexualOrientation.Hetero;

/*................................................................................................................................
 . Copyright (c)
 .
 . The PersonalData class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 00:36
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
    public PersonalData (Gender gender)
    {
        this.gender = gender;
        this.orientation = Hetero;
        this.birthday = SimulationController.currentTime;
    }

    public PersonalData (Gender gender, SexualOrientation orientation)
    {
        this.gender = gender;
        this.orientation = orientation;
        this.birthday = SimulationController.currentTime;
    }

    public PersonalData (int birthday, Gender gender, SexualOrientation orientation)
    {
        this.gender = gender;
        this.orientation = orientation;
        this.birthday = birthday;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public Gender getGender () { return gender; }

    public int getAge () { return SimulationController.currentTime - birthday; }

    public int getBirthday () { return birthday; }

    public SexualOrientation getOrientation () { return orientation; }
    //endregion

    //region --------------- Override ------------------------
    @Override
    public String toString ()
    {
        return String.format("Data {%d}{%s}{%s}", getAge(), gender, orientation);
    }

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
    //endregion
}
