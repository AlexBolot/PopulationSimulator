package PopulationSimulator.entities.enums;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Gender class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 08:18
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("ConstantConditions")
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
        //region --> Check params
        if (gender == null) throw new IllegalArgumentException("Gender param is null");
        //endregion

        return (gender == Male) ? Female : Male;
    }
}
