package PopulationSimulator.entities;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Person class was coded by : Alexandre BOLOT
 .
 . Last modified : 14/01/18 00:34
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Person
{
    //region --------------- Attributes ----------------------
    private static int IDCounter = 0;

    private final int ID;

    private final PersonalData data;
    //endregion

    //region --------------- Constructors --------------------
    public Person (PersonalData data)
    {
        this.data = data;
        ID = IDCounter++;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public PersonalData data () { return data; }

    public int ID () { return ID; }
    //endregion

    //region --------------- Override ------------------------
    @Override
    public String toString ()
    {
        return String.format("Person %s", data.toString().substring(5));
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null || !getClass().isInstance(obj)) return false;

        Person personCompare = (Person) obj;

        return ID == personCompare.ID();
    }

    @Override
    public int hashCode () { return ID; }
    //endregion
}
