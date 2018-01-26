package PopulationSimulator.entities;

import org.jetbrains.annotations.NotNull;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Person class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 21:15
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

    /**
     <hr>
     <h2>Simple constructor of Person <br>
     Fetches ID, attributes it, then increments it.</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @param data Personal data of the Person
     */
    public Person (@NotNull PersonalData data)
    {
        this.data = data;
        ID = IDCounter++;
    }
    //endregion

    //region --------------- Getters - Setters ---------------
    @NotNull
    public PersonalData data () { return data; }

    public int ID () { return ID; }
    //endregion

    //region --------------- Override ------------------------

    /**
     <hr>
     <h2>Format : Person + ID + data.toString()</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 16/12 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Person + ID + data.toString()
     */
    @Override
    @NotNull
    public String toString ()
    {
        return String.format("%s : %d - %s", getClass().getSimpleName(), ID, data.toString().substring(5));
    }

    /**
     <hr>
     <h2>Compares : ID</h2>
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
    public boolean equals (@NotNull Object obj)
    {
        if (!getClass().isInstance(obj)) return false;

        Person personCompare = (Person) obj;

        return ID == personCompare.ID();
    }

    /**
     <hr>
     <h3>
     Created : Alexandre Bolot 10/01 <br>
     Modified : Alexandre Bolot 14/01
     </h3>
     <hr>

     @return Unique HashCode of this Person instance <br>
     Based on : ID
     */
    @Override
    public int hashCode () { return ID; }
    //endregion
}
