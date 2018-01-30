package PopulationSimulator.entities.enums;

import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The SexualOrientation class was coded by : Alexandre BOLOT
 .
 . Last modified : 30/01/18 02:28
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public enum SexualOrientation
{
    Hetero,
    Homo,
    Bi;

    /**
     <hr>
     <h2>Gives a random SexualOrientation out of the SexualOrientation Enum</h2>
     <hr>
     <h3>
     Created : Alexandre Bolot 26/01 <br>
     Modified : Alexandre Bolot 26/01
     </h3>
     <hr>

     @return A random SexualOrientation out of the SexualOrientation Enum
     */
    @NotNull
    public static SexualOrientation getRandom ()
    {
        SexualOrientation[] values = SexualOrientation.values();

        return values[randBetween(0, values.length)];
    }

    public static boolean contains (@NotNull String value)
    {
        try
        {
            SexualOrientation.valueOf(value);
            return true;
        }
        catch (IllegalArgumentException ignored)
        {
            return false;
        }
    }
}
