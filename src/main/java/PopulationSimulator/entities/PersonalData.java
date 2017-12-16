package PopulationSimulator.entities;

import PopulationSimulator.controllers.Controller;

import static PopulationSimulator.entities.SexualOrientation.Hetero;

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
        this.birthday = Controller.currentTime;
    }

    public PersonalData (String name, Gender gender, SexualOrientation orientation)
    {
        this.name = name;
        this.gender = gender;
        this.orientation = orientation;
        this.birthday = Controller.currentTime;
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

    public void setName (String name) { this.name = name; }

    public Gender getGender () { return gender; }

    public void setGender (Gender gender) { this.gender = gender; }

    public int getAge () { return Controller.currentTime - birthday; }

    public int getBirthday () { return birthday; }

    public void setBirthday (int birthday) { this.birthday = birthday; }

    public SexualOrientation getOrientation () { return orientation; }

    public void setOrientation (SexualOrientation orientation) { this.orientation = orientation; }
    //endregion

    //region --------------- Override ------------------------
    @Override
    public String toString ()
    {
        return "Data {name:" + name + "}{age:" + getAge() + "}{gender:" + gender + "}{orientation:" + orientation + "}";
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
