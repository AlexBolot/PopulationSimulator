package PopulationSimulator.entities.enums;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Gender class was coded by : Alexandre BOLOT
 .
 . Last modified : 30/01/18 02:27
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public enum Gender
{
    Male,
    Female;

    /**
     <hr>
     <h2>Gives a random Gender out of the Gender Enum</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 26/01 <br>
     Modified : Alexandre Bolot 26/01
     </h3>
     <hr>

     @return A random Gender out of the Gender Enum
     */
    @NotNull
    public static Gender getRandom ()
    {
        Gender[] values = Gender.values();

        return values[randBetween(0, values.length)];
    }

    /**
     <hr>
     <h2>Gives the opposite gender than the one given as param</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 26/01 <br>
     Modified : Alexandre Bolot 26/01
     </h3>
     <hr>

     @return The opposite gender than the one given as param
     */
    @NotNull
    @Contract (pure = true)
    public static Gender getOppositeGender (@NotNull Gender gender)
    {
        return (gender == Male) ? Female : Male;
    }

    public static boolean contains (@NotNull String value)
    {
        try
        {
            Gender.valueOf(value);
            return true;
        }
        catch (IllegalArgumentException ignored)
        {
            return false;
        }
    }
}
