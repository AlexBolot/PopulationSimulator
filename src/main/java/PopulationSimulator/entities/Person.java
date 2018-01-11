package PopulationSimulator.entities;

import java.util.Objects;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Person class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 22:19
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Person
{
    //region --------------- Attributes ----------------------
    private final PersonalData data;
    //endregion

    //region --------------- Constructors --------------------
    public Person (PersonalData data)
    {
        this.data = data;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    public PersonalData data () { return data; }
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

        return data.equals(personCompare.data);
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;

        hash += Objects.hashCode(data.getName());
        hash += Objects.hashCode(data.getBirthday());
        hash += Objects.hashCode(data.getGender());
        hash += Objects.hashCode(data.getOrientation());

        return hash;
    }

    //endregion
}
