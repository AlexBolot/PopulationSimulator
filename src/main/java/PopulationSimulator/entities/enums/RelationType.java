package PopulationSimulator.entities.enums;

import org.jetbrains.annotations.NotNull;

import static PopulationSimulator.utils.Const.randBetween;

/*................................................................................................................................
 . Copyright (c)
 .
 . The RelationType class was coded by : Alexandre BOLOT
 .
 . Last modified : 26/01/18 20:27
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
     <h3>
     Created : Alexandre Bolot 26/01 <br>
     Modified : Alexandre Bolot 26/01
     </h3>
     <hr>

     @return A random RelationType out of the RelationType Enum
     */
    @NotNull
    public static RelationType getRandom ()
    {
        RelationType[] values = RelationType.values();

        return values[randBetween(0, values.length)];
    }
}
