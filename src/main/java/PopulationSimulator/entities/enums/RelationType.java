package PopulationSimulator.entities.enums;

import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationType class was coded by : Alexandre BOLOT
 .
 . Last modified : 04/02/18 22:26
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public enum RelationType
{
    Couple;

    /**
     <hr>
     <h2>Gives a random RelationType out of the RelationType Enum</h2>
     <hr>

     @return A random RelationType out of the RelationType Enum
     */
    @NotNull
    public static RelationType getRandom ()
    {
        RelationType[] values = RelationType.values();

        return values[randBetween(0, values.length)];
    }

    public static boolean contains (@NotNull String value)
    {
        try
        {
            RelationType.valueOf(value);
            return true;
        }
        catch (IllegalArgumentException ignored)
        {
            return false;
        }
    }
}
