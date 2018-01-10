package PopulationSimulator.entities;

import PopulationSimulator.controllers.SimulationController;
import PopulationSimulator.entities.enums.Gender;
import PopulationSimulator.entities.enums.SexualOrientation;

import static PopulationSimulator.entities.enums.SexualOrientation.Hetero;

public class PersonalData
{
    //region --------------- Attributes ----------------------
    private String            name;
    private Gender            gender;
    private int               birthday;
    private SexualOrientation orientation;
    //endregion

    //region --------------- Constructors --------------------
    public PersonalData (String name, Gender gender)
    {
        this.name = name;
        this.gender = gender;
        this.orientation = Hetero;
        this.birthday = SimulationController.currentTime;
    }

    public PersonalData (String name, Gender gender, SexualOrientation orientation)
    {
        this.name = name;
        this.gender = gender;
        this.orientation = orientation;
        this.birthday = SimulationController.currentTime;
    }

    public PersonalData (String name, int birthday, Gender gender, SexualOrientation orientation)
    {
        this.name = name;
        this.gender = gender;
        this.orientation = orientation;
        this.birthday = birthday;
    }
    //endregion

    //region --------------- Getters - Setters ---------------

    public String getName () { return name; }

    public Gender getGender () { return gender; }

    public int getAge () { return SimulationController.currentTime - birthday; }

    public int getBirthday () { return birthday; }

    public SexualOrientation getOrientation () { return orientation; }
    //endregion

    //region --------------- Override ------------------------
    @Override
    public String toString ()
    {
        return String.format("Data {%s}{%d}{%s}{%s}", name, getAge(), gender, orientation);
        //return String.format("Data {name:%s}{age:%d}{gender:%s}{orientation:%s}", name, getAge(), gender, orientation);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null || !getClass().isInstance(obj)) return false;

        PersonalData dataCompare = (PersonalData) obj;

        if (!name.equals(dataCompare.name)) return false;
        if (birthday != dataCompare.birthday) return false;
        if (!gender.equals(dataCompare.gender)) return false;
        if (!orientation.equals(dataCompare.orientation)) return false;

        return true;
    }
    //endregion
}
