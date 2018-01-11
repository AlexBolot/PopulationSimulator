package PopulationSimulator.utils;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Const class was coded by : Alexandre BOLOT
 .
 . Last modified : 11/01/18 22:21
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Const
{
    private static final Random random = new Random();

    /**
     <hr>
     <h2>Generates a random int between lowBound (included) and highBound (excluded)</h2>
     <h3>randNum is in range of : [lowBound : highBound[</h3>
     <hr>

     @param lowBound  Included lower bound of the random number
     @param highBound Excluded higer bound of the random number
     @return A random int between lowBound (included) and highBound (excluded)
     */
    public static int randBetween (int lowBound, int highBound)
    {
        return random.nextInt(highBound - lowBound) + lowBound;
    }
}
