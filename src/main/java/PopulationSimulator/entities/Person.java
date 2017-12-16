package PopulationSimulator.entities;

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
        return "Person " + data.toString().substring(5);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null || !getClass().isInstance(obj)) return false;

        Person personCompare = (Person) obj;

        return data.equals(personCompare.data);
    }
    //endregion
}
